package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class HopperSubsystem extends Subsystem
{
    public static Solenoid hopper;

    boolean out;

    /**
     * Initialize Hopper
     */
    public HopperSubsystem()
    {
        hopper = new Solenoid(RobotMap.Solenoid.HOPPER_SOLENOID);
        out = false;
    }

    protected void initDefaultCommand()
    {
    }

    /**
     * Switch the hopper from its current state
     */
    public void switchHopper()
    {
        setHopper(out = !out);
    }

    /**
     * Sets hopper to either in or out
     *
     * @param out Do you want it to be extended?
     */
    public void setHopper(boolean out)
    {
        if (this.out != out)
        {
            hopper.set(this.out = out);
        }
    }


}
