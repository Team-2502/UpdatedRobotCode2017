package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class EncDriveTurn extends Command
{
    public static DriveTrainSubsystem DriveTrain;
    
    int leftDirection;
    int rightDirection;
    
    double rotateDist = 3.1;
    
    public EncDriveTurn(int leftDir, int rightDir)
    {
        leftDirection = leftDir;
        rightDirection = rightDir;
        requires(Robot.DRIVE_TRAIN);
        DriveTrain = Robot.DRIVE_TRAIN;
    }
    
    double currentDist;
    @Override
    protected void initialize()
    {
        DriveTrain.setAutonSettings(DriveTrain.rightTalon0);
        DriveTrain.setAutonSettings(DriveTrain.leftTalon0);
    }

    @Override
    protected void execute()
    {  
        currentDist = DriveTrain.getEncLeftPosition();
        DriveTrain.leftTalon0.set(-rotateDist*leftDirection);
        DriveTrain.rightTalon0.set(rotateDist*rightDirection);
//     gearBox.setGear(on);
    }

    @Override
    protected boolean isFinished()
    {
        return false;
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