package com.team2502.robot2017.subsystem;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimberSubsystem extends Subsystem
{
    public boolean isBraked = false;
    public final WPI_TalonSRX climberLeft;
    public final WPI_TalonSRX climberRight;

    /**
     * Subsystem for climber brake
     */
    public ClimberSubsystem()
    {
    	climberLeft = new WPI_TalonSRX(RobotMap.Motor.CLIMBER_LEFT);
    	climberRight = new WPI_TalonSRX(RobotMap.Motor.CLIMBER_RIGHT);
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
		climberLeft.set(-speed);
		climberRight.set(speed);
	}

	public void stop()
	{
		climberLeft.set(0);
		climberRight.set(0);
		
	}
	
	public enum ClimberMode
	{
		STOP(0),
		CLIMB(1);
		
		public int val;
		
		ClimberMode(int val) { this.val = val; }
	}
}