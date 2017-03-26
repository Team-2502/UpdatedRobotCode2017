package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@SuppressWarnings("WeakerAccess")
public class DriveTimeBackwards extends Command
{
    private DriveTrainSubsystem driveTrain;
    private long runTime;
    private long startTime;

    /**
     * @param runTime Time to run for in milliseconds.
     */
    public DriveTimeBackwards(long runTime)
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        this.runTime = runTime;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public DriveTimeBackwards(double runTime)
    {
        this((long) (runTime * 1000));
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {
        driveTrain.runMotors(-.65D, .65D);
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