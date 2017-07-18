package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

//import edu.wpi.first.wpilibj.DoubleSolenoid;

public class DriveTrainTransmissionSubsystem extends Subsystem
{
    private static Solenoid switcher;

    // TODO: Change name to `lowGear` if applicable.
    public boolean highGear;

    /**
     * Initialize transmission
     */
    public DriveTrainTransmissionSubsystem()
    {
        switcher = new Solenoid(RobotMap.Solenoid.TRANSMISSION_SWITCH);
        highGear = false;
    }

    @Override
    protected void initDefaultCommand() {}

    /**
     * Switch the gear from its current state
     */
    public void switchGear() {
        setGear(highGear = !highGear);
        Robot.SHIFTED = System.currentTimeMillis();
    }
    
    /**
     * @return if we are in high gear
     */
    public boolean getGear() { return highGear; }

    /**
     * Set the transmission to a specific high gear or low gear
     * @param highGear Boolean saying "do you want to be in high gear?"
     */
    public void setGear(boolean highGear) {
        if(this.highGear != highGear){
            Robot.SHIFTED = System.currentTimeMillis();
            switcher.set(this.highGear = highGear);
        }
    }
}