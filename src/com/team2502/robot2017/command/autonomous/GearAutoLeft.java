package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoLeft extends CommandGroup {

	public GearAutoLeft() 
	{
//		addSequential(new EncoderDrive(1));
		addSequential(new DriveTimeCommand(1, 0.75));
		addSequential(new WaitCommand(1));
		addSequential(new NavXMoveCommand(58, 2));
		addSequential(new WaitCommand(1D));
		addSequential(new AutoVCommand(2));
//		addSequential(new AutoVCommand(200, false));
	}
	
}
