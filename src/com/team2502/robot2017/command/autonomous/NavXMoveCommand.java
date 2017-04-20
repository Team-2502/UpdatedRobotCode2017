package com.team2502.robot2017.command.autonomous;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class NavXMoveCommand extends Command
{
	public double targetYaw;
	private DriveTrainSubsystem driveTrain;
	private AHRS navx;
	public double currentYaw;
	private boolean angleOnly = false;
	public boolean done = false;;
	private double runTime;
	private long startTime;
	private double deadZone = 1;
	private double elapsedTime;
	private double speed;
	private boolean ifManualSpeed;
	public double manualSpeed = 0.5;
	/**
	 * Drive in a straight line for 5 seconds according to the navx.
	 */
	public NavXMoveCommand()
    {
		requires(Robot.DRIVE_TRAIN);
	    driveTrain = Robot.DRIVE_TRAIN;
	    navx = Robot.NAVX;    
//        navx.reset();
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
//        navx.reset();
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
//	    navx.reset();
    }
    
    /**
     * Turns angle for a curtain amount of time and curtain speed
     * @param angle - turn a curtain amount
     * @param runTime - runs for a curtain amount
     * @param speed - sets curtain amount of speed
     * @param speedIsForStraightOnly - linear turning or not?
     */
    public NavXMoveCommand(double angle, double runTime, double speed, boolean speedIsForStraightOnly)
    {
        this();
        targetYaw = angle;
        this.runTime = (runTime*1000);
        ifManualSpeed = !speedIsForStraightOnly;
        manualSpeed = speed;
//        navx.reset();
    }

	@Override
	protected void initialize() 
	{
		startTime = System.currentTimeMillis();
	    navx.reset();
	}

	@Override
	protected void execute() 
	{
		elapsedTime = System.currentTimeMillis() - startTime;
		currentYaw = Robot.NAVX.getAngle();
		if(ifManualSpeed){ speed = manualSpeed;}
		else{speed = getSpeed(currentYaw - targetYaw);}
		SmartDashboard.putNumber("NavX: Target yaw", targetYaw);
		if(Math.abs(currentYaw - targetYaw) > deadZone)
		{	
			// right = pos
			// left = neg
			if(currentYaw > targetYaw) { driveTrain.runMotors(-speed, -speed); } 
			else if(currentYaw < targetYaw) { driveTrain.runMotors(speed, speed); }
		}
		else { driveTrain.runMotors(speed, -speed); }
	}

	@Override
	protected boolean isFinished()
	{
		// Will end if time elapsed while at targetYaw or at appropriate distance
		if(angleOnly) { return Math.abs(currentYaw - targetYaw) > deadZone; }
		else
		{ 
		    if(Math.abs(currentYaw - targetYaw) > deadZone)
		    {
		    return System.currentTimeMillis() - startTime > runTime;
		    }
		    else { return false; }
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
	protected double getSpeed(double x)
	{
		if(targetYaw == 0){ return manualSpeed; }
		else { return (-0.5/(1+Math.pow(x, 2)/2000))+0.5; }
	}
}