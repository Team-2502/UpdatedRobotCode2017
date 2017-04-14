package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

import com.team2502.robot2017.command.*;

public class ClimberCommandG extends CommandGroup
{
    public static ClimberCommand ClimberCommand;
    public ClimberCommandG()
    {
        addSequential(new StopDriveCommand());
        addSequential(new WaitCommand(0.1));
        addSequential(new ClimberCommand());
    }
}