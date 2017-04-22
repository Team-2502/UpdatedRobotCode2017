package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class AutoVCommand extends Command
{
    public static DriveTrainSubsystem dt;
    public static double offset;
    public double leftSpeed;
    public double rightSpeed;
    public static VisionSubsystem vision;
    double startTime = System.currentTimeMillis();
    double targetElapsed = 15;
    static boolean alignOnly = false;
	static double highSpeed = 0.3;
	static double lowSpeed = highSpeed/2;
    double turningFactor = -0.2/3;
    boolean smoothTurning = false;
    /**
     * Automatic vision-based alignment with shiny objects
     * <br>
     * Runs for 2 seconds
     */
    public AutoVCommand(){
    	this(2);
    }
    
    /**
     * Automatic vision-based alignment with shiny objects
     * 
     * @param runTime How long vision should run for
     */
    public AutoVCommand(double runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.VISION);
        
        vision = Robot.VISION;
        dt = Robot.DRIVE_TRAIN;
        targetElapsed = runTime*1000;
    }
    
    /**
     * Automatic vision-based alignment with shiny objects
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
     * Automatic vision-based alignment with shiny objects
     * <br>
     * This one is special because it uses a math function to smooth out the turning
     * @param runTime    How long vision should run for
     * @param slowFactor How much slower the slow side should go.
     */
    public AutoVCommand(double runTime, double slowFactor)
    {
    	this(runTime);
    	this.turningFactor = slowFactor;
    	smoothTurning = true;
    	if(slowFactor == 1) { alignOnly = true; }
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
    	vision.turnOnVisionLight();
    	startTime = System.currentTimeMillis();
    }

    protected void execute()
    {
    	align();
    }

    public static void align()
    {
	    offset = vision.getOffset();

	    if(offset > 0.1) { dt.runMotors(highSpeed, lowSpeed); }
	    else if(offset < 0.1) { dt.runMotors(-lowSpeed, -highSpeed); }
	    else if((-0.1 < offset) && (offset < 0.1) && !alignOnly) { dt.runMotors(.5D, -.5D); }
    }

    @Override
    protected boolean isFinished()
    {
    	if(System.currentTimeMillis() - startTime > targetElapsed)
    	{
    		if(alignOnly) { return Math.abs(offset) < 0.1; } // if aligned properly and enough time gone by
            else{ return true; } // if aligned properly
    	}
    	else { return false; } // if not enough time has gone by
    }

    protected void end()
    {
    	dt.stop();
    	vision.turnOffVisionLight();
    }
    
    private double getSpeed(double x) { return (-2/(1+(Math.pow(x, 2)/1600))+2); }
}
