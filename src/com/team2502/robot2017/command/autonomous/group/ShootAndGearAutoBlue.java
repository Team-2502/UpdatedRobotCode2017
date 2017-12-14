package com.team2502.robot2017.command.autonomous.group;

import com.team2502.robot2017.command.autonomous.AutoVisionCommand;
import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import com.team2502.robot2017.command.autonomous.NavXRotateCommand;
import com.team2502.robot2017.command.autonomous.WiggleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndGearAutoBlue extends CommandGroup
{
    public ShootAndGearAutoBlue()
    {
        addSequential(new ShootAutoBase("blue"));

        //Forward
        addSequential(new EncoderDriveCommand(53.254, 2));

        // Point at gear peg
        addSequential(new NavXRotateCommand(60, 2));

        addSequential(new AutoVisionCommand(4, false, -0.2, 0.3));
        addSequential(new WiggleCommand());
    }
}