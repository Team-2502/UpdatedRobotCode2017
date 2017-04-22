package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoCenter extends CommandGroup 
{
    /**
     * Gets the center gear in autonomous 
     */
    public GearAutoCenter() 
    {
		addSequential(new EncoderDrive(25, 25));
		addSequential(new AutoVCommand(5));
	}
}