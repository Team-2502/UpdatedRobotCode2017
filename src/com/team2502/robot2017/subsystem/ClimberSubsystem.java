package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem
{
    public boolean isBraked = false;
    private final Solenoid climber;
	
    /**
     * Subsystem for climber brake
     */
    public ClimberSubsystem()
    {
        this.climber = new Solenoid(RobotMap.Solenoid.CLIMBER_SOLENOID);
    }

    /**
     * Set the init default command
     */
    @Override
    protected void initDefaultCommand()
    {
        climber.set(true);
    }
    
    /**
     * Switch the brake from its curent position
     */
    public void switchBrake()
    {
        setBrake(isBraked = !isBraked);
    }
    
    /**
     * Set the brake to what you want it to be directly
     * 
     * @param brake A boolean that indicates the state of the pneumatic
     */
    public void setBrake(boolean brake)
    {
        climber.set(this.isBraked = brake);
    }

    
}