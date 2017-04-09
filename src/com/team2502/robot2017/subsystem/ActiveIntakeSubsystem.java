package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ActiveCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ActiveIntakeSubsystem extends Subsystem
{
    private CANTalon activeTalon;
    /**
     * Constructor for the active intake subsystem
     */
    public ActiveIntakeSubsystem() { activeTalon = new CANTalon(RobotMap.Motor.ACTIVE_INTAKE); }

    /**
     * Set default command
     */
    @Override
    protected void initDefaultCommand() { this.setDefaultCommand(new ActiveCommand()); }

    /**
     * Buttons for active intake
     */
    public void activeDrive()
    {
    	//TODO: Do buttons the proper way
        if(OI.JOYSTICK_FUNCTION.getRawButton(3)) { activeTalon.set(1); }
        else if(OI.JOYSTICK_FUNCTION.getRawButton(4)) { activeTalon.set(-1); }
        else { activeTalon.set(0); }
    }

    /**
     * Kill active intake subsystem
     */
    public void stop() { activeTalon.set(0); }
}