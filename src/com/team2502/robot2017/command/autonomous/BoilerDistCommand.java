package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by 64009334 on 9/2/17.
 */
public class BoilerDistCommand extends Command
{
    DriveTrainSubsystem dt;
    VisionSubsystem camera;
    double error;
    double target = RobotMap.Vision.TARGET_HEIGHT;
    double tolerance;
    boolean done;

    public BoilerDistCommand(double timeout)
    {
        super(timeout);
        camera = Robot.VISION;
        dt = Robot.DRIVE_TRAIN;
        target = RobotMap.Vision.TARGET_HEIGHT;
        tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
        requires(Robot.VISION);
        requires(Robot.DRIVE_TRAIN);
        done = false;
    }

    public BoilerDistCommand()
    {
        camera = Robot.VISION;
        dt = Robot.DRIVE_TRAIN;
        target = RobotMap.Vision.TARGET_HEIGHT;
        tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
        requires(Robot.VISION);
        requires(Robot.DRIVE_TRAIN);
        done = false;
    }

    @Override
    protected void initialize()
    {
        dt.setTeleopSettings();
    }

    @Override
    protected void execute()
    {
        error = (target - camera.getHeight());
        double speed = 0.2;
        // /Math.abs(target - camera.getHeight());
        if(Math.abs(error) > tolerance)
        {
            if(error > 0)
            {
                dt.runMotors(speed, -speed);
            } else if(error < 0)
            {
                dt.runMotors(-speed, speed);
            }
        }
        else if(Math.abs(error) < tolerance)
        {
            done = true;
        }

    }

    @Override
    protected void end()
    {
        System.out.println("Did a boilerdist");
    }

    @Override
    protected boolean isFinished()
    {
        return done || isTimedOut();
    }
}