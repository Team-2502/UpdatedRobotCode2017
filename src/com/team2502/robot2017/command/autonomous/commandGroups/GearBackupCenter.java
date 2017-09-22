package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.EncoderDrive;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearBackupCenter extends CommandGroup
{
    /**
     * Gets the center gear in autonomous
     */
    public GearBackupCenter()
    {
        addSequential(new EncoderDrive(114.5 - 30, 114.5 - 30, 10, 10, 3));
    }
}
