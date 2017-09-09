package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainTransmissionSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class SwitchDriveTransmissionCommand extends InstantCommand
{
    private final DriveTrainTransmissionSubsystem driveTrainTransmissionSubsystem;

    public SwitchDriveTransmissionCommand()
    {
        requires(Robot.DRIVE_TRAIN_GEAR_SWITCH);
        driveTrainTransmissionSubsystem = Robot.DRIVE_TRAIN_GEAR_SWITCH;
    }

    @Override
    protected void initialize()
    {
        driveTrainTransmissionSubsystem.switchGear();
    }
}