package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.ClimberDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem
{
    public boolean isBraked = false;
    public final CANTalon climberTop;
    public final CANTalon climberBottom;
	
    /**
     * Subsystem for climber brake
     */
    public ClimberSubsystem()
    {
    	climberTop = new CANTalon(RobotMap.Motor.CLIMBER_TOP);
    	climberBottom = new CANTalon(RobotMap.Motor.CLIMBER_BOTTOM);
    }

    /**
     * Set the init default command
     */
    @Override
    protected void initDefaultCommand() { setDefaultCommand(new ClimberDrive()); }

	public void drive() {
		if(OI.JOYSTICK_FUNCTION.getRawButton(RobotMap.Joystick.Button.CLIMBER_TOP))
		{
			climberTop.set(1);
			climberBottom.set(1);
		}
		else
		{
			climberTop.set(0);
		}
		
		
		
	}

	public void stop() {
		climberTop.set(0);
		climberBottom.set(0);
		
	}
}