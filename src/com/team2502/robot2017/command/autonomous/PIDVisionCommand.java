package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.VisionSubsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Vision alignment with PID
 */
public class PIDVisionCommand extends Command implements PIDOutput
{
    private VisionSubsystem vision;
    private DriveTrainSubsystem dt;
    private double turnRate;
    private PIDController alignController;
    private long alignedTime;
    private boolean onTarget = false;

    public PIDVisionCommand(double maxTime)
    {
        super(maxTime);
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.VISION);

        vision = Robot.VISION;
        dt = Robot.DRIVE_TRAIN;

        alignController = new PIDController(0.03, 0.00025, 0, 0, vision, this, 1000 / 7);

        alignController.setInputRange(-40.0f, 40.0f);
        alignController.setOutputRange(-1.0, 1.0);
        alignController.setAbsoluteTolerance(0.5);
        alignController.setContinuous(true);
        alignController.disable();  // just in case

    }

    @Override
    protected void initialize()
    {
        if (!alignController.isEnabled())
        {
            alignController.setSetpoint(0);
            alignController.enable();
        }

        if (alignController.onTarget() && !onTarget)
        {
            onTarget = true;
            alignedTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void execute()
    {
        dt.runMotors(turnRate, turnRate);
    }

    @Override
    protected boolean isFinished()
    {
        return isTimedOut() || (onTarget && System.currentTimeMillis() - alignedTime >= 500);
    }

    @Override
    protected void end()
    {
        dt.stopDriveS();
    }

    @Override
    protected void interrupted()
    {
        end();
    }

    @Override
    public void pidWrite(double output)
    {
        turnRate = output;
    }
}
