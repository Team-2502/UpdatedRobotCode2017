package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;

public class StopDriveCommand extends Command
{   
    private final DriveTrainSubsystem DriveTrain;
    public StopDriveCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        DriveTrain = Robot.DRIVE_TRAIN;   
    }
    
    protected void initialize()
    {
    }

    @Override
    protected void execute()
    {
        DriveTrain.stopDriveS();
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }

    @Override
    protected void end()
    {
    }

    @Override
    protected void interrupted()
    {

    }
}