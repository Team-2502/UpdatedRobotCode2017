package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.GearBoxSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class EncDriveDistanceCommand extends Command
{
    public static DriveTrainSubsystem DriveTrain;
    public EncDriveDistanceCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        DriveTrain = Robot.DRIVE_TRAIN;
    }
    
    double targetDist = 4.65;
    double currentDist;
    @Override
    protected void initialize()
    {
        DriveTrain.setAutoSettings(DriveTrain.rightTalon0);
        DriveTrain.setAutoSettings(DriveTrain.leftTalon0);
    }

    @Override
    protected void execute()
    {   
        currentDist = DriveTrain.getEncLeftPosition();
        DriveTrain.leftTalon0.set(-targetDist);
        DriveTrain.rightTalon0.set(targetDist);
//     gearBox.setGear(on);
    }

    @Override
    protected boolean isFinished()
    {
        return currentDist <= -4.4;
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