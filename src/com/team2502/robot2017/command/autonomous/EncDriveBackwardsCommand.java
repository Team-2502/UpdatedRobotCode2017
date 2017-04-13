package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import edu.wpi.first.wpilibj.command.Command;

@Deprecated
public class EncDriveBackwardsCommand extends Command
{
    public static DriveTrainSubsystem driveTrain;
    double targetDist = 4.65;
    double currentDist;
    public EncDriveBackwardsCommand(double TargetDist)
    {
        requires(Robot.DRIVE_TRAIN);
        driveTrain = Robot.DRIVE_TRAIN;
        targetDist = TargetDist;
    }

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
    protected boolean isFinished() { return currentDist <= targetDist; }

    @Override
    protected void end()
    {
        driveTrain.setTeleopSettings(driveTrain.leftTalon0);
        driveTrain.setTeleopSettings(driveTrain.rightTalon0);
    }

    @Override
    protected void interrupted() { end(); }
}