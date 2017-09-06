package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderDrive extends Command
{
    private double targetRotLeft = -4.65;
    private double targetRotRight = 4.65;

    private boolean onTarget = false;
    private long onTargetStartTime = 0;

    private double revLeftL;
    private double revLeftR;

    private DriveTrainSubsystem dt;

    private EncoderDrive(double time)
    {
        super(time);
        dt = Robot.DRIVE_TRAIN;
        requires(Robot.DRIVE_TRAIN);
    }

    public EncoderDrive(double inches, double maxtime) { this(inches, inches, maxtime); }

    private EncoderDrive(double inchesLeft, double inchesRight, double time)
    {
        this(time);

        targetRotLeft = inchesLeft / (4 * Math.PI);
        targetRotRight = inchesRight / (4 * Math.PI);
    }

    @Override
    protected void initialize()
    {
        dt.setAutonSettings();
    }

    @Override
    protected void execute()
    {
        revLeftL = dt.leftTalon0.getClosedLoopError();
        revLeftR = dt.rightTalon1.getClosedLoopError();

        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Left", revLeftL);
        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Right", revLeftR);

        if(!onTarget && (Math.abs(revLeftL) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR && Math.abs(revLeftR) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR))
        {
            onTargetStartTime = System.currentTimeMillis();
        }
        else if(onTarget && (Math.abs(revLeftL) >= RobotMap.Motor.ALLOWABLE_LOOP_ERR && Math.abs(revLeftR) >= RobotMap.Motor.ALLOWABLE_LOOP_ERR))
        {
            onTargetStartTime = 0;
        }


        dt.rightTalon1.set(-targetRotRight);
        dt.leftTalon0.set(targetRotLeft);
    }

    @Override
    protected boolean isFinished()
    {
        if(isTimedOut())
        {
            return true;
        }
        else
        {
            return (Math.abs(revLeftR) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR
                    && Math.abs(revLeftL) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR)
                    && (System.currentTimeMillis() - onTargetStartTime >= RobotMap.Motor.TIME_TO_STOP);
        }

    }

    @Override
    protected void end()
    {
        dt.setTeleopSettings();
        System.out.println("Exiting PID");
        dt.stop();
    }

    @Override
    protected void interrupted() { end(); }
}