package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoLeft extends CommandGroup {

	public GearAutoLeft() 
	{
//		addSequential(new EncoderDrive(1));
//		addSequential(new DriveTimeCommand(1, 0.63));
//		addSequential(new NavXMoveCommand(62, 1.25));
	    addSequential(new WaitCommand(1));
		addSequential(new AutoVCommand(200, false, .1, .3));
//		addSequential(new AutoVCommand(200, false));
	}
	
}
