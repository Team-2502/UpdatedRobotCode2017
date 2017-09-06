package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.trajectory.Trajectory;
import com.team2502.robot2017.trajectory.TrajectoryCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Created by ritik on 9/2/17.
 */
public class TrajectoryTest extends CommandGroup
{
    public TrajectoryTest() { addSequential(new TrajectoryCommand("Straight", false)); }
}
