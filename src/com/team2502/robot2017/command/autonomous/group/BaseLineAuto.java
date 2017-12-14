package com.team2502.robot2017.command.autonomous.group;

import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BaseLineAuto extends CommandGroup
{
    public BaseLineAuto()
    {
        addSequential(new EncoderDriveCommand(200, 2));
    }
}
