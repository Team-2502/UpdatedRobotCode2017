package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class FlywheelCommand extends Command
{
    private final ShooterSubsystem shooterSubsystem;

    public FlywheelCommand()
    {
        requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;
    }

    @Override
    protected void initialize()
    {
        shooterSubsystem.isFlywheelActive = false;
        shooterSubsystem.isFeederActive = false;
//        shooterSubsystem.defaultShooter();
    }

    @Override
    protected void execute() { shooterSubsystem.flywheelDrive(); }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { shooterSubsystem.stop(); }

    @Override
    protected void interrupted() { end(); }
}