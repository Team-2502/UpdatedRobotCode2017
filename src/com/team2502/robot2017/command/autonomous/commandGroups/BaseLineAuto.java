package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.EncoderDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BaseLineAuto extends CommandGroup
{
    public BaseLineAuto()
    {
        addSequential(new EncoderDrive(200, 2));
    }
}
