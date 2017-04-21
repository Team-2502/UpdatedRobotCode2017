package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import com.team2502.robot2017.command.autonomous.*;

public class GearAutoLeft extends CommandGroup
{
    /**
     * Gets the left gear in autonomous 
     */
	public GearAutoLeft() 
	{
		addSequential(new DriveTimeCommand(1, .85));//this is for distance of 114.3 in
		addSequential(new NavXMoveCommand(62, 1.25, .4, false));
		addSequential(new AutoVCommand(5, false, -.2, .3));
	}
}