package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineRed extends CommandGroup
{
    public ShootAndBaseLineRed()
    {
    	addSequential(new ShootAutoBase("red"));
        addSequential(new DriveTimeCommand(1, 1));
    }
}