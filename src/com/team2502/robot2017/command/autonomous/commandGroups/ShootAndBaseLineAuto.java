package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;

public class ShootAndBaseLineAuto extends CommandGroup
{
    public ShootAndBaseLineAuto()
    {
        addSequential(new DriveTimeCommand(.25, -.5));
        addSequential(new NavXMoveCommand(90));
        addSequential(new DriveTimeCommand(1,1));
    }
}