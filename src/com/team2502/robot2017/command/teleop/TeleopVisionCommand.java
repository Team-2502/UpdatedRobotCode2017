package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.command.autonomous.AutoVCommand;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class TeleopVisionCommand extends InstantCommand
{
	VisionSubsystem vision;
	DriveTrainSubsystem dt;
	double highSpeed = 0.3;
	double lowSpeed = highSpeed * -0.2/0.3;
	double offset;

	public TeleopVisionCommand()
	{
		requires(Robot.VISION);
		requires(Robot.DRIVE_TRAIN);

		vision = Robot.VISION;
		dt = Robot.DRIVE_TRAIN;
	}

	@Override
	protected void initialize()
	{
		offset = vision.getOffset();

		if(offset > 0.1) { dt.runMotors(highSpeed, lowSpeed); }
		else if(offset < 0.1) { dt.runMotors(-lowSpeed, -highSpeed); }
		else { dt.runMotors(highSpeed, -highSpeed); }

	}

	@Override
	protected void interrupted() { dt.stopDriveS(); }

}