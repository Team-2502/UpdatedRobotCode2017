package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BoilerBlue extends CommandGroup
{
    public BoilerBlue()
    {
        addSequential(new NavXResetCommand());
        addSequential(new EncoderDrive(-62, -42, 10, 8, 1.4)); //First curve
        addSequential(new EncoderDrive(-(3.75*12), -(4.85*12), 6.5, 10.5, 1.32)); //Second curve to hopper .74
        addParallel(new SetHopperCommand(true)); // Expand hopper
        addSequential(new EncoderDrive(10,-10, .15));
        addSequential(new EncoderDrive(100,.4)); // Move forward to get more balls

        //left: -48.413      right: -68.28
        //left: -(5.7*12)    right: -(4.2*12)

        addSequential(new AlignBoiler());
        addParallel(new ShootCommand(.5, false));
        addSequential(new ShootCommand(10, true));
        addSequential(new SetHopperCommand(false));
    }
}
