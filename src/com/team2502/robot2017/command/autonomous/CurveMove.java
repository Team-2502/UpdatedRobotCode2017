package com.team2502.robot2017.command.autonomous;


import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Mky135 on 9/2/17.
 */
public class CurveMove extends Command
{
    private double curve;

    private DriveTrainSubsystem dt;

    public CurveMove(double time, double curve)
    {
        super(time);
        dt = Robot.DRIVE_TRAIN;
        requires(Robot.DRIVE_TRAIN);

        this.curve = curve;
    }

    @Override
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        dt.runMotors(.7, .5/curve);
    }

    @Override
    protected boolean isFinished()
    {
        return isTimedOut();
    }
}