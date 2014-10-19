package org.frc5122.commands;

import org.frc5122.Robot;
import org.frc5122.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.frc5122.Robot;
import org.frc5122.subsystems.Drivetrain;
/**
 *
 */
public class Turn_PID extends Command {

	// instance variables
	double desired_deg = 0;
	double kP = 0;//set by constructor
	double error = 0;
	double THRESH = 5;
	double startingAngle = 0;
	

    public Turn_PID(double degrees, double speed, double kP) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.drivetrain);
         this.desired_deg = degrees;
         this.kP = kP;
         System.out.println("TURN_PID DAMNIT! kp:"+kP+" deg:"+degrees+" speed:"+speed);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startingAngle = RobotMap.gyro.getAngle();
    	System.out.println("TURN_PID STARTING THIS SHIT");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("TURN_PID ECXECUTE THEM");
    	double actual = RobotMap.gyro.getAngle() - startingAngle;
    	error = desired_deg - actual;
    	double rightSpeed = error * kP;
    	System.out.println("act "+actual+" err"+error+" rs"+rightSpeed);
    	double leftSpeed = rightSpeed;
    	if (desired_deg < 0)
    		leftSpeed = -leftSpeed;
    	else if (desired_deg > 0)
    		rightSpeed = -rightSpeed;
    	System.out.println("ls: "+leftSpeed+" Rs: "+rightSpeed);
    	Robot.drivetrain.TankDrive(leftSpeed, rightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("TURN_PID DONE? "+(error <= THRESH)+" err:"+error);
    	return error <= THRESH;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("TURN_PID END!");
    	Robot.drivetrain.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("TURN_PID INTERRUPTED!");
    	end();
    }
}
