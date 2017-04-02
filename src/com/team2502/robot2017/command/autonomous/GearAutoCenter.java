package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoCenter extends CommandGroup {

	public GearAutoCenter() {
		addSequential(new DriveTimeCommand(1D, .65));
		addSequential(new AutoVCommand(7));
	}
	
}
