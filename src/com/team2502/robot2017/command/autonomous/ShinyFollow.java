package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShinyFollow extends CommandGroup
{   
    /**
     * Does a follow  
     */
    public ShinyFollow()
    {
        addSequential(new AutoVCommand(200, false, 0, 0.3));
    }
}
    