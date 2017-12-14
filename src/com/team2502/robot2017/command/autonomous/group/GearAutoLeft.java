package com.team2502.robot2017.command.autonomous.group;

import com.team2502.robot2017.command.autonomous.AutoVisionCommand;
import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import com.team2502.robot2017.command.autonomous.NavXRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoLeft extends CommandGroup
{
    /**
     * Gets the left gear in autonomous
     */
    public GearAutoLeft()
    {
        addSequential(new EncoderDriveCommand(80, 1.7));
        addSequential(new NavXRotateCommand(60, 1));
        System.out.println("Running Vision [Gear Auto Left]");
        addSequential(new AutoVisionCommand(3, false, -0.2, 0.3));
        addSequential(new NavXRotateCommand(-5, 0.5));
        addSequential(new NavXRotateCommand(10, 0.5));
    }
}