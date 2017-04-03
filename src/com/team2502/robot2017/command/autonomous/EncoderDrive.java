package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import logger.Log;

public class EncoderDrive extends Command
{
	private double targetRotLeft = -4.65;
	private double targetRotRight = 4.65;
	
	private DriveTrainSubsystem dt;
	
	public EncoderDrive()
	{
		dt = Robot.DRIVE_TRAIN;
		requires(Robot.DRIVE_TRAIN);
	}
	
	public EncoderDrive(double revolutions) { this(revolutions, -revolutions); }
	
	public EncoderDrive(double revLeft, double revRight)
	{	
		this();
		if((revLeft > 0 && revRight > 0) || (revLeft < 0 && revRight < 0)){
			Log.warn("Warning: EncoderDrive.java will cause the robot to turn");
		}
		
		targetRotLeft = revLeft;
		targetRotRight = revRight;
	}
	
	@Override
	protected void initialize()
	{
		dt.setAutonSettings(dt.rightTalon0);
		dt.setAutonSettings(dt.rightTalon1);
		dt.setAutonSettings(dt.leftTalon0);
		dt.setAutonSettings(dt.leftTalon1);
	}
	
	@Override
	protected void execute()
	{
		dt.rightTalon1.set(targetRotRight);
		dt.leftTalon0.set(targetRotLeft);
	}

	@Override
	protected boolean isFinished() 
	{
		return Math.abs(dt.rightTalon1.getPosition()) >= Math.abs(targetRotRight) && Math.abs(dt.leftTalon0.getPosition()) >= Math.abs(targetRotLeft);
	}
	
	@Override
	protected void end()
	{
		dt.setTeleopSettings(dt.rightTalon0);
		dt.setTeleopSettings(dt.rightTalon1);
		dt.setTeleopSettings(dt.leftTalon0);
		dt.setTeleopSettings(dt.leftTalon1);
		dt.stop();
	}
	
	@Override
	protected void interrupted() { end(); }
}