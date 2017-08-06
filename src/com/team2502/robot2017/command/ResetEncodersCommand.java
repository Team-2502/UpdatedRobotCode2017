package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
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
        dt.leftTalon0.setEncPosition(0);
        dt.leftTalon1.setEncPosition(0);
        dt.rightTalon0.setEncPosition(0);
        dt.rightTalon1.setEncPosition(0);
    }

}