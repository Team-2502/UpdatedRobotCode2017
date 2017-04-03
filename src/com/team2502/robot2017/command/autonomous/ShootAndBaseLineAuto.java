package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineAuto extends CommandGroup
{
    public ShootAndBaseLineAuto()
    {

    	addSequential(new ShootAutoBase("blue"));
    	
        addSequential(new DriveTimeCommand(1, 1));

    }
}
