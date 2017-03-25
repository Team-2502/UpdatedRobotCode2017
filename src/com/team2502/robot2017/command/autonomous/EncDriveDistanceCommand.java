package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.GearBoxSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class EncDriveDistanceCommand extends Command
{
    public static DriveTrainSubsystem DriveTrain;
    double targetDist;
    double currentDistL;
    double currentDistR;
    double currentDistAvg;
    
    public EncDriveDistanceCommand(double TargetDist)
    {
        requires(Robot.DRIVE_TRAIN);
        DriveTrain = Robot.DRIVE_TRAIN;
        targetDist = TargetDist;
    }
    

    @Override
    protected void initialize()
    {
        DriveTrain.setAutonSettings(DriveTrain.rightTalon0);
        DriveTrain.setAutonSettings(DriveTrain.leftTalon0);
        DriveTrain.setAutonSettings(DriveTrain.rightTalon1);
        DriveTrain.setAutonSettings(DriveTrain.leftTalon1);
        
    }

    @Override
    protected void execute()
    {   
        currentDistL = DriveTrain.getEncLeftPosition();
        currentDistR = DriveTrain.getEncRightPosition();
        currentDistAvg = ((currentDistR + currentDistL)/2);
        DriveTrain.leftTalon0.set(targetDist);
        DriveTrain.rightTalon0.set(-targetDist);
//     gearBox.setGear(on);
    }

    @Override
    protected boolean isFinished()
    {
        return currentDistAvg >= targetDist;
    }

    @Override
    protected void end()
    {
        DriveTrain.setTeleopSettings(DriveTrain.leftTalon0);
        DriveTrain.setTeleopSettings(DriveTrain.rightTalon0);
        DriveTrain.setTeleopSettings(DriveTrain.leftTalon1);
        DriveTrain.setTeleopSettings(DriveTrain.rightTalon1);
//        DriveTrain.stopDriveS();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}