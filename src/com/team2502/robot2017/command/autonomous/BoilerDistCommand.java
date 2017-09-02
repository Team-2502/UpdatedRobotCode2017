package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by 64009334 on 9/2/17.
 */
public class BoilerDistCommand extends Command
{
	public BoilerDistCommand()
	{
		VisionSubsystem camera = Robot.VISION;
		DriveTrainSubsystem dt = Robot.DRIVE_TRAIN;
		target = RobotMap.Vision.TARGET_HEIGHT;
		tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
		requires(camera);
		requires(dt);
		error = 100;
		done = false;
	}

	DriveTrainSubsystem dt;
	VisionSubsystem camera;
	double error;
	double target;
	double tolerance;
	double base_speed = 0.5;
	boolean done;

	@Override
	protected void execute()
	{
		error = Math.abs(target - camera.getHeight());
		if (error <= tolerance)
		{
			done = true;
		} else
		{
			dt.runMotors(0.5 * error, -0.5 * error);
		}
	}

	@Override
	protected boolean isFinished()
	{
		return done;
	}
}
