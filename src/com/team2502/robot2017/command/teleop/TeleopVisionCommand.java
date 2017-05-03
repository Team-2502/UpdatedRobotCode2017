package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopVisionCommand extends Command
{
	VisionSubsystem vision;
	DriveTrainSubsystem dt;
	boolean isTriggerPressed;
	boolean visonMode;

	public TeleopVisionCommand()
	{
		requires(Robot.VISION);
		requires(Robot.DRIVE_TRAIN);

		vision = Robot.VISION;
		dt = Robot.DRIVE_TRAIN;
	}

	@Override
	protected void execute()
	{
		if(OI.JOYSTICK_DRIVE_LEFT.getRawButton(RobotMap.Joystick.Button.VISION_ALIGN) && !isTriggerPressed) { visonMode = !visonMode; }
		isTriggerPressed = OI.JOYSTICK_DRIVE_LEFT.getRawButton(RobotMap.Joystick.Button.VISION_ALIGN);
		if(visonMode)
		{
			vision.align(dt, -0.2, 0.3, false, false);
		}
	}

	@Override
	protected boolean isFinished() { return false; }


	@Override
	protected void end() { dt.stopDriveS(); }

	@Override
	protected void interrupted() { end(); }

}