package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoRight extends CommandGroup {
    /**
     * Gets the right gear in autonomous 
     */
	public GearAutoRight() 
	{
	       addSequential(new DriveTimeCommand(1.35, .86));//this is for distance of 114.3 in
	       addSequential(new NavXMoveCommand(-62, 1.25, .4));
		   addSequential(new AutoVCommand(5, false, 0, .3));

	}
	
}
