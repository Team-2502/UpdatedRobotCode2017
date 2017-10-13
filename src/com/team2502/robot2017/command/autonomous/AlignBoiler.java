package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AlignBoiler extends CommandGroup
{
    public AlignBoiler()
    {
        addSequential(new AutoVCommand(4, true));
        addSequential(new BoilerDistCommand(1));
    }
}
