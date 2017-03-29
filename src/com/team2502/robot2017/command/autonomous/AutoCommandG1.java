package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.FlywheelCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
        /**
         * For testing 
         */
        addSequential(new EncDriveDistanceCommand());       
    }
}
