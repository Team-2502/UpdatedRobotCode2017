package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.BoilerDistCommand;
import com.team2502.robot2017.command.autonomous.SetHopperCommand;
import com.team2502.robot2017.trajectory.TrajectoryCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Created by Ritik on 9/2/17.
 */
public class BoilerBlue extends CommandGroup
{
	public BoilerBlue()
	{
		addSequential(new TrajectoryCommand("HopperTrajectory", false));
		addSequential(new SetHopperCommand(true));
		addSequential(new WaitCommand(0.5));
		addSequential(new SetHopperCommand(false));
		addSequential(new BoilerDistCommand());
	}
}
