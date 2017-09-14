package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Created by 64009334 on 9/2/17.
 */
public class BoilerRed extends CommandGroup
{
	public BoilerRed()
	{
		addSequential(new EncoderDrive(10, 1));
		addSequential(new NavXMoveCommand(-45,1));
		addSequential(new EncoderDrive(1,2.5));
		addSequential(new CurveMove(.75, 100));
//		addSequential(new SetHopperCommand(true));
//		addSequential(new WaitCommand(0.5));
//		addSequential(new BoilerDistCommand());
	}
}
