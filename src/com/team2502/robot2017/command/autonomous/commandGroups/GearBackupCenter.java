package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Gets the center gear in autonomous
 */
public class GearBackupCenter extends CommandGroup
{
    public GearBackupCenter()
    {
        addSequential(new EncoderDriveCommand(114.5 - 30, 114.5 - 30, 10));
    }
}
