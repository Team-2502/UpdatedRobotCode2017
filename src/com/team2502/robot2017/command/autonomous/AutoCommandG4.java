package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.*;
import com.team2502.robot2017.command.autonomous.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG4 extends CommandGroup
{
    public AutoCommandG4()
    {
        addSequential(new DriveTimeCommand(3D,.7));
    }
}
    