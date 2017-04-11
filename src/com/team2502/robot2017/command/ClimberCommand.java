package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import com.team2502.robot2017.subsystem.ClimberSubsystem;

public class ClimberCommand extends InstantCommand
{
    private final ClimberSubsystem ClimberSubsystem;
    boolean stopped;
    
    public ClimberCommand()
    {
        requires(Robot.CLIMBER);
        ClimberSubsystem = Robot.CLIMBER;           
    }
    
    @Override
    protected void initialize() { ClimberSubsystem.switchBrake(); }
}