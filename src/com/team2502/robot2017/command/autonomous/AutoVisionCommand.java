package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.command.PIDCommandBase;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.PIDController;

public class AutoVisionCommand extends PIDCommandBase
{
	private static DriveTrainSubsystem dt;
	double runTime;
	double startTime;
	double pidOutput;
	double tolerance = 0.2;
    private VisionSubsystem vision;
	private double turnFactor = 1/2;
	private boolean alignOnly = false;
	private PIDController controller;



    private AutoVisionCommand()
    {
	    super(0.5, 0, 0, Robot.VISION, 1000/6); // The 1000/6 is in the units "milliseconds per frame". Trust me on this one.

	    requires(Robot.DRIVE_TRAIN);
	    requires(Robot.VISION);

	    vision = Robot.VISION;
	    dt = Robot.DRIVE_TRAIN;

    }
	/**
	 * Automatic vision-based alignment with shiny objects
	 *
	 * @param align Align only?
	 */
	public AutoVisionCommand(boolean align)
	{
		this(1);
		if(align)
		{
			turnFactor = 1;
			alignOnly = align;
		}

	}

    /**
     * Automatic vision-based alignment with shiny objects
     * 
     * @param runTime How long vision should run for
     */
    public AutoVisionCommand(double runTime)
    {
        this();
        this.runTime = runTime;
        this.startTime = System.currentTimeMillis();
    }
    
    /**
     * Automatic vision-based alignment with shiny objects.
     * 
     * @param runTime    How long vision should run for
     * @param turnFactor How much slower the slow side should go. The closer it is to -1, the less wiggly it becomes.
     */
    public AutoVisionCommand(double runTime, double turnFactor)
    {
        this(runTime);
        this.turnFactor = turnFactor;
    }


    
    @Override
    protected void initialize() 
    {
    	vision.turnOnVisionLight();

    	// Set up controller
    	controller = getController();

    	// Tell it what to expect
	    controller.setInputRange(-40, 40);

	    // Start running PID calculations
	    if (!controller.isEnabled()){ controller.enable(); }
	    controller.setAbsoluteTolerance(tolerance);
    }

    @Override
    protected void execute()
    {
    	pidOutput = getOutput();

    	// Drive

	    if (pidOutput < -tolerance)
	    {
		    // turn left
		    dt.runMotors(pidOutput * turnFactor, pidOutput);

	    } else if (pidOutput > tolerance)
	    {
		    // turn right
		    dt.runMotors(pidOutput, pidOutput * turnFactor);
	    } else if (-tolerance < pidOutput && pidOutput < tolerance)
	    {
		    dt.runMotors(0.3, -0.3);
	    }
    }

    @Override
    protected boolean isFinished()
    {

    	if(controller.onTarget()) { return isTimedOut() || alignOnly; } // aligned and if supposed to drive forward
    	else { return false; } // not aligned
    }

    protected void end()
    {
    	dt.stop();
    	vision.turnOffVisionLight();
    }

	@Override
	protected void interrupted() { end(); }

	@Override
	protected boolean isTimedOut() { return System.currentTimeMillis() - startTime >= runTime;}

}
