package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.command.PIDCommandBase;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.PIDController;

public class TeleopVisionCommand extends PIDCommandBase
{
	private static DriveTrainSubsystem dt;
	double runTime;
	double startTime;
	double pidOutput;
	double tolerance = 0.2;
	int counter = 0;
    private VisionSubsystem vision;
	private double turnFactor = 1/2;
	private boolean alignOnly = false;
	private PIDController controller;




    private TeleopVisionCommand()
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
	public TeleopVisionCommand(boolean align)
	{
		this(1);
		if(align)
		{
			turnFactor = 1;
			alignOnly = align;
		}

	}


    /**
     * Automatic vision-based alignment with shiny objects.
     *
     * @param turnFactor How much slower the slow side should go. The closer it is to -1, the less wiggly it becomes.
     */
    public TeleopVisionCommand(double turnFactor)
    {
        this();
        this.turnFactor = turnFactor;
    }


    
    @Override
    protected void initialize() 
    {

    	vision.turnOnVisionLight();

    	if(counter == 0)
	    {
		    // Set up controller
		    controller = getController();

		    // Tell it what to expect
		    controller.setInputRange(-40, 40);

		    // Start running PID calculations
		    if (!controller.isEnabled())
		    {
			    controller.enable();
		    }
		    controller.setAbsoluteTolerance(tolerance);
	    }
	    ++counter;

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
	protected boolean isFinished() { return true; }


}
