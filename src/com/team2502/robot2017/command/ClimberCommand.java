package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.subsystem.ClimberSubsystem;

public class ClimberDrive extends Command
{
    private final ClimberSubsystem ClimberSubsystem;
    boolean stopped;
    
    public ClimberDrive()
    {
        requires(Robot.CLIMBER);
        ClimberSubsystem = Robot.CLIMBER;           
    }
    
    @Override
    protected void initialize() {}
    
    @Override
    protected void execute()
    {
    	ClimberSubsystem.drive();
    }

	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end()
	{
		ClimberSubsystem.stop();
	}
	
	@Override
	protected void interrupted() { end(); }
}