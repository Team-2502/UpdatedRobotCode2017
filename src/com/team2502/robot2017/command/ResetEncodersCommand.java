package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ResetEncodersCommand extends InstantCommand
{
    private DriveTrainSubsystem dt;

    /**
     * Command for resetting the encoder values
     */
    public ResetEncodersCommand()
    {
        dt = Robot.DRIVE_TRAIN;
        requires(Robot.DRIVE_TRAIN);
    }

    @Override
    protected void initialize()
    {
        dt.leftTalon0.setSelectedSensorPosition(0, 0, RobotMap.Motor.INIT_TIMEOUT);
        dt.leftTalon1.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        dt.rightTalon0.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        dt.rightTalon1.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
    }

}