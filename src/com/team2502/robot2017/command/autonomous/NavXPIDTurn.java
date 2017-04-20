package com.team2502.robot2017.command.autonomous;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.command.PIDCommandBase;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.PIDController;


public class NavXPIDTurn extends PIDCommandBase
{
	double targetYaw;
	double runTime;
	double startTime;
	double tolerance = 2;
	double output;
	boolean alignOnly;
	DriveTrainSubsystem driveTrainSubsystem;
	AHRS navX;
	PIDController controller;



	/**
	 * Stuff all constructors in this class need
	 */
	private NavXPIDTurn()
	{
		super(0.5, 0, 0, 0.0, Robot.NAVX);
		requires(Robot.DRIVE_TRAIN);
		driveTrainSubsystem = Robot.DRIVE_TRAIN;
		navX = Robot.NAVX;
	}

	public NavXPIDTurn(double targetYaw)
	{
		this();
		this.alignOnly = true;
		this.targetYaw = targetYaw;
	}
	/**
	 * Turn a certain amount of degrees and drive at that angle for some time
	 * @param targetYaw the certain amount of degrees
	 * @param runTime   the amount of time to drive for
	 */
	public NavXPIDTurn(double targetYaw, double runTime)
	{
		this(targetYaw);
		this.runTime = runTime;
		this.startTime = System.currentTimeMillis();
	}

	@Override
	protected void initialize()
	{
		navX.reset();

		controller = getController();

		// Tell it what to expect
		controller.setInputRange(-180, 180);

		// Start running PID calculations
		if (!controller.isEnabled()){ controller.enable(); }
		controller.setAbsoluteTolerance(tolerance);
	}

	@Override
	protected void execute()
	{
		output = getOutput();

		if(controller.onTarget())
		{
			driveTrainSubsystem.runMotors(0.5, 0.5);
		}
		else
		{
			driveTrainSubsystem.runMotors(output, output);
		}


	}
	@Override
	protected boolean isFinished()
	{
		if(controller.onTarget())
		{
			return alignOnly || isTimedOut();
		}
		else
		{
			return false;
		}

	}

	@Override
	protected boolean isTimedOut() { return System.currentTimeMillis() - startTime >= runTime;}
}
