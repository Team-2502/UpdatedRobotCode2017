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
		addSequential(new EncoderDrive(80, 1.7));
		addSequential(new NavXMoveCommand(60, 1));
		System.out.println("Running Vision [Gear Auto Left]");
		addSequential(new AutoVCommand(3, false, -0.2, 0.3));
		addSequential(new NavXMoveCommand(-5, 0.5));
		addSequential(new NavXMoveCommand(10, 0.5));
	}
}