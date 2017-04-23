package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;

public class GearAutoLeft extends CommandGroup
{
    /**
     * Gets the left gear in autonomous 
     */
	public GearAutoLeft() 
	{
		addSequential(new EncoderDrive(114.3));
		addSequential(new NavXMoveCommand(62));
		System.out.println("Running Vision [Gear Auto Left]");
		addSequential(new AutoVCommand(5, -.2/.3));

	}
}