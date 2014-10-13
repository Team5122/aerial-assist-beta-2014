package org.frc5122.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_RoboRodeo extends CommandGroup {

	public Auto_RoboRodeo() {
		addSequential(new Debug("Starting RoboRodeo Dance"));
		                //Drive(double duration, double move, double rotate)
		
		addSequential(new Drive(1.15, .75, 0));
		addSequential(new Debug("Starting Turn"));
		addSequential(new Turn(260, .75, true));
		for (int i =0; i<2; i++) {
			addSequential(new Drive(.9, .75, 0));
			addSequential(new Debug("Starting Turn"));
			addSequential(new Turn(260, .75, true));
		}
		addSequential(new Drive(.85, .75, 0));
		addSequential(new Debug("Starting Turn"));
		addSequential(new Turn(260-45, .75, true));
		//go to middle
		addParallel(new PopBall());
		addSequential(new Drive(.85/2, .75, 0));
		addSequential(new Turn(360*5, .9, true));
	}
}
