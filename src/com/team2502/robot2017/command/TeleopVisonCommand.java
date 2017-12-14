package com.team2502.robot2017.command;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class TeleopVisonCommand extends Command
{
    public static DriveTrainSubsystem driveTrainSubsystem;
    public double offset;
    public VisionSubsystem vision;
    boolean alignOnly = false;
    double highSpeed = 0.3;
    double turningFactor = 0.5;

    protected void smoothSpeed(double offset)
    {
        highSpeed = getSpeed(offset);
        if (offset > 0.15) { driveTrainSubsystem.runMotors(highSpeed, highSpeed * turningFactor); } else if (offset < 0.15)
        {
            driveTrainSubsystem.runMotors(-turningFactor * highSpeed, -highSpeed);
        } else if ((-0.15 < offset) && (offset < 0.15) && !alignOnly) { driveTrainSubsystem.runMotors(.5D, -.5D); }
    }

    @Override
    protected void execute()
    {
        offset = vision.getOffset();
        smoothSpeed(offset);
    }

    @Override
    protected boolean isFinished()
    {
        // TODO Auto-generated method stub
        return false;
    }

    private double getSpeed(double x)
    {
        return (-2 / (1 + (Math.pow(x, 2) / 1600)) + 2);
    }
}
