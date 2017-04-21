package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RightGearBackupG extends CommandGroup
{
    public RightGearBackupG()
    {
        addSequential(new DriveTimeCommand(1D, .7));
        addSequential(new WaitCommand(1));
        addSequential(new NavXMoveCommand(-60, 4));
        addSequential(new AutoVCommand(2));  
    }
}
    