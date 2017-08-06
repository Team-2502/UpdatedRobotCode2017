package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.EncoderDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineRed extends CommandGroup
{
    public ShootAndBaseLineRed()
    {
        addSequential(new ShootAutoBase("red"));
        addSequential(new EncoderDrive(80, 4));
    }
}