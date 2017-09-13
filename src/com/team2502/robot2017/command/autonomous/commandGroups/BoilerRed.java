package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.EncoderDrive;
import com.team2502.robot2017.command.autonomous.NavXMoveCommand;
import com.team2502.robot2017.command.autonomous.SetHopperCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class BoilerRed extends CommandGroup
{
    public BoilerRed()
    {
        addSequential(new EncoderDrive(12, 3));
        addSequential(new NavXMoveCommand(-45, 3));
        addSequential(new EncoderDrive(12, 3));
        addParallel(new NavXMoveCommand(-100, 3));
        addSequential(new SetHopperCommand(true));
//		addSequential(new WaitCommand(0.5));
//		addSequential(new BoilerDistCommand());
    }
}
