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
    double startTime = 0;
    double targetElapsed = 15;
    public AutoVCommand(double runtime)
    {
    	requires(Robot.DRIVE_TRAIN);
    	dt = Robot.DRIVE_TRAIN;
    	targetElapsed = (runtime * 1000);
    	
       
    }

    @Override
    protected void interrupted() { end(); }
    
    @Override
    protected void initialize() 
    {
    	startTime = System.currentTimeMillis();
    }
    
    @Override
    protected void execute()
    {
        offset = vision.getOffset();
        if(offset > 0.25)
        {
        	dt.runMotors(0.325D, 0D);
        }
        else if(offset < -0.25)
        {
        	dt.runMotors(0, -0.325D);
        }
        else
        {
        	dt.runMotors(.75D, -.75D);
        }
    
        

    }

    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > targetElapsed;
//    	return false;
    }

    protected void end() { dt.stop(); }
    
    
}



