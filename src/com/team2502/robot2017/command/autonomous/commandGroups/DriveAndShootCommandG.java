package com.team2502.robot2017.command.autonomous.commandGroups;

//import com.team2502.robot2017.command.FlywheelCommand;
import com.team2502.robot2017.command.autonomous.DriveTimeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

@SuppressWarnings("deprecation")
public class DriveAndShootCommandG extends CommandGroup
{
    @SuppressWarnings("deprecation")
    public DriveAndShootCommandG()
    {
        addSequential(new DriveTimeCommand(1.2, .5 ));
//        addSequential(new FlywheelCommand());
    }
}