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
    double base_speed = 0.5;
    boolean done;

    public BoilerDistCommand()
    {
        VisionSubsystem camera = Robot.VISION;
        DriveTrainSubsystem dt = Robot.DRIVE_TRAIN;
        target = RobotMap.Vision.TARGET_HEIGHT;
        tolerance = RobotMap.Vision.HEIGHT_TOLERANCE;
        requires(camera);
        requires(dt);
        done = false;
    }

    @Override
    protected void execute()
    {
        error = (target - camera.getHeight());
        // /Math.abs(target - camera.getHeight());
        if (error <= tolerance)
        {
            done = true;
        } else if (error > 0)
        {
            dt.runMotors(1, -1);
        } else if (error < 0)
        {
            dt.runMotors(-1, 1);
        }

    }

    @Override
    protected boolean isFinished()
    {
        return done;
    }
}