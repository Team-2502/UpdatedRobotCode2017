package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelCommand;
import com.team2502.robot2017.command.TurnLeftCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
        addSequential(new EncDriveDistanceCommand());
        addSequential(new WaitCommand(1D));
        addSequential(new GearCommandG(true, true));
        addSequential(new WaitCommand(1D));
        addSequential(new GearCommandG(false, true));
        addSequential(new DriveTimeCommand(1D));
        

    }
}
