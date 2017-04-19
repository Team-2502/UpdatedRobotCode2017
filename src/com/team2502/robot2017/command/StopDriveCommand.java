package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;


@Deprecated
public class StopDriveCommand extends InstantCommand
{   
    private final DriveTrainSubsystem DriveTrain;
    public StopDriveCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        DriveTrain = Robot.DRIVE_TRAIN;   
    }
    
    protected void initialize() { DriveTrain.stopDriveS(); }

}