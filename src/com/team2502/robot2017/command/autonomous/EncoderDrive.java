package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import logger.Log;

//@Deprecated
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
		dt.setAutonSettings();
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
		return Math.abs(dt.getEncRightPosition()) >= Math.abs(targetRotRight) && Math.abs(dt.getEncLeftPosition()) >= Math.abs(targetRotLeft);
	}
	
	@Override
	protected void end()
	{
		dt.setTeleopSettings();
		dt.stop();
	}
	
	@Override
	protected void interrupted() { end(); }
}