package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineBlue extends CommandGroup
{
    public ShootAndBaseLineBlue()
    {
    	addSequential(new ShootAutoBase("blue"));
        addSequential(new DriveTimeCommand(1, 1));
    }
}
