package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.Command;

// TODO: Add proper implementation
public class ChangeSpeedAgitatorCommand extends Command
{
    private final ShooterSubsystem shooterSubsystem;
    
    private boolean checkIsAdd;

    public ChangeSpeedAgitatorCommand(boolean isAdd)
    {
        requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;
        
        checkIsAdd = isAdd;
    }

    @Override
    protected void initialize() { }

    @Override
    protected void execute() { shooterSubsystem.changeSpeedAgitator(checkIsAdd);}

    @Override
    protected boolean isFinished() { return true; }

    @Override
    protected void end() { }

    @Override
    protected void interrupted() { }
}

