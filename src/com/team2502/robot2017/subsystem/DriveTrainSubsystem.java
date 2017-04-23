package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.DriveCommand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Example Implementation, Many changes needed.
 */

public class DriveTrainSubsystem extends Subsystem
{
    private static final Pair<Double, Double> SPEED_CONTAINER = new Pair<Double, Double>();


    public final CANTalon leftTalon0; //enc
    public final CANTalon leftTalon1;
    public final CANTalon rightTalon0; //enc
    public final CANTalon rightTalon1;
    private final RobotDrive drive;
    private double lastLeft;
    private double lastRight;

    private double leftSpeed;
    private double rightSpeed;
    private boolean negative = false;
    private boolean isNegativePressed = false;

	private DriveTrainTransmissionSubsystem DTTS;

    /**
     * Initialize the drive train subsystem
     */
    public DriveTrainSubsystem()
    {	
        lastLeft = 0.0D;
        lastRight = 0.0D;

        leftTalon0 = new CANTalon(RobotMap.Motor.LEFT_TALON_0);
        leftTalon1 = new CANTalon(RobotMap.Motor.LEFT_TALON_1);
        rightTalon0 = new CANTalon(RobotMap.Motor.RIGHT_TALON_0);
        rightTalon1 = new CANTalon(RobotMap.Motor.RIGHT_TALON_1); 

        drive = new RobotDrive(leftTalon0, leftTalon1, rightTalon0, rightTalon1);

        drive.setSafetyEnabled(true);

        DTTS = Robot.DRIVE_TRAIN_GEAR_SWITCH;
        
        setTeleopSettings(leftTalon0);
        setTeleopSettings(rightTalon1);
    }

	/**
	 * Set all talons into auton
	 */
	public void setAutonSettings()
    {
    	setAutonSettings(leftTalon0, false);
	    leftTalon1.changeControlMode(TalonControlMode.Follower);
//	    leftTalon1.set(RobotMap.Motor.LEFT_TALON_0);

	    setAutonSettings(rightTalon1, true);
	    rightTalon0.changeControlMode(TalonControlMode.Follower);
//	    rightTalon0.set(RobotMap.Motor.RIGHT_TALON_1);
    }


    /**
     * Set the appropriate settings for autonomous
     * @param talon the talon to set the settings of
     */
    public void setAutonSettings(CANTalon talon, boolean reverseEnc)
    {
        talon.changeControlMode(TalonControlMode.Position);
        talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        talon.configEncoderCodesPerRev(256);
        talon.reverseSensor(reverseEnc);
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0D, -12.0D);
        talon.setPID(0.5, 0.001,50);
        // increase P until
	    talon.setEncPosition(0);
	    talon.enableControl();
    }


	/**
	 * Set all talons into auton
	 */
	public void setTeleopSettings()
	{
		setTeleopSettings(leftTalon0);
		setTeleopSettings(leftTalon1);
		setTeleopSettings(rightTalon0);
		setTeleopSettings(rightTalon1);

	}

    /**
     * Set a talon back to teleoperated settings 
     * @param talon the talon in question
     */
    
    //WHAT THE HECK IS GOING ON WITH THE ENCODERS???
    public void setTeleopSettings(CANTalon talon)
    {
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0D, -12.0D);
        talon.changeControlMode(TalonControlMode.PercentVbus);
        talon.disableControl(); // needed if switching from auton settings
    }

    /**
     * @return the position of the left side of the drivetrain inches
     */
    public double getEncLeftPosition() { return leftTalon0.getPosition(); }

    /**
     * @return the position of the right side of the drivetrain in inches
     */
    public double getEncRightPosition() { return rightTalon1.getPosition() / 1024; }
    
    /**
     * @return the average position between the left and right side of the drivetrain
     */
    public double getEncAveg() { return (getEncRightPosition() + getEncLeftPosition())/2; }

    @Override
    protected void initDefaultCommand() { setDefaultCommand(new DriveCommand()); }

//    private static void debugSpeed(String format, Object... args)
//    {
//        Log.debug(String.format(format, args));
//    }

    private int logCounter = 0;

    @SuppressWarnings({ "SuspiciousNameCombination", "PointlessBooleanExpression", "ConstantConditions" })
    private Pair<Double, Double> getSpeedArcade(Pair<Double, Double> out)
    {
        // Get the base speed of the robot
        
        double yLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();
        double leftSpeed = yLevel;
        double rightSpeed = yLevel;

        double xLevel = -OI.JOYSTICK_DRIVE_RIGHT.getX();

        // Should invert the left/right to be more intuitive while driving backwards.
        if(yLevel < 0.0D) { xLevel = -xLevel;}

        if(xLevel > 0.0D) { leftSpeed -= xLevel; }
        
        else if(xLevel < 0.0D) { rightSpeed += xLevel; }

//        if(logCounter++ % 10 == 0 && false)
//        {
//            debugSpeed("X: %d&nY: %d%nL: %d%nR: %d%n%n", yLevel, xLevel, leftSpeed, rightSpeed);
//        }

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if(Math.abs(leftSpeed) < 0.05D) { leftSpeed = 0.0D; }
        
        if(Math.abs(rightSpeed) < 0.05D) { rightSpeed = 0.0D; }

        out.left = leftSpeed;
        out.right = rightSpeed;
        return out;
    }

    long counter = 0;
    
    private Pair<Double, Double> getSpeedArcade() { return getSpeedArcade(SPEED_CONTAINER); }

    /**
     * Used to gradually increase the speed of the robot.
     *
     * @param out The object to store the data in
     * @return the speed of the robot
     */
    private Pair<Double, Double> getSpeed(Pair<Double, Double> out)
    {	
    	double joystickLevel;
        // Get the base speed of the robot
        if(negative) { joystickLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY(); }
        
        else { joystickLevel = -OI.JOYSTICK_DRIVE_LEFT.getY(); }
        
        // Only increase the speed by a small amount
        double diff = joystickLevel - lastLeft;
        if(diff > 0.1D) { joystickLevel = lastLeft + 0.1D; }
        
        else if(diff < 0.1D) { joystickLevel = lastLeft - 0.1D; }
        
        lastLeft = joystickLevel;
        out.left = joystickLevel;
        
        if(negative) { joystickLevel = -OI.JOYSTICK_DRIVE_LEFT.getY(); }
        
        else { joystickLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY(); }
        
        diff = joystickLevel - lastRight;
        if(diff > 0.1D) { joystickLevel = lastRight + 0.1D; }
        
        else if(diff < 0.1D) { joystickLevel = lastRight - 0.1D; }
        
        lastRight = joystickLevel;
        out.right = joystickLevel;
        
        // Sets the speed to 0 if the speed is less than 0.05 or larger than
        // -0.05
        if(Math.abs(out.left) < 0.05D) { out.left = 0.0D; }
        
        if(Math.abs(out.right) < 0.05D) { out.right = 0.0D; }

        return out;
    }

    private Pair<Double, Double> getSpeed() { return getSpeed(SPEED_CONTAINER); }

    public void drive()
    {
        Pair<Double, Double> speed = getSpeed();

        //reverse drive
        if(OI.JOYSTICK_DRIVE_LEFT.getRawButton(1) && !isNegativePressed) { negative = !negative; }
        
        isNegativePressed = OI.JOYSTICK_DRIVE_LEFT.getRawButton(1);
        
        if (negative) { drive.tankDrive(-speed.left, -speed.right, true); }
        
        else { drive.tankDrive(speed.left, speed.right, true); }
    }

    private static final double DELAY_TIME = 5.77D + 43902.0D / 9999900.0D;

    /**
     * Drive the robot. The equation x=-y must be true for the robot to drive straight.
     * <br>
     * Make sure to set the motors according to the control mode. In auton, it's position. In teleop, it's percent voltage.
     * @param x Units for the left side of drivetrain
     * @param y Units for the right side of drivetrain
     */
    public void runMotors(double x, double y) // double z
    {
    	leftSpeed = x;
    	rightSpeed = y;
        leftTalon0.set(x);
        leftTalon1.set(x);
        rightTalon0.set(y);
        rightTalon1.set(y);
        // Timer.delay(DELAY_TIME);
        // Scheduler.getInstance().add(new WaitCommand(DELAY_TIME));
        // stopDriveS();
        //SmartDashboard.putNumber("Autonomous", Robot.AUTO.getTimerStraight());
    }

    /**
     * Stop driving by setting talons to 0
     */
    public void stopDriveS()
    {
        leftTalon0.set(0);
        leftTalon1.set(0);
        rightTalon0.set(0);
        rightTalon1.set(0);
    }

    /**
     * Stop driving
     */
    public void stop()
    {
        lastLeft = 0.0D;
        lastRight = 0.0D;
        drive.tankDrive(0.0D, 0.0D);
//        ClimberCommand.setStopped(true);
        Timer.delay(0.3D);
    }

    public enum DriveTypes { DUAL_STICK, ARCADE; }

    @SuppressWarnings("WeakerAccess")
    public static class Pair<L, R>
    {
        public L left;
        public R right;

        private String nameL;
        private String nameR;

        public Pair(L left, R right)
        {
            this.left = left;
            this.right = right;
            this.nameL = left.getClass().getSimpleName();
            this.nameR = right.getClass().getSimpleName();
        }

        public Pair() {}

        @Override
        public String toString()
        {
            return new StringBuilder(100 + nameL.length() + nameR.length()).append("Pair<").append(nameL).append(',')
                                                                           .append(nameR).append("> { \"left\": \"").append(left).append("\", \"right\": \"").append(right)
                                                                           .append("\" }").toString();
        }

        @Override
        public int hashCode() { return left.hashCode() * 13 + (right == null ? 0 : right.hashCode()); }

        @Override
        public boolean equals(Object o)
        {
            if(this == o) { return true; }
            if(o instanceof Pair)
            {
                Pair pair = (Pair) o;
                return (left != null ? left.equals(pair.left) : pair.left == null)
                       && (left != null ? left.equals(pair.left) : pair.left == null);
            }
            return false;
        }
    }
}