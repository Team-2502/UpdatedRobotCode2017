package com.team2502.robot2017.command.autonomous;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DistanceSensorSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXMoveCommand extends Command{

	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	private DistanceSensorSubsystem Sensor;
	public double currentYaw;
	private boolean angleOnly = false;
	public boolean done = false;
	private long startTime;
	private double deadZone = 2;
	private double targetTime;
	private double speed;
	private double passedTime;
//	private double targetXDisplace = 0;
//	private boolean displacementDrive = false;
//	private double targetYDisplace = 0;
//	private double displaceDeadzone = 0.006;
	
	public NavXMoveCommand(){
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
        navx.reset(); 
        targetYaw = 0;
        targetTime = 5000;
	}
	
    public NavXMoveCommand(double angle) 
    {
    	// distance is distance in inches
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
        navx.reset();
        targetYaw = angle;
        angleOnly = true;
		
	}
    
    public NavXMoveCommand(double angle, double time)
    {
    	// distance is distance in inches
    	requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
    	
        navx.reset();
		targetYaw = angle;
		targetTime = (time*1000);
    }

	@Override
	protected void initialize() 
	{
		startTime = System.currentTimeMillis();
        
	}

	@Override
	protected void execute() 
	{

		
		currentYaw = Robot.NAVX.getYaw();
		passedTime = System.currentTimeMillis() - startTime;
		SmartDashboard.putNumber("NavX: Target yaw", targetYaw);
		speed = getSpeed(passedTime);
		if(Math.abs(currentYaw - targetYaw) > deadZone)
		{	
			// right = pos
			// left = neg
			if(currentYaw > targetYaw)
			{
//				driveTrain.runMotors(-speed, -speed);
			    driveTrain.runMotors(0, -speed);
			} 
			else if(currentYaw < targetYaw)
			{
//				driveTrain.runMotors(speed, speed);
			    driveTrain.runMotors(speed, 0);
			}
		}
		else
		{	
				driveTrain.runMotors(0.5, -0.5);
		}	
	}	

	@Override
	protected boolean isFinished() {
		// Will end if time elapsed while at targetYaw or at appropriate distance\
		if(Math.abs(currentYaw - targetYaw) > deadZone)
		{
		    if(angleOnly)
		    {
		        return true;
		    }
		    else
		    {
		        return System.currentTimeMillis() - startTime > targetTime; // time
		    }
			
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void end() 
	{

	}

	@Override
	protected void interrupted() { end(); }
	
	protected double getSpeed(double time) {
		if(targetYaw == 0 || Math.abs(currentYaw - targetYaw) > deadZone){
			return 0.5;
		}
		else
		{
			return 1/(1+Math.pow(Math.E, time/2500));
//			return (-5)/(6 + (Math.pow(offset, 2)/3600))/4;
		}
	}

}
