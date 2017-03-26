package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.NavXMoveCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionAutoCommandGroup extends CommandGroup {

	public VisionAutoCommandGroup() {
		addSequential(new DriveTimeCommand(1D));
		addSequential(new AutoVCommand());
	}
	
}
