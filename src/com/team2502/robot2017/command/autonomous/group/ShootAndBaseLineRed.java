package com.team2502.robot2017.command.autonomous.group;

import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineRed extends CommandGroup
{
    public ShootAndBaseLineRed()
    {
        addSequential(new ShootAutoBase("red"));
        addSequential(new EncoderDriveCommand(80, 4));
    }
}