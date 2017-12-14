package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutoCommand extends CommandGroup
{
    /**
     * Handy autonomous command to use for testing
     */
    public TestAutoCommand()
    {
        addSequential(new NavXRotateCommand(90, 3));
    }

}   