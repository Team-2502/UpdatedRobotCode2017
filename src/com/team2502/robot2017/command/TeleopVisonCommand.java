package com.team2502.robot2017.command;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class TeleopVisonCommand extends Command
{
    public static DriveTrainSubsystem dt;
    public double offset;
    public double leftSpeed;
    public double rightSpeed;
    public boolean inFrontOfGear = false;
    public boolean reverse = false;
    public VisionSubsystem vision;
    double startTime = System.currentTimeMillis();
    double targetElapsed = 15;
    boolean alignOnly = false;
    double highSpeed = 0.3;
    double lowSpeed = highSpeed / 2;
    double turningFactor = 0.5;
    boolean smoothTurning = false;

    @Override
    protected void initialize()
    {
        vision.turnOnVisionLight();
    }

    protected void smoothSpeed(double offset)
    {
        highSpeed = getSpeed(offset);
        if (offset > 0.15){ dt.runMotors(highSpeed, highSpeed * turningFactor); } 
        else if (offset < 0.15){ dt.runMotors(-turningFactor * highSpeed, -highSpeed); } 
        else if ((-0.15 < offset) && (offset < 0.15) && !alignOnly){  dt.runMotors(.5D, -.5D); }
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
