package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;



@SuppressWarnings("WeakerAccess")
public class AutoVTeleop extends Command
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
    boolean alignOnly = false;
    double highSpeed = 0.3;
    double lowSpeed = highSpeed/2;
    boolean isFinished;
    
    /**
     * Automatic vision-based alignment with shiny objects
     * 
     * @param runTime How long vision should run for
     */
    public AutoVTeleop(double lowSpeed, double highSpeed)
    {
        requires(Robot.DRIVE_TRAIN);
        dt = Robot.DRIVE_TRAIN;
        this.lowSpeed = lowSpeed;
        this.highSpeed = highSpeed;

    }
 

    @Override
    protected void interrupted() { end(); }
    
    @Override
    protected void initialize() 
    {
    }
    
    double slowspeed = 0.2;
    @Override
    protected void execute()
    {
        
        offset = vision.getOffset();
        if(offset > 0.1)
        {
            dt.runMotors(highSpeed, lowSpeed);
        }
        else if(offset < 0.1)
        {
            dt.runMotors(-lowSpeed, -highSpeed);
//          dt.runMotors(0, -slowspeed);
        }
        else if((offset == 0) && !alignOnly)
        {
            dt.runMotors(.5D, -.5D);
//          dt.runMotors(slowspeed, -slowspeed);
        }
    
        

    }

    @Override
    protected boolean isFinished()
    {
       return isFinished;
    }

    protected void end() { dt.stop(); }
    
    
}



