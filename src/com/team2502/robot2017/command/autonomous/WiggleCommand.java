package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class WiggleCommand extends CommandGroup
{
    public WiggleCommand()
    {
        addSequential(new NavXRotateCommand(-7.5, 0.5));
        addSequential(new NavXRotateCommand(15, 0.5));
    }
}
