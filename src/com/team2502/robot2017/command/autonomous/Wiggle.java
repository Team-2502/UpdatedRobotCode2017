package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Created by 64009334 on 4/23/17.
 */
public class Wiggle extends CommandGroup
{
	public Wiggle()
	{
		addSequential(new NavXMoveCommand(-7.5, 0.5));
		addSequential(new NavXMoveCommand(15, 0.5));
	}
}
