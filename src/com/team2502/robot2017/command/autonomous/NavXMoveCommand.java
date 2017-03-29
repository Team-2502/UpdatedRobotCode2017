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
	public double currentYaw;
	private boolean angleOnly = false;
	public boolean done = false;;
	private double runTime;
	private long startTime;
	private double deadZone = 2;
	private double elapsedTime;
	private double speed;
	
	/**
	 * Drive in a straight line for 5 seconds according to the navx.
	 */
	public NavXMoveCommand()
    {
		requires(Robot.DRIVE_TRAIN);
	    driveTrain = Robot.DRIVE_TRAIN;
	    navx = Robot.NAVX;    
	    navx.reset();
	    targetYaw = 0;
	    
	    this.runTime = (long)  5000;
	}
	
	/**
	 * Turn to an angle, and immediately end once turned.
	 * 
	 * @param angle the angle to turn to.
	 */
    public NavXMoveCommand(double angle) 
    {
	    this();
        angleOnly = true;
        targetYaw = angle;
	
	  }
    /**
     * Turn to an angle, and drive on it for some time
     * @param angle   the angle to turn to
     * @param runTime the time to drive for
     */
    public NavXMoveCommand(double angle, double runTime)
    {
        this();
	    targetYaw = angle;
	    this.runTime = (runTime*1000);
    } 

	@Override
	protected void initialize() 
	{
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void execute() 
	{
		elapsedTime = System.currentTimeMillis() - startTime;
		speed = getSpeed(elapsedTime);
		currentYaw = Robot.NAVX.getAngle();
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
		// Will end if time elapsed while at targetYaw or at appropriate distance
		if(angleOnly)
		{
			return Math.abs(currentYaw - targetYaw) > deadZone;
		}
		else
		{
		if(Math.abs(currentYaw - targetYaw) > deadZone)
			{
				return System.currentTimeMillis() - startTime > runTime;
			}
		else
			{
				return false;
			}
		}
	}

	@Override
	protected void end() { driveTrain.stop(); }

	@Override
	protected void interrupted() { end(); }
	
	
	/**
	 * @param  x seconds that have passed since you started turning/
	 * @return the speed one side of the drive train should go at
	 */
	protected double getSpeed(double x) {
		if(targetYaw == 0){
			return 0.5;
		}
		else
		{
			return 1/(1+Math.pow(Math.E, x/2500));
		}
	}

}