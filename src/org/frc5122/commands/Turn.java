package org.frc5122.commands;

import org.frc5122.Robot;
import org.frc5122.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {

	public double m_degrees;
    public Turn(double degrees) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        m_degrees = degrees;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Turn command start");
    	Robot.drivetrain.setSetpoint(m_degrees);
    	Robot.drivetrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getSetpoint() - Robot.drivetrain.gyro.pidGet()) < 1;
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Turn Command End");
    	Robot.drivetrain.setSetpoint(0);
    	Robot.drivetrain.Stop();
    	Robot.drivetrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	System.out.println("Turn Command Interrupted");
    	end();
    }
}
