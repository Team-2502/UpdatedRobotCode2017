package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.AutoVisionCommand;
import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import com.team2502.robot2017.command.autonomous.NavXRotateCommand;
import com.team2502.robot2017.command.autonomous.WiggleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ShootAndGearAutoRed extends CommandGroup
{
    public ShootAndGearAutoRed()
    {
        addSequential(new ShootAutoBase("red"));
        addSequential(new EncoderDriveCommand(60, 2));
        addSequential(new NavXRotateCommand(-60, 1));
        addSequential(new AutoVisionCommand(4, false, -0.2, 0.3));
        addSequential(new WiggleCommand());
    }
}