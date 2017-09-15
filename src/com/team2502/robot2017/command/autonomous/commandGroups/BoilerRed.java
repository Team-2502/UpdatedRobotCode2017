package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Created by Miguel on 9/2/17.
 */
public class BoilerRed extends CommandGroup
{
    public BoilerRed()
    {
        addSequential(new EncoderDrive(150, 1.7));
        addSequential(new NavXMoveCommand(-40,1));
        addSequential(new EncoderDrive(40, 1));
        addSequential(new NavXMoveCommand(30,1));
        addSequential(new EncoderDrive(-30, 1));
		addSequential(new SetHopperCommand(true));
//		addSequential(new WaitCommand(0.5));
//        addSequential(new AutoVCommand(2, true, 0.21, 0.21));
//		addSequential(new BoilerDistCommand());

    }
}