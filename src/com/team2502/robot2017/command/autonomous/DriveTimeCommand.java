package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

// Please use encoders!!!
@Deprecated
public class DriveTimeCommand extends Command
{
	double speed = 0.5;
	final DriveTrainSubsystem dt;

	public DriveTimeCommand(double runtime)
	{
		super(runtime);
		requires(Robot.DRIVE_TRAIN);
		dt = Robot.DRIVE_TRAIN;

	}

	public DriveTimeCommand(double runtime, double speed)
	{
		this(runtime);
		this.speed = speed;
	}

	@Override
	protected void execute()
	{
		dt.runMotors(speed, -speed);
	}

	@Override
	protected boolean isFinished()
	{
		return isTimedOut();
	}

	@Override
	protected void end() { dt.stopDriveS(); }
}