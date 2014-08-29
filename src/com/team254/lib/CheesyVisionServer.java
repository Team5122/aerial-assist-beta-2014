package com.team254.lib;

/**
 * @author Tom Bottiglieri
 * Team 254, The Cheesy Poofs
 */

import edu.wpi.first.wpilibj.Timer;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class CheesyVisionServer implements Runnable {
  
  private static CheesyVisionServer instance_;
  Thread serverThread = new Thread(this);
  private int listenPort_;
  private Vector<Socket> connections_;
  private boolean counting_ = false;
  private int leftCount_ = 0, rightCount_ = 0, totalCount_ = 0;
  private boolean curLeftStatus_ = false, curRightStatus_ = false;
  double lastHeartbeatTime_ = -1;
  private boolean listening_ = true;

  public static CheesyVisionServer getInstance() {
      if (instance_ == null) {
          instance_ = new CheesyVisionServer();
      }
      return instance_;
  }
  
  public void start() {
      serverThread.start();
  }
  
  public void stop() {
      listening_  = false;
  }
    
  private CheesyVisionServer() {
    this(1180);
  }

  private CheesyVisionServer(int port) {
    listenPort_ = port;
    connections_ = new Vector<Socket>();
  }
  
  public boolean hasClientConnection() {
    return lastHeartbeatTime_ > 0 && (Timer.getFPGATimestamp() - lastHeartbeatTime_) < 3.0; 
  }
  
  public void setPort(int port) {
    listenPort_ = port;
  }

  private void updateCounts(boolean left, boolean right) {
    if (counting_) {
      leftCount_ += left ? 1 : 0;
      rightCount_ += right ? 1 : 0;
      totalCount_++;
    }
  }

  public void startSamplingCounts() {
    counting_ = true;
  }

  public void stopSamplingCounts() {
    counting_ = false;
  }

  public void reset() {
    leftCount_ = rightCount_ = totalCount_ = 0;
    curLeftStatus_ = curRightStatus_ = false;
  }
  
  public int getLeftCount() {
      return leftCount_;
  }
  
  public int getRightCount() {
      return rightCount_;
  }
  
  public int getTotalCount() {
      return totalCount_;
  }
  
  public boolean getLeftStatus() {
      return curLeftStatus_;
  }
  
  public boolean getRightStatus() {
      return curRightStatus_;
  }
  
  // This class handles incoming TCP connections
  private class VisionServerConnectionHandler implements Runnable {

    Socket connection;

    public VisionServerConnectionHandler(Socket c) {
      connection = c;
    }

    public void run() {
      try {
        InputStream is = connection.getInputStream();

        //int ch = 0;
        byte[] b = new byte[1024];
        double timeout = 10.0;
        double lastHeartbeat = Timer.getFPGATimestamp();
        CheesyVisionServer.this.lastHeartbeatTime_ = lastHeartbeat;
        while (Timer.getFPGATimestamp() < lastHeartbeat + timeout) {
          //boolean gotData = false;
          while (is.available() > 0) {
            //gotData = true;
            int read = is.read(b);
            for (int i = 0; i < read; ++i) {
              byte reading = b[i];
              boolean leftStatus = (reading & (1 << 1)) > 0;
              boolean rightStatus = (reading & (1 << 0)) > 0;
              CheesyVisionServer.this.curLeftStatus_ = leftStatus;
              CheesyVisionServer.this.curRightStatus_ = rightStatus;
              CheesyVisionServer.this.updateCounts(leftStatus, rightStatus);
            }
            lastHeartbeat = Timer.getFPGATimestamp();
            CheesyVisionServer.this.lastHeartbeatTime_ = lastHeartbeat;
          }

          try {
            Thread.sleep(50); // sleep a bit
          } catch (InterruptedException ex) {
            System.out.println("Thread sleep failed.");
          }
        }
        is.close();
        connection.close();

      } catch (IOException e) {
      }
    }
  }

  // run() to implement Runnable
  // This method listens for incoming connections and spawns new
  // VisionServerConnectionHandlers to handle them
  public void run() {
    ServerSocket s = null;
    try {
      s = new ServerSocket(listenPort_);
      while (listening_) {
        Socket connection = (Socket) s.accept();
        Thread t = new Thread(new CheesyVisionServer.VisionServerConnectionHandler(connection));
        t.start();
        connections_.addElement(connection);
        try {
          Thread.sleep(100);
        } catch (InterruptedException ex) {
          System.out.println("Thread sleep failed.");
        }
      }
    } catch (IOException e) {
      System.out.println("Socket failure.");
      e.printStackTrace();
    }
  }
}
