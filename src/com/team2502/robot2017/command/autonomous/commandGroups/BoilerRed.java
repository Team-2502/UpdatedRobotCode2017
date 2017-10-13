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
//        addSequential(new EncoderDrive(43.27, 65.73, 5.5, 10, 500));
        double alignspeed = 0.4;
        addSequential(new NavXResetCommand());
        addSequential(new EncoderDrive(-48.413,-68.28, 8, 10, 3));
        addSequential(new EncoderDrive(-(5.7*12), -(4.2*12), 10, 6, 3));
        addSequential(new SetHopperCommand(true));

        //left: -50.413      right: -66.280

        addSequential(new NavXMoveCommand(0, 1));
        addSequential(new AlignBoiler());
		addSequential(new ShootCommand(.5, false));
		addSequential(new ShootCommand(10, true));

    }
}