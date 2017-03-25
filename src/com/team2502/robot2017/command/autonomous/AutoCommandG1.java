package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
        addSequential(new EncDriveDistanceCommand(3.5));
        addSequential(new WaitCommand(1D));
//        addSequential(new PushGearCommand(true));
//        addSequential(new WaitCommand(1D));
//        addSequential(new PushGearCommand(false));
//        addSequential(new DriveTimeCommand(1D));
        
    }
}
