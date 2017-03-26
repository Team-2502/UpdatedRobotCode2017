package com.team2502.robot2017.command;

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
	private boolean forever = false;
	public boolean done = false;;
	private double revolutions;
	private long startTime;
	private double deadZone = 2;
	private double elapsedTime;
	private double speed;
	private double revolutionsComplete;
	private double encLeft;
	private double encRight;
	
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
        forever = true;
		this.revolutions = 36/(Math.PI*4); 
	}
	
    public NavXMoveCommand(double distance) 
    {
    	// distance is distance in inches
		requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
        
        navx.reset();
        targetYaw = 0;
		this.revolutions = distance/(Math.PI*4);
	}
    
    public NavXMoveCommand(double angle, double distance)
    {
    	// distance is distance in inches
    	requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        requires(Robot.DISTANCE_SENSOR);
        Sensor = Robot.DISTANCE_SENSOR;
    	
        navx.reset();
		targetYaw = angle;
		this.revolutions = distance/(Math.PI*4);
    }

	@Override
	protected void initialize() 
	{
		startTime = System.currentTimeMillis();

        driveTrain.setAutonSettings(driveTrain.rightTalon1);
        driveTrain.setAutonSettings(driveTrain.leftTalon0);
	}

	@Override
	protected void execute() 
	{
		encLeft = driveTrain.getEncLeftPosition();
		encRight = driveTrain.getEncRightPosition();
		
		
		currentYaw = Robot.NAVX.getYaw();
		speed = getSpeed(currentYaw-targetYaw);
		SmartDashboard.putNumber("NavX: Target yaw", targetYaw);

		if(Math.abs(currentYaw - targetYaw) > deadZone)
		{	
			// right = pos
			// left = neg
			if(currentYaw > targetYaw)
			{
				driveTrain.runMotors(0, -1 * speed);
			} 
			else if(currentYaw < targetYaw)
			{
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
			return (encLeft + encRight)/2 > revolutions;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void end() {
		driveTrain.setTeleopSettings(driveTrain.rightTalon0);
        driveTrain.setTeleopSettings(driveTrain.leftTalon0);
	}

	@Override
	protected void interrupted() { end(); }
	
	protected double getSpeed(double offset) {
		if(targetYaw == 0 || Math.abs(currentYaw - targetYaw) > deadZone){
			return 0.5;
		}
		else
		{
//			return 1/(1+Math.pow(Math.E, time/2500))
			return (-5)/(6 + (Math.pow(offset, 2)/3600));
		}
	}

}
