// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.frc5122.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.frc5122.Robot;
/**
 *
 */
public class  ThrowBall extends Command {
    public double start;
    public double wait_time;
    public ThrowBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.thrower);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("Init: Throw Ball");
        wait_time = .5;
		
		if (Robot.arm.isArmDown) {
			this.wait_time = 0;
		} else
		{
			Robot.arm.Down();
		}
        start = timeSinceInitialized();
        //Timer.delay(.5);
        //Robot.thrower.Throw();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if ((timeSinceInitialized()-start) < wait_time) return; //wait then start the kicker
        Robot.thrower.Throw();
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timeSinceInitialized()-start) > wait_time+.5; //wait at least one second before finished //!Robot.thrower.Ready() && 
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.thrower.Stop();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
        System.out.println("Throw Ball Interrupted");
    }
}
