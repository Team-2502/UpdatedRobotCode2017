package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;

public class GearAutoRight extends CommandGroup
{
    /**
     * Gets the right gear in autonomous 
     */
	public GearAutoRight() 
	{
		addSequential(new EncoderDrive(83, 2));
		addSequential(new NavXMoveCommand(-60, 2));
		System.out.println("Running Vision [Gear Auto Right]");
		addSequential(new AutoVCommand(3, false, -0.2, 0.3));
		addSequential(new Wiggle());

	}
}