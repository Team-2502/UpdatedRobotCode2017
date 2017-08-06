package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.EncoderDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndBaseLineBlue extends CommandGroup
{
    public ShootAndBaseLineBlue()
    {
        addSequential(new ShootAutoBase("blue"));
        addSequential(new EncoderDrive(80, 2));
    }
}
