package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoBlue extends CommandGroup
{
    public ShootAndGearAutoBlue()
    {
        addSequential(new ShootCommand(2D,false));
        addSequential(new ShootCommand(5.0D, true));   
        addSequential(new NavXMoveCommand(112,1,1));
        addSequential(new DriveTimeCommand(1, 0.66));
        addSequential(new NavXMoveCommand(62, 1.25));
        addSequential(new WaitCommand(1));
        addSequential(new AutoVCommand(5, false, -.1, .3));
        addSequential(new NavXMoveCommand(10,1, .2));
        addSequential(new NavXMoveCommand(-10,1, .2));
    }
}
