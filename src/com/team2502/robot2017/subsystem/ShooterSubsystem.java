package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.FeedbackDevice;
import com.team2502.robot2017.DashboardData;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.FlywheelCommand;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem.DriveTypes;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem.Pair;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem
{	
	
	

	   private double lastLeft;
	
	   public double leftSpeed;

	   public boolean negative = false;
	   public boolean isNegativePressed = false;
	   public boolean negMode = false;
	   
	   public ShooterSubsystem ShooterSubsystem;
	
    private final CANTalon flywheelTalon;
    private final CANTalon feederTalon0; //coleson
    private final CANTalon feederTalon1;  //banebot
    private final CANTalon feederTalon2; //agtator
    
    double targetSpeed = 1540;
    double autoTargetSpeed = targetSpeed + 50;
    int error = 0;

    public boolean isFlywheelActive;
    public boolean isFeederActive;
    private boolean shooterMode = false;
    private boolean isTriggerPressed = false;

    public ShooterSubsystem()
    {
    	lastLeft = 0.0D;
 
        
        flywheelTalon = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
        feederTalon0 = new CANTalon(RobotMap.Motor.FEEDER_TALON_0);
        feederTalon1 = new CANTalon(RobotMap.Motor.FEEDER_TALON_1);
        feederTalon2 = new CANTalon(RobotMap.Motor.AGITATOR);
    }

    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new FlywheelCommand());

        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        flywheelTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        flywheelTalon.configEncoderCodesPerRev(256);
        flywheelTalon.reverseSensor(false);

        flywheelTalon.configNominalOutputVoltage(0.0D, -0.0D);
        flywheelTalon.configPeakOutputVoltage(12.0D, -12.0D);

        flywheelTalon.setProfile(0);
        flywheelTalon.setF(0.21765900);
        flywheelTalon.setP(1.71312500);
        flywheelTalon.setI(0.0);
        flywheelTalon.setD(0.0);
    }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The current velocity of the flywheel.
     */
    public int getFlywheelSpeed()
    {
        return flywheelTalon.getEncVelocity();
    }

    public double getMotorOutput()
    {
        return flywheelTalon.getOutputVoltage() / flywheelTalon.getBusVoltage();
    }
    public void turnOnFlywheel()
    {
        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);
        flywheelTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        flywheelTalon.configEncoderCodesPerRev(256);
        flywheelTalon.reverseSensor(false);

        flywheelTalon.configNominalOutputVoltage(0.0D, -0.0D);
        flywheelTalon.configPeakOutputVoltage(12.0D, -12.0D);

        flywheelTalon.setProfile(0);
        flywheelTalon.setF(0.21765900);
        flywheelTalon.setP(1.71312500);
        flywheelTalon.setI(0.0);
        flywheelTalon.setD(0.0);
        
        flywheelTalon.set(1670);
    }
    public void feed()
    {
        feederTalon0.set(1);//1
        feederTalon1.set(-1);//-1
        feederTalon2.set(.75);//.75
    }

    public double getTargetSpeed()
    {
        return targetSpeed;
    }

    public int getError()
    {
        return flywheelTalon.getClosedLoopError();
    }

    public int getTopError()
    {
        int newError = getError();

        if(newError > error) { error = newError; }

        return error;
    }
    private double getSpeed()
    {	
    	double joystickLevel;
        // Get the base speed of the robot
    	joystickLevel = -OI.JOYSTICK_FUNCTION.getY();
        
        // Only increase the speed by a small amount
        double diff = joystickLevel - lastLeft;
        if(diff > 0.1D)
        {
            joystickLevel = lastLeft + 0.1D;
        }
        else if(diff < 0.1D)
        {
            joystickLevel = lastLeft - 0.1D;
        }
        lastLeft = joystickLevel;

        double out = joystickLevel;
        
		return out;
    }

    public void flywheelDrive()
    {
        /* This line initializes the flywheel talon so that the speed
		   we give it is in RPM, not a scale of -1 to 1. */
        flywheelTalon.changeControlMode(CANTalon.TalonControlMode.Speed);

        // Toggle mode for flywheel. It is bound to button 5 on the Function stick.
        if(OI.JOYSTICK_FUNCTION.getRawButton(5) && !isTriggerPressed)
        {
            shooterMode = !shooterMode;
        }
        isTriggerPressed = OI.JOYSTICK_FUNCTION.getRawButton(5);

        if(shooterMode) { flywheelTalon.set(targetSpeed); }
        else { flywheelTalon.set(0); }

        // For changing the flywheel speed.
        if(OI.JOYSTICK_DRIVE_LEFT.getRawButton(3))
        {
            targetSpeed += 10;
        }
        else if(OI.JOYSTICK_DRIVE_LEFT.getRawButton(2))
        {
            targetSpeed -= 10;
        }


        //Control for turning on/off the feeding mechanism.
        if(OI.JOYSTICK_FUNCTION.getTrigger() /*&& (Math.abs(flywheelTalon.getEncVelocity()) > Math.abs(targetSpeed - 500))*/)
        {
            feederTalon0.set(1);
            feederTalon1.set(-1);
            feederTalon2.set(.75);
        }

        else
        {
            feederTalon0.set(0);
            feederTalon1.set(0);
            feederTalon2.set(0);
        }
//        feederTalon0.set(getSpeed());
//        feederTalon1.set(-getSpeed());//-1
//        feederTalon2.set((getSpeed())*.75);//.75
    }
    
    

    public void stop()
    {
        flywheelTalon.set(0.0D);
        feederTalon0.set(0.0D);
        feederTalon1.set(0.0D);
        feederTalon2.set(0.0D);

        isFlywheelActive = false;
        isFeederActive = false;
    }
}

    
