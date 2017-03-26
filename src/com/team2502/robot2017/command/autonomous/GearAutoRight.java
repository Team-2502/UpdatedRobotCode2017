package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoRight extends CommandGroup {

	public GearAutoRight() {
		addSequential(new DriveTimeCommand(1.35D)); // fine tune this and try to use encoders if possible
		addSequential(new NavXMoveCommand(45, 3));
		addSequential(new AutoVCommand(10));
	}
	
}
