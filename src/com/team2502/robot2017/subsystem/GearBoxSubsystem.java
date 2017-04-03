package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

// TODO: Isaac, Implement this however you want.
public class GearBoxSubsystem extends Subsystem
{
    private final Solenoid Gear;

    // States for debounce code. For moveTop Solenoid.

    private boolean GearMoved = false;
        
    public GearBoxSubsystem()
    {
        this.Gear = new Solenoid(RobotMap.Solenoid.GEARBOX_SOLENOID0);
    }

    @Override
    protected void initDefaultCommand() {}

    public void switchGear()
    {
    	setGear(GearMoved = !GearMoved);
    }
    
    public void setGear(boolean GearMoved)
    {
    	Gear.set(this.GearMoved = GearMoved);
    }
}