package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class DriveTimeCommand extends Command
{
    private DriveTrainSubsystem driveTrain;
    private double runTime;
    private long startTime;
    double speed;


    /**
     * This the main way of movement
     * @param RunTime
     */
    public DriveTimeCommand(double RunTime)
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        speed = 0.65;
        this.runTime = RunTime * 1000;
        
    }
    
    /**
     * This the main way of movement
     * @param RunTime
     * @param Speed
     */
    public DriveTimeCommand(double RunTime, double Speed)
    {
    	this(RunTime);
        speed = Speed;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
 
    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {

        driveTrain.runMotors(speed, -speed);

    }

    @Override
    protected boolean isFinished()
    {
        return System.currentTimeMillis() - startTime > runTime;
    }

    @Override
    protected void end()
    {
        driveTrain.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}