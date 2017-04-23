package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PIDController;

import logger.Log;

public class EncoderDrive extends Command
{
	private double targetRotLeft = -4.65;
	private double targetRotRight = 4.65;

	private boolean onTarget = false;
	private long onTargetStartTime = 0;

	private double revLeftL;
	private double revLeftR;

	private DriveTrainSubsystem dt;
	
	private EncoderDrive()
	{
		super(5);
		dt = Robot.DRIVE_TRAIN;
		requires(Robot.DRIVE_TRAIN);
	}
	
	public EncoderDrive(double inches) { this(inches, inches); }
	
	public EncoderDrive(double inchesLeft, double inchesRight)
	{	
		this();

		targetRotLeft = inchesLeft / (4 * Math.PI);
		targetRotRight = inchesRight / (4 * Math.PI);
	}
	
	@Override
	protected void initialize()
	{
		dt.setAutonSettings();
	}
	
	@Override
	protected void execute()
	{
		revLeftL = dt.leftTalon0.getClosedLoopError();
		revLeftR = dt.rightTalon1.getClosedLoopError();

		SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Left", revLeftL);
		SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Right", revLeftR);

		if (!onTarget && (revLeftL <= 0.01 && revLeftR <= 0.01))
		{
			onTargetStartTime = System.currentTimeMillis();
		}
		else if(onTarget && (revLeftL <= 0.01 && revLeftR <= 0.01))
		{
			onTargetStartTime = 0;
		}


		dt.rightTalon1.set(-targetRotRight);
		dt.leftTalon0.set(targetRotLeft);
	}

	@Override
	protected boolean isFinished() 
	{

		return (Math.abs(revLeftR) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR
				&& Math.abs(revLeftL) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR)
				&& (System.currentTimeMillis() - onTargetStartTime >= RobotMap.Motor.TIME_TO_STOP);

	}
	
	@Override
	protected void end()
	{
		dt.setTeleopSettings();
		System.out.println("Exiting PID");
		dt.stop();
	}
	
	@Override
	protected void interrupted() { end(); }
}