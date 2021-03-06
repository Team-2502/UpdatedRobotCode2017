package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class BoilerDistCommand extends Command
{
    DriveTrainSubsystem driveTrainSubsystem;
    VisionSubsystem camera;
    double error;
    double target;
    double tolerance;
    boolean done;

    public BoilerDistCommand(double timeout)
    {
        super(timeout);
        camera = Robot.VISION;
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        target = RobotMap.Vision.TARGET_HEIGHT;
        tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
        requires(camera);
        requires(driveTrainSubsystem);
        done = false;
    }

    public BoilerDistCommand()
    {
        camera = Robot.VISION;
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        target = RobotMap.Vision.TARGET_HEIGHT;
        tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
        requires(camera);
        requires(driveTrainSubsystem);
        done = false;
    }

    @Override
    protected void initialize()
    {
        driveTrainSubsystem.setTeleopSettings();
    }

    @Override
    protected void execute()
    {
        error = (target - camera.getHeight());
        double speed = 0.1;
        if (error > tolerance)
        {
            if (error > 0)
            {
                driveTrainSubsystem.runMotors(speed, -speed);
            } else if (error < 0)
            {
                driveTrainSubsystem.runMotors(-speed, speed);
            }
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