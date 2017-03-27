package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoLeft extends CommandGroup {

	public GearAutoLeft() 
	{
		addSequential(new EncDriveDistanceCommand());
//		addSequential(new WaitCommand(1));
//		addSequential(new NavXMoveCommand(60, 4));
//		addSequential(new AutoVCommand(2));
	}
	
}
