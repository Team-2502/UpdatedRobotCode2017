package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbModeCommand extends Command
{
	DriveTrainSubsystem dt;
	
	public ClimbModeCommand()
	{
		requires(Robot.DRIVE_TRAIN);
		dt = Robot.DRIVE_TRAIN;	
	}
	
	@Override
	protected void execute() { dt.switchClimbSettings(); }
	
	@Override
	protected boolean isFinished() { return true; }
}