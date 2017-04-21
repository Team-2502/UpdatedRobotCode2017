package com.team2502.robot2017.command.autonomous.commandGroups;


import com.team2502.robot2017.command.autonomous.AutoShootCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class ShootAutoCommand extends CommandGroup
{
    public ShootAutoCommand(double time1, double time2)
    {
        addSequential(new AutoShootCommand(time1, false));
        addSequential(new AutoShootCommand(time2, true));
    }
}
    