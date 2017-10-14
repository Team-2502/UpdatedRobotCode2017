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
        addSequential(new NavXResetCommand());
        addSequential(new EncoderDrive(-48.413,-68.28, 8, 10, 1.35)); //First curve
        addSequential(new EncoderDrive(-(4.85*12), -(3.75*12), 10.5, 6.5, 1.32)); //Second curve to hopper .74
        addParallel(new SetHopperCommand(true)); // Expand hopper
        addSequential(new EncoderDrive(-10,10, .15));
        addSequential(new EncoderDrive(100,.4)); // Move forward to get more balls

        //left: -48.413      right: -68.28
        //left: -(5.7*12)    right: -(4.2*12)

        addSequential(new AlignBoiler());
        addParallel(new ShootCommand(.5, false));
		addSequential(new ShootCommand(10, true));
		addSequential(new SetHopperCommand(false));

    }
}