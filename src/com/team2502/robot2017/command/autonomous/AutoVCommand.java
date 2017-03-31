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
<<<<<<< HEAD
    
=======
    boolean alignOnly = false;
    double highSpeed = 0.3;
    double lowSpeed = highSpeed/2;
    
    /**
     * Automatic vision-based alignment with shiny objects
     * 
     * @param runTime How long vision should run for
     */
>>>>>>> develop-ritikm
    public AutoVCommand(double runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        dt = Robot.DRIVE_TRAIN;
        targetElapsed = runTime*1000;

    }
    
    /**
     * Automatic vision-based alignment with shiny objects
     * <br>
     * Wiggly Butt - the closer lowSpeed approaches highSpeed the more of a wiggle.
     *  
     * @param runTime How long vision should run for
     * @param align   if it should be in align-only mode
     */
    public AutoVCommand(double runTime, boolean align)
    {
        this(runTime);
        alignOnly = align;

    }
    
    /**
     * Automatic vision-based alignment with shiny objects.
     * <br>
     * Wiggly Butt - the closer lowSpeed approaches highSpeed the more of a wiggle.
     * 
     * @param runTime   How long vision should run for
     * @param align     if it should be in align-only mode
     * @param lowSpeed  The lower speed for turning
     * @param highSpeed The higher speed for turning
     * 
     * 
     * 
     */
    public AutoVCommand(double runTime, boolean align, double lowSpeed, double highSpeed)
    {
        this(runTime, align);
        this.lowSpeed = lowSpeed;
        this.highSpeed = highSpeed;
       
        
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
        if(offset > 0.1)
        {
<<<<<<< HEAD

        	dt.runMotors(0.325D, 0D);
=======
        	dt.runMotors(highSpeed, lowSpeed);
>>>>>>> develop-ritikm
        }
        else if(offset < 0.1)
        {
<<<<<<< HEAD
        	dt.runMotors(0, -0.325D);
=======
        	dt.runMotors(-lowSpeed, -highSpeed);
//        	dt.runMotors(0, -slowspeed);
>>>>>>> develop-ritikm
        }
        else if((offset == 0) && !alignOnly)
        {
<<<<<<< HEAD
        	dt.runMotors(.75D, -.75D);

        	
=======
        	dt.runMotors(.5D, -.5D);
//        	dt.runMotors(slowspeed, -slowspeed);
>>>>>>> develop-ritikm
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

