package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.BoilerDistCommand;
import com.team2502.robot2017.command.autonomous.SetHopperCommand;
import com.team2502.robot2017.trajectory.TrajectoryCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Created by 64009334 on 9/2/17.
 */
public class BoilerRed extends CommandGroup
{
	public BoilerRed()
	{
		addSequential(new TrajectoryCommand("HopperTrajectory", true));
		addSequential(new SetHopperCommand(true));
//		addSequential(new BoilerDistCommand());
	}
}
