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
	DriveTrainSubsystem dt;
	VisionSubsystem camera;
	double error;
	double target = RobotMap.Vision.TARGET_HEIGHT;
	double tolerance;
	double base_speed = 0.5;
	boolean done;
	double speed;
	double onTarget;

	public BoilerDistCommand(double speed)
	{
		this.speed = speed;
		camera = Robot.VISION;
		dt = Robot.DRIVE_TRAIN;
		target = RobotMap.Vision.TARGET_HEIGHT;
		tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
		requires(camera);
		requires(dt);
		done = false;
	}

	@Override
	protected void execute()
	{
//		double kp = 0.5;

		error = (target - camera.getHeight());
		if (error <= tolerance)
		{
			onTarget = System.currentTimeMillis();
			dt.runMotors(0,0);
		}
		else if (error > 0)
		{
			onTarget = 0;
			dt.runMotors(speed, -speed);
		} else if (error < 0)
		{
			onTarget = 0;
			dt.runMotors(-speed, speed);
		}

	}

	@Override
	protected boolean isFinished()
	{
		return onTarget != 0 && System.currentTimeMillis() - onTarget >= 1000 && error <= tolerance;
	}
}
