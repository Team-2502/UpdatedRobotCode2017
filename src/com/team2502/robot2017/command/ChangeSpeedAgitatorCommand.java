package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ChangeSpeedAgitatorCommand extends InstantCommand
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
    protected void initialize() { shooterSubsystem.changeSpeedAgitator(checkIsAdd); }

}