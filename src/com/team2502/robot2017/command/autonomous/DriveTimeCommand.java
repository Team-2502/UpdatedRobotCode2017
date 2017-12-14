package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 *  Use encoders instead.
 *  @deprecated
 */
public class DriveTimeCommand extends Command
{
    final DriveTrainSubsystem dt;
    double speedLeft = 0.5;
    double speedRight = -0.5;

    public DriveTimeCommand(double runtime)
    {
        super(runtime);
        requires(Robot.DRIVE_TRAIN);
        dt = Robot.DRIVE_TRAIN;

    }

    public DriveTimeCommand(double runtime, double speed)
    {
        this(runtime, speed, -speed);
    }

    public DriveTimeCommand(double runtime, double speedLeft, double speedRight)
    {
        this(runtime);
        this.speedLeft = speedLeft;
        this.speedRight = speedRight;

    }

    @Override
    protected void execute()
    {
        dt.runMotors(speedLeft, speedRight);
    }

    @Override
    protected boolean isFinished()
    {
        return isTimedOut();
    }

    @Override
    protected void end() { dt.stopDriveS(); }
}