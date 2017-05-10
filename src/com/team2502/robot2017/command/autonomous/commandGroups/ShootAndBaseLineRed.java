package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;

public class ShootAndBaseLineRed extends CommandGroup
{
    public ShootAndBaseLineRed()
    {
    	addSequential(new ShootAutoBase("red"));
        addSequential(new EncoderDrive(80, 4));
    }
}