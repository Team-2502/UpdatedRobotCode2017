package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
        addSequential(new DriveTimeCommand(1.35D));//1.5
        addSequential(new WaitCommand(1D));
//        addSequential(new DriveTimeCommand(1));
//        addSequential(new PushGearCommand(true));
//        addSequential(new WaitCommand(1D));
//        addSequential(new PushGearCommand(false));
//        addSequential(new DriveTimeCommand(1D));
        
    }
}
