package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

@SuppressWarnings("WeakerAccess")
public class DriveTimeCommand extends CommandGroup
{
	public DriveTimeCommand(double runTime)
	{
		addSequential(new NavXMoveCommand(0, runTime, 0.65, false));		
	}
	
	public DriveTimeCommand(double runTime, double speed)
	{
		addSequential(new NavXMoveCommand(0, runTime, speed, false));		
	}
	
}