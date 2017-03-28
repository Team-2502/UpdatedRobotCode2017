package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;



@SuppressWarnings("WeakerAccess")
public class AutoVCommand extends Command
{
    public static DriveTrainSubsystem dt;
    public double offset;
    public double leftSpeed;
    public double rightSpeed;
    public boolean inFrontOfGear = false;
    public boolean Reverse = false;
    public VisionSubsystem vision = Robot.VISION;
    double deadRight = 1;
    double deadLeft = -1;
    double startTime = System.currentTimeMillis();
    double targetElapsed = 15;
    boolean alignOnly = false;
    
    public AutoVCommand(double runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        dt = Robot.DRIVE_TRAIN;
        targetElapsed = runTime*1000;

    }
    
    public AutoVCommand(double runTime, boolean align)
    {
        this(runTime);
        alignOnly = align;

    }

    @Override
    protected void interrupted() { end(); }
    
    @Override
    protected void initialize() 
    {
    	startTime = System.currentTimeMillis();
    }
    
    double slowspeed = 0.2;
    @Override
    protected void execute()
    {
    	
        offset = vision.getOffset();
        if(offset > 0)
        {
        	dt.runMotors(0.3D, 0.3/2);
//        	dt.runMotors(slowspeed, 0);
        }
        else if(offset < 0)
        {
        	dt.runMotors(-0/3/2, -0.3D);
//        	dt.runMotors(0, -slowspeed);
        }
        else if((offset == 0) && !alignOnly)
        {
        	dt.runMotors(.5D, -.5D);
//        	dt.runMotors(slowspeed, -slowspeed);
        }
    
        

    }

    @Override
    protected boolean isFinished()
    {
    	if(System.currentTimeMillis() - startTime > targetElapsed){
    		if(alignOnly){
            	return Math.abs(offset) < 0;
            }
            else{
            	return true;
            }
    	}
    	else{
    		return false;
    	}
    }

    protected void end() { dt.stop(); }
    
    
}



