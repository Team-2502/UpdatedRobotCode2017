package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.subsystem.ClimberSubsystem;

public class ClimberCommand extends Command
{   
    private final ClimberSubsystem ClimberSubsystem;
    public ClimberCommand()
    {
        requires(Robot.CLIMBER);
        ClimberSubsystem = Robot.CLIMBER;   
    }
    
    protected void initialize()
    {
      ClimberSubsystem.setBrake(false);
    }

    @Override
    protected void execute()
    {
        ClimberSubsystem.switchBrake();
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