package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem
{
    public boolean isBraked = false;
    private final Solenoid climber;
	
    public ClimberSubsystem()
    {
        this.climber = new Solenoid(RobotMap.Solenoid.CLIMBER_SOLENOID);
    }

    @Override
    protected void initDefaultCommand()
    {
        climber.set(true);
    }
    public void switchBrake()
    {
        setBrake(isBraked = !isBraked);
    }
    
    public void setBrake(boolean brake)
    {
        climber.set(this.isBraked = brake);
    }   
}