package org.frc5122.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_RoboRodeo extends CommandGroup {

	public Auto_RoboRodeo() {
		addSequential(new Debug("Starting RoboRodeo Dance"));
		                //Drive(double duration, double move, double rotate)
		
		double speed = .83;
		double turnspeed = .9;
		double turn = 250;
		
		addSequential(new Drive(1, speed, 0));
		addSequential(new Debug("Starting Turn"));
		addSequential(new Turn(turn, turnspeed, true));
		for (int i =0; i<2; i++) {
			addSequential(new Drive(.9, speed, 0));
			addSequential(new Debug("Starting Turn"));
			addSequential(new Turn(turn, turnspeed, true));
		}
		addSequential(new Drive(.85, speed, 0));
		addSequential(new Debug("Starting Turn"));
		addSequential(new Turn(turn-45, turnspeed, true));
		//go to middle
		addParallel(new PopBall());
		addSequential(new Drive(.85/2, speed, 0));
		addSequential(new Turn(360*10, 1, true));
	}
}
