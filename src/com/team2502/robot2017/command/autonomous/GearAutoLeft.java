package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoLeft extends CommandGroup
{
    /**
     * Gets the left gear in autonomous 
     */
	public GearAutoLeft() 
	{
		addSequential(new DriveTimeCommand(1, .85));//this is for distance of 114.3 in
		addSequential(new NavXMoveCommand(62, 1.25, .4));
		addSequential(new AutoVCommand(5, false, -.2, .3));
//      addSequential(new DriveTimeCommand(.55, -.2));
        addSequential(new NavXMoveCommand(10, 1, .2));
        addSequential(new WaitCommand(.5));
        addSequential(new NavXMoveCommand(-10, 1, .3));
//      addSequential(new NavXMoveCommand(10, 1, .2));
        addSequential(new DriveTimeCommand(.5, .2));
	}
}