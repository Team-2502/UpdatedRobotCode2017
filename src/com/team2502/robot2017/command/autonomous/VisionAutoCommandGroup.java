package com.team2502.robot2017.command.autonomous;




import edu.wpi.first.wpilibj.command.CommandGroup;

public class VisionAutoCommandGroup extends CommandGroup {

	public VisionAutoCommandGroup() {
//		addSequential(new DriveTimeCommand(1D));
		addSequential(new AutoVCommand(10));
	}
	
}
