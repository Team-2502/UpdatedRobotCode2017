package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class EncdoerTestCommand extends CommandGroup
{
    public EncdoerTestCommand()
    {
        addSequential(new EncoderDrive(1,1));
    }
}
