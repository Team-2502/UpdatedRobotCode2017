package com.team2502.robot2017.command.autonomous.group;

import com.team2502.robot2017.command.autonomous.AutoVisionCommand;
import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoCenter extends CommandGroup
{
    /**
     * Gets the center gear in autonomous
     */
    public GearAutoCenter()
    {
        addSequential(new EncoderDriveCommand(50, 1));
        System.out.println("Running Vision [Gear Auto Center]");
        addSequential(new AutoVisionCommand(5, false, -.1, .3));
    }
}