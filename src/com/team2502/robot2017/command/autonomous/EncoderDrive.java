package com.team2502.robot2017.command.autonomous;

import com.ctre.CANTalon;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class EncoderDrive extends Command
{
    private double targetRotLeft = -4.65;
    private double targetRotRight = 4.65;

    private double voltageLeft = 12;
    private double voltageRight = 12;
    private boolean onTarget = false;
    private long onTargetStartTime = 0;

    CANTalon EncTalonLeft;
    CANTalon EncTalonRight;

    private double revLeftL;
    private double revLeftR;

    private DriveTrainSubsystem dt;

    private EncoderDrive(double time)
    {
        super(time);
        dt = Robot.DRIVE_TRAIN;
        requires(Robot.DRIVE_TRAIN);
        EncTalonLeft = dt.leftTalon0;
        EncTalonRight = dt.rightTalon1;
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

    public EncoderDrive(double inchesLeft, double inchesRight, double voltageLeft, double voltageRight, double time)
    {
        this(time);

        targetRotLeft = inchesLeft / (4 * Math.PI);
        targetRotRight = inchesRight / (4 * Math.PI);

        this.voltageLeft = voltageLeft;
        this.voltageRight = voltageRight;
    }


    @Override
    protected void initialize()
    {
//        dt.setAutonSettingsVolts(EncTalonLeft, true, voltageLeft);
//        dt.setAutonSettingsVolts(EncTalonRight, false, voltageRight);
        dt.setAutonSettings();
        EncTalonLeft.setAllowableClosedLoopErr(RobotMap.Motor.ALLOWABLE_LOOP_ERR);
        EncTalonRight.setAllowableClosedLoopErr(RobotMap.Motor.ALLOWABLE_LOOP_ERR);
//        dt.leftTalon0.reverseOutput(true);
//        dt.rightTalon1.reverseOutput(true);
    }

    @Override
    protected void execute()
    {
        revLeftL = Math.abs(EncTalonLeft.getClosedLoopError());
        revLeftR = Math.abs(EncTalonRight.getClosedLoopError());


        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Left", revLeftL);
        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Right", revLeftR);

        System.out.println("Needs to go " + revLeftL);
//        System.out.println("Time: " + System.currentTimeMillis());
        System.out.println("Setpoint: " + EncTalonLeft.getSetpoint());
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


//        EncTalonRight.set(targetRotRight);
//        EncTalonLeft.set(-targetRotLeft);
        EncTalonRight.set(-targetRotRight);
        EncTalonLeft.set(targetRotLeft);
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