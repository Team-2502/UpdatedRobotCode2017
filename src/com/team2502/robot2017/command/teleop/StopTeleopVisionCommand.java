package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class StopTeleopVisionCommand extends InstantCommand
{
	DriveTrainSubsystem dt;
	VisionSubsystem vision;

	public StopTeleopVisionCommand()
	{
		requires(Robot.DRIVE_TRAIN);
		requires(Robot.VISION);

		dt = Robot.DRIVE_TRAIN;
		vision = Robot.VISION;
	}
	@Override
	protected void initialize()
	{
		dt.stop();
		vision.turnOffVisionLight();
	}
}
