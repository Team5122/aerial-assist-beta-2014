package org.frc5122.commands;

import org.frc5122.Robot;
import org.frc5122.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

import org.frc5122.Robot;
/**
 *
 */
public class Turn extends Command {

	// instance variables
	private double degrees = 0;
	private double speed = 0;
	
	private double leftValue = 0;
	private double rightValue = 0;
	private boolean turnRight = true;
	
	private double startingAngle = 0;
	
	//true is right (clockwise)
	//degrees must be positive on the range 1 to 360
    public Turn(double degrees, double speed, boolean turnRight) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.drivetrain);
         this.degrees = degrees;
         this.speed = speed;
         this.turnRight = turnRight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startingAngle = RobotMap.gyro.getAngle();
//    	if (degrees == 0) {
//    		leftValue = speed;
//    		rightValue = speed;
//    	} else 
    	if (turnRight){
    		leftValue  = speed;
    		rightValue = -speed;
    	} else {
    		leftValue  = -speed;
    		rightValue = speed;
    	}
    	Robot.drivetrain.TankDrive(leftValue, rightValue);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.TankDrive(leftValue, rightValue);
    	System.out.println(RobotMap.gyro.getAngle()+" "+ (startingAngle+degrees));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (RobotMap.gyro.getAngle() >= (startingAngle+degrees));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
