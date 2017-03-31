package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoCenter extends CommandGroup 
{
    /**
     * Gets the center gear in autonomous 
     */
    public GearAutoCenter() 
    {
		addSequential(new DriveTimeCommand(1D, .65));
		addSequential(new AutoVCommand(5, false, -.1, .3));
//		addSequential(new DriveTimeCommand(.55, -.2));
		addSequential(new NavXMoveCommand(10, 1, .2));
		addSequential(new WaitCommand(1));
        addSequential(new NavXMoveCommand(-10, 1, .3));
//        addSequential(new NavXMoveCommand(10, 1, .2));
        addSequential(new DriveTimeCommand(.5, .2));
	}
	
}
