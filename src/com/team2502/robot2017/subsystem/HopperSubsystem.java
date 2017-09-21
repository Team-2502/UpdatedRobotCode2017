package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.team2502.robot2017.command.teleop.SwitchHopperCommand;

/**
 * Contains all the code for the Hopper
 */
public class HopperSubsystem extends Subsystem
{
    private static Solenoid switcher;

    public boolean out;

    /**
     * Initialize hopper
     */
    public HopperSubsystem()
    {
        switcher = new Solenoid(RobotMap.Solenoid.HOPPER_SOLENOID);
        out = false;
    }

    @Override
    protected void initDefaultCommand() { new SwitchHopperCommand();}

    /**
     * Switch the hopper from its current state
     */
    public void switchHopper()
    {
        out = !out;
        setHopper(out);
        System.out.println(out ? "Out" : "In");
    }

    public boolean getOut()
    {
        return out;
    }

    /**
     * Set the hopper to a specific in or out
     *
     * @param out Boolean saying "do you want to be in out/extended?"
     */
    public void setHopper(boolean out)
    {
        if(this.out != out)
        {
            switcher.set(this.out = out);
        }
    }
}