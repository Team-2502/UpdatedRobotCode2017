package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class EncDriveBackwardsCommand extends Command
{
    public static DriveTrainSubsystem driveTrain;
    public EncDriveBackwardsCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
    }
    
    double targetDist = 4.65;
    double currentDist;
    @Override
    protected void initialize()
    {
        driveTrain.setAutonSettings(driveTrain.rightTalon0);
        driveTrain.setAutonSettings(driveTrain.leftTalon0);
    }

    @Override
    protected void execute()
    {   
        currentDist = driveTrain.getEncLeftPosition();
        driveTrain.leftTalon0.set(targetDist/2);
        driveTrain.rightTalon0.set(-targetDist/2);
//     gearBox.setGear(on);
    }

    @Override
    protected boolean isFinished()
    {
        return currentDist <= -2.2;
    }

    @Override
    protected void end()
    {
//        DriveTrain.setTeleopSettings(DriveTrain.leftTalon0);
//        DriveTrain.setTeleopSettings(DriveTrain.rightTalon0);
//        DriveTrain.stopDriveS();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}