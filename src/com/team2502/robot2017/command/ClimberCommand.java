package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;
import com.team2502.robot2017.subsystem.ClimberSubsystem;
import com.team2502.robot2017.subsystem.ClimberSubsystem.ClimberMode;

public class ClimberCommand extends InstantCommand
{
    private final ClimberSubsystem climber;
    
    int mode = 0;
    
    public ClimberCommand(ClimberMode mode)
    {
        requires(Robot.CLIMBER);
        climber = Robot.CLIMBER;
        this.mode = mode.val;
    }
    
    @Override
    protected void initialize()
    {
    	climber.runMotors(mode);
    }

}