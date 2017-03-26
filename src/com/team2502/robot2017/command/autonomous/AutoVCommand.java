package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import logger.Log;


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
    public AutoVCommand()
    {
    	requires(Robot.DRIVE_TRAIN);
    	dt = Robot.DRIVE_TRAIN;
       
    }

    @Override
    protected void interrupted() { end(); }

    @Override
    protected void execute()
    {
        offset = vision.getOffset();
        if(offset > 0)
        {
        	dt.runMotors(0.2D, 0D);
        }
        else if(offset < 0)
        {
        	dt.runMotors(0, -0.2D);
        }
        else if(offset == 0)
        {
        	dt.runMotors(0.5D, -0.5D);
        }
    
        

    }

    @Override
    protected boolean isFinished()
    {
//        return (System.currentTimeMillis() - startTime > targetElapsed);
    	return false;
    }

    protected void end() { dt.stop(); }
    
    @Override
    protected void initialize() {}
}



