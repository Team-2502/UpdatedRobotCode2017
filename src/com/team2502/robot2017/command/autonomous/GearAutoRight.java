package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearAutoRight extends CommandGroup {

	public GearAutoRight() 
	{
	    addSequential(new DriveTimeCommand(.90, 0.75));
	    addSequential(new WaitCommand(1));
	    addSequential(new NavXMoveCommand(-60, 2));
	    addSequential(new WaitCommand(1D));
	    addSequential(new AutoVCommand(2, false));
	    addSequential(new AutoVCommand(200, false));   
	}
	
}
