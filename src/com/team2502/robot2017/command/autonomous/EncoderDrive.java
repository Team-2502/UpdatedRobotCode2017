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
//        targetRotLeft = inchesLeft;
        targetRotRight = inchesRight / (4 * Math.PI);
//        targetRotRight = inchesRight;
    }

    @Override
    protected void initialize()
    {
        dt.setAutonSettings();
        dt.leftTalon0.setAllowableClosedLoopErr(RobotMap.Motor.ALLOWABLE_LOOP_ERR);
        dt.rightTalon1.setAllowableClosedLoopErr(RobotMap.Motor.ALLOWABLE_LOOP_ERR);
//        dt.leftTalon0.reverseOutput(true);
//        dt.rightTalon1.reverseOutput(true);
    }

    @Override
    protected void execute()
    {
        revLeftL = Math.abs(dt.leftTalon0.getClosedLoopError());
        revLeftR = Math.abs(dt.rightTalon1.getClosedLoopError());



        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Left", revLeftL);
        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Right", revLeftR);

        System.out.println("Needs to go " + revLeftL);
//        System.out.println("Time: " + System.currentTimeMillis());
        System.out.println("Setpoint: " + dt.leftTalon0.getSetpoint());
//
//        if(!onTarget && (revLeftL <= RobotMap.Motor.ALLOWABLE_LOOP_ERR && revLeftR <= RobotMap.Motor.ALLOWABLE_LOOP_ERR))
//        {
//            onTargetStartTime = System.currentTimeMillis();
//            onTarget = true;
//        }
//        else if(onTarget && (revLeftL >= RobotMap.Motor.ALLOWABLE_LOOP_ERR && revLeftR >= RobotMap.Motor.ALLOWABLE_LOOP_ERR))
//        {
//            onTargetStartTime = 0;
//            onTarget = false;
//        }


        dt.rightTalon1.set(targetRotRight);
        dt.leftTalon0.set(-targetRotLeft);
    }

    @Override
    protected boolean isFinished()
    {
//        if(isTimedOut() || revLeftL <= RobotMap.Motor.ALLOWABLE_LOOP_ERR && revLeftR <= RobotMap.Motor.ALLOWABLE_LOOP_ERR)
        {
//            return true;
            return isTimedOut();
        }
//        else
//        {
//            return false;
//        }
//        else
//        {
//            return (Math.abs(revLeftR) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR
//                    && Math.abs(revLeftL) <= RobotMap.Motor.ALLOWABLE_LOOP_ERR)
//                   && (System.currentTimeMillis() - onTargetStartTime >= RobotMap.Motor.TIME_TO_STOP);
//        }

    }

    @Override
    protected void end()
    {
        dt.setTeleopSettings();
        System.out.println("Exiting PID");
//        dt.stopDriveS();
//        dt.leftTalon0.reverseOutput(false);
//        dt.rightTalon1.reverseOutput(false);
        dt.runMotors(0, 0);
    }

    @Override
    protected void interrupted() { end(); }
}