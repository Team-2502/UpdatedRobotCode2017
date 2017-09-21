package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.AutoVCommand;
import com.team2502.robot2017.command.autonomous.EncoderDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoCenter extends CommandGroup
{
    /**
     * Gets the center gear in autonomous
     */
    public GearAutoCenter()
    {
        addSequential(new EncoderDrive(50, 1));
        System.out.println("Running Vision [Gear Auto Center]");
        addSequential(new AutoVCommand(5, false, -.1, .3));
    }
}