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


    public DriveTimeCommand(double RunTime, double Speed)
    {
        /**
         * @param RunTime Time to run for in milliseconds.
         * @param Speed RobotSpeed
         * @return
         */
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        speed = Speed;
        this.runTime = RunTime * 1000;
        
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