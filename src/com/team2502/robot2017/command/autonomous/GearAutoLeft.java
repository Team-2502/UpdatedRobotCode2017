package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoLeft extends CommandGroup {

	public GearAutoLeft() {
		addSequential(new DriveTimeCommand(1.35D, .5)); // fine tune this and try to use encoders if possible
		addSequential(new NavXMoveCommand(-45, 3));
		addSequential(new AutoVCommand(10));
	}
	
}
