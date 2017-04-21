package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.DriveTimeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BackupGearAutoCommand extends CommandGroup
{
    public BackupGearAutoCommand()
    {

        addSequential(new DriveTimeCommand(1.35D, .7));//1.5
        addSequential(new WaitCommand(1D));
   
    }
}
