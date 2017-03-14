package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.NavXMoveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG3 extends CommandGroup
{
    public AutoCommandG3()
    {
        addSequential(new DriveTimeCommand(3D));
    }
}
    