package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.AutoVisionCommand;
import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import com.team2502.robot2017.command.autonomous.NavXRotateCommand;
import com.team2502.robot2017.command.autonomous.WiggleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoRight extends CommandGroup
{
    /**
     * Gets the right gear in autonomous
     */
    public GearAutoRight()
    {
        addSequential(new EncoderDriveCommand(83, 2));
        addSequential(new NavXRotateCommand(-60, 2));
        System.out.println("Running Vision [Gear Auto Right]");
        addSequential(new AutoVisionCommand(3, false, -0.2, 0.3));
        addSequential(new WiggleCommand());

    }
}