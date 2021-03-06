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
import edu.wpi.first.wpilibj.command.Command;
import org.frc5122.Robot;
/**
 *
 */
public class  WaitForIR extends Command {
    private double delay;
    private double start;
    public WaitForIR(double delay) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.delay = delay;
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        start = timeSinceInitialized();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("IR: "+Robot.iR.Seen());
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.iR.Seen()) {
            return (timeSinceInitialized()-start) > delay;
        }
        else
        {
            return true;
        }
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
