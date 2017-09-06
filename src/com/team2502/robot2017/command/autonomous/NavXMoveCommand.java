package com.team2502.robot2017.command.autonomous;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn to a certain angle with the NavX. Is also a good example of using custom PID control
 */
public class NavXMoveCommand extends Command implements PIDOutput
{
    private double targetYaw;
    private DriveTrainSubsystem driveTrain;
    private AHRS navx;
    private double turnRate = 0;
    private PIDController turnController;
    private long alignedTime;
    private boolean onTarget = false;

    /**
     * Drive in a straight line for 5 seconds according to the navx.
     */
    private NavXMoveCommand(double time)
    {
        super(time);
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;

        turnController = new PIDController(0.02, 0.000007, 0, 0, navx, this);
        //.0225 , .0002, 0
        turnController.setInputRange(-180.0f, 180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(1);
        turnController.setContinuous(true);
        turnController.disable();
    }

    /**
     * Turn to an angle, and immediately end once turned.
     *
     * @param angle the angle to turn to.
     */
    public NavXMoveCommand(double angle, double maxtime)
    {
        this(maxtime);
        targetYaw = angle;
    }


    @Override
    protected void initialize()
    {
        navx.reset();

        if(!turnController.isEnabled())
        {
            turnController.setSetpoint(targetYaw);
            turnController.enable();
        }

        if(turnController.onTarget() && !onTarget)
        {
            onTarget = true;
            alignedTime = System.currentTimeMillis();

        }
    }

    @Override
    protected void execute()
    {
        driveTrain.runMotors(turnRate, turnRate);
    }

    @Override
    protected boolean isFinished()
    {
        return isTimedOut() || (onTarget && (System.currentTimeMillis() - alignedTime) >= 1000);
    }

    @Override
    protected void end() { driveTrain.stop(); }

    @Override
    protected void interrupted() { end(); }

    @Override
    public void pidWrite(double output) { turnRate = output; }
}