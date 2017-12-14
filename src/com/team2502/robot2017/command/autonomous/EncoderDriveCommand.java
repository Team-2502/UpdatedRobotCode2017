package com.team2502.robot2017.command.autonomous;

import com.ctre.CANTalon;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * EncoderDriveCommand is a command for longitudinal (translational forward or backward) movement of the robot.
 */
public class EncoderDriveCommand extends Command
{
    CANTalon encTalonLeft;
    CANTalon encTalonRight;
    private double targetRevolutionsLeft = -RobotMap.Constants.REVOLUTIONS_TO_AIRSHIP;
    private double targetRevolutionsRight = RobotMap.Constants.REVOLUTIONS_TO_AIRSHIP;
    private double revLeftL;
    private double revLeftR;

    private DriveTrainSubsystem driveTrainSubsystem;

    /**
     *
     * @param timeout the timeout in seconds for the command
     */
    private EncoderDriveCommand(double timeout)
    {
        super(timeout);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        requires(Robot.DRIVE_TRAIN);
        encTalonLeft = driveTrainSubsystem.leftTalon0;
        encTalonRight = driveTrainSubsystem.rightTalon1;
    }

    public EncoderDriveCommand(double inches, double maxtime) { this(inches, inches, maxtime); }

    public EncoderDriveCommand(double inchesLeft, double inchesRight, double time)
    {
        this(time);

        targetRevolutionsLeft = inchesLeft / (RobotMap.Constants.WHEEL_DIAMETER * Math.PI);
        targetRevolutionsRight = inchesRight / (RobotMap.Constants.WHEEL_DIAMETER * Math.PI);
    }

    @Override
    protected void initialize()
    {
        driveTrainSubsystem.setAutonSettings();
        encTalonLeft.setAllowableClosedLoopErr(RobotMap.Motor.ALLOWABLE_LOOP_ERR);
        encTalonRight.setAllowableClosedLoopErr(RobotMap.Motor.ALLOWABLE_LOOP_ERR);
    }

    @Override
    protected void execute()
    {
        revLeftL = Math.abs(encTalonLeft.getClosedLoopError());
        revLeftR = Math.abs(encTalonRight.getClosedLoopError());

        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Left", revLeftL);
        SmartDashboard.putNumber("DT: Autonomous encoder ticks needed Right", revLeftR);

        encTalonRight.set(-targetRevolutionsRight);
        encTalonLeft.set(targetRevolutionsLeft);
    }

    @Override
    protected boolean isFinished()
    {
        {
            return isTimedOut();
        }
    }

    @Override
    protected void end()
    {
        driveTrainSubsystem.setTeleopSettings();
        driveTrainSubsystem.runMotors(0, 0);
    }

    @Override
    protected void interrupted() { end(); }
}