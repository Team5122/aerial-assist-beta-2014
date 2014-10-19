package org.frc5122.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_RoboRodeo_PID extends CommandGroup {

	public Auto_RoboRodeo_PID() {
		addSequential(new Debug("Starting RoboRodeo Dance"));
		                //Drive(double duration, double move, double rotate)
		
		double speed = .83;
		double turnspeed = .9;
		double turn = 250;
		double kP = .3;
		
		addSequential(new Drive(1.15, speed, 0));
		addSequential(new Debug("Starting Turn"));
		addSequential(new Turn_PID(turn, turnspeed, kP));
		for (int i =0; i<2; i++) {
			addSequential(new Drive(.9, speed, 0));
			addSequential(new Debug("Starting Turn"));
			addSequential(new Turn_PID(turn, turnspeed, kP));
		}
		addSequential(new Drive(.85, speed, 0));
		addSequential(new Debug("Starting Turn"));
		addSequential(new Turn_PID(turn-45, turnspeed, kP));
		//go to middle
		addParallel(new PopBall());
		addSequential(new Drive(.85/2, speed, 0));
		addSequential(new Turn_PID(360*5, 1, kP));
	}
}
