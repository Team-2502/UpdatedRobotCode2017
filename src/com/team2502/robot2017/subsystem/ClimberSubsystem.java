package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;

import com.team2502.robot2017.command.teleop.ClimberDriveCommand;
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
    protected void initDefaultCommand() { setDefaultCommand(new ClimberDriveCommand()); }

	public void drive()
	{
		double speed = Math.abs(OI.JOYSTICK_FUNCTION.getY());

		if(speed <= 0.1D) { speed = 0; }

		runMotors(speed);
	}

	public void runMotors(double speed)
	{
		climberTop.set(speed);
		climberBottom.set(speed);
	}

	public void stop()
	{
		climberTop.set(0);
		climberBottom.set(0);
		
	}
	
	public enum ClimberMode
	{
		STOP(0),
		CLIMB(1);
		
		public int val;
		
		ClimberMode(int val) { this.val = val; }
	}
}