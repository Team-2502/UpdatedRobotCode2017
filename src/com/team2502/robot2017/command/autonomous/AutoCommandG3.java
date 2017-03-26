package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG3 extends CommandGroup
{
    public AutoCommandG3()
    {
        addSequential(new DriveTimeCommand(3D));
    }
}
    