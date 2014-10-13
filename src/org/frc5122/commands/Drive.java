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
public class  Drive extends Command {
    double duration, starttime, move, rotate;
    public Drive(double duration, double move, double rotate) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.drivetrain);
        this.duration = duration;
        this.move = move;
        this.rotate = rotate;
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drivetrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        starttime = timeSinceInitialized();
        Robot.drivetrain.ArcadeDrive(-move, rotate);
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (timeSinceInitialized()-starttime) > duration;
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
