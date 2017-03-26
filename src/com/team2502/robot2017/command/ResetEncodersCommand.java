package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.command.Command;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

public class ResetEncodersCommand extends Command {
	private AHRS dt;
	
	public ResetEncodersCommand() {
		dt = Robot.NAVX;
	
	}
	
	@Override
	protected void execute() {
		dt.reset();
	}
	@Override
	protected boolean isFinished() {
		return true;
	}

}
