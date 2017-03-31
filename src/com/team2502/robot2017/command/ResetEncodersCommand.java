package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

public class ResetEncodersCommand extends Command {
	private DriveTrainSubsystem dt;
	
	public ResetEncodersCommand() {
		dt = Robot.DRIVE_TRAIN;
		requires(Robot.DRIVE_TRAIN);
	}
	
	@Override
	protected void execute() {
		dt.leftTalon0.setEncPosition(0);
		dt.rightTalon1.setEncPosition(0);
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}
