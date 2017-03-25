package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.subsystem.ClimberSubsystem;

public class ClimberCommand extends Command
{   
    private final ClimberSubsystem ClimberSubsystem;
    boolean stopped;
    public ClimberCommand()
    {
        requires(Robot.CLIMBER);
        ClimberSubsystem = Robot.CLIMBER;   
        
    }
    public boolean getstopped()
    {
        return stopped;
    }
    public void setStopped(boolean Stopped)
    {
        stopped = Stopped;
    }
    
    protected void initialize()
    {
      ClimberSubsystem.switchBrake();
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