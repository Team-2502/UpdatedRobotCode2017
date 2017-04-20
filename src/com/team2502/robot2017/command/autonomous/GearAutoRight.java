package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GearAutoRight extends CommandGroup
{
    /**
     * Gets the right gear in autonomous 
     */
	public GearAutoRight() 
	{
	       addSequential(new DriveTimeCommand(1.35, .86));//this is for distance of 114.3 in
//	       addSequential(new NavXMoveCommand(62, 1.25, .4, false));
		   addSequential(new NavXPIDTurn(62             ));
		   addSequential(new AutoVisionCommand(5, -2/.3));
	}
}