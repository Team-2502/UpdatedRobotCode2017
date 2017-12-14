package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BoilerRed extends CommandGroup
{
    public BoilerRed()
    {
        double alignspeed = 0.4;
        addSequential(new NavXResetCommand());
        addSequential(new EncoderDrive(58.732, 44.848, 11));
        addSequential(new EncoderDrive(10.5, .1));
        addSequential(new EncoderDrive(34.7, 54.3, 7));
        addSequential(new EncoderDrive(-7, -7, 9));

        addSequential(new SetHopperCommand(true));
        addParallel(new ActiveIntakeCommand(5));

        addSequential(new NavXMoveCommand(0, 1));

        addSequential(new AutoVCommand(2, true, alignspeed, alignspeed));

        addSequential(new BoilerDistCommand(2));

        addSequential(new ShootCommand(.5, false));
        addSequential(new ShootCommand(10, true));

    }
}