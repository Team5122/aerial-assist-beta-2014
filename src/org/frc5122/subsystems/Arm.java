// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc5122.Fred.subsystems;
import org.usfirst.frc5122.Fred.RobotMap;
import org.usfirst.frc5122.Fred.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 *
 */
public class Arm extends Subsystem {
	public boolean isArmDown = false;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController roller = RobotMap.armRoller;
    Solenoid deploy = RobotMap.armDeploy;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void Down()
    {
        System.out.println("Arm Down");
        RollerOn(); //turn roller on
        deploy.set(true); //activate solenoid
        //ArmToggle.armDown = true;
		this.isArmDown = true;
    }
    public void Up()
    {
        System.out.println("Arm Up");
        
        deploy.set(false); //activate solenoid
        //Timer.delay(1);
        RollerOff(); //turn roller off
        //ArmToggle.armDown = false;
		this.isArmDown = false;
    }
    public void RollerOff()
    {
        roller.set(0); //turn roller off
    }
    public void RollerOn()
    {
        roller.set(-1); //turn roller off
    }
	public void RollerOut()
	{
		roller.set(1);
	}
	}		
