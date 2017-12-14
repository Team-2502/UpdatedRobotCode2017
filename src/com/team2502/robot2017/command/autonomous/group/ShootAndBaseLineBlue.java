package com.team2502.robot2017.command.autonomous.group;

import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineBlue extends CommandGroup
{
    public ShootAndBaseLineBlue()
    {
        addSequential(new ShootAutoBase("blue"));
        addSequential(new EncoderDriveCommand(80, 2));
    }
}
