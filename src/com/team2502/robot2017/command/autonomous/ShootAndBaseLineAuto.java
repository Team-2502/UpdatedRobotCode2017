package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineAuto extends CommandGroup
{
    public ShootAndBaseLineAuto()
    {
//        addSequential(new NavXMoveCommand(150, 1, .5));
//        addParallel(new DriveTimeCommand(1, .3));
//        addSequential(new ShootCommand(1, false));  
//        addSequential(new ShootCommand(5, true));
        addSequential(new DriveTimeCommand(.25, -.5));
        addSequential(new NavXMoveCommand(90, 1, .5));
        addSequential(new DriveTimeCommand(1,1));
    }
}