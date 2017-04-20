package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.DriveCommand;
import com.team2502.robot2017.command.ClimberCommand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Example Implementation, Many changes needed.
 */

public class DriveTrainSubsystem extends Subsystem
{
    private static final Pair<Double, Double> SPEED_CONTAINER = new Pair<Double, Double>();
    
    public DriveTrainTransmissionSubsystem DTTS;
    public ClimberCommand ClimberCommand;
    
    public final CANTalon leftTalon0; //enc
    public final CANTalon leftTalon1;
    public final CANTalon rightTalon0;
    public final CANTalon rightTalon1; //enc
    private final RobotDrive drive;
    private double lastLeft;
    private double lastRight;
    public double leftSpeed;
    public double rightSpeed;
    public boolean negative = false;
    public boolean isNegativePressed = false;
    private boolean isClimbMode = false;
//    public boolean negMode = false;

    public int millisecondsToRunTL = 1000;
    public int millisecondsToRunTR = 1000;

    public int m = 1000;

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
//        drive.setExpiration(.3);

        
        DTTS = Robot.DRIVE_TRAIN_GEAR_SWITCH;
        
        setTeleopSettings(leftTalon0);
        setTeleopSettings(rightTalon1);
    }

    /**
     * Set the appropriate settings for autonomous
     * @param talon the talon to set the settings of
     */
    public void setAutonSettings(CANTalon talon)
    {
    	isClimbMode = false;
        talon.changeControlMode(TalonControlMode.Position);
        talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        talon.configEncoderCodesPerRev(256);
        talon.reverseSensor(false);
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0D, -12.0D);
        talon.setPID(1, 0, 0);
        talon.enableControl();
        talon.setEncPosition(0);
    }
    
    /**
     * Set a talon back to teleoperated settings 
     * @param talon the talon in question
     */
    public void setTeleopSettings(CANTalon talon)
    {
    	isClimbMode = false;
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0D, -12.0D);
        talon.changeControlMode(TalonControlMode.PercentVbus);
        talon.disableControl(); // needed if switching from auton settings
    }
    
    public void switchClimbSettings()
    {
    	if(isClimbMode)
    	{
    		isClimbMode = false;
    		setTeleopSettings(leftTalon0);
    		setTeleopSettings(leftTalon1);
    		setTeleopSettings(rightTalon0);
    		setTeleopSettings(rightTalon1);
    	}
    	else if(!isClimbMode)
    	{
    		isClimbMode = true;
    		leftTalon0.configPeakOutputVoltage(-8.0D, 8.0D);
        	rightTalon0.configPeakOutputVoltage(-8.0D, 8.0D);
        	leftTalon1.configPeakOutputVoltage(-8.0D, 8.0D);
        	rightTalon1.configPeakOutputVoltage(-8.0D, 8.0D);
    	}
    }
    
    /**
     * @param talon A talon with an encoder attached to it
     * @return the position of the encoder
     */
    public double getPostition(CANTalon talon) { return talon.getPosition(); }
    
    /**
     * @return the position of the left side of the drivetrain
     */
    public double getEncLeftPosition() { return leftTalon0.getPosition(); }
    
    /**
     * Get the RPM of a talon with an encoder on it
     * @param talon the talon in question
     * @return the RPM of the talon
     */
    public double getRPM(CANTalon talon) { return talon.getEncVelocity(); }
    
    /**
     * @return the position of the right side of the drivetrain
     */
    public double getEncRightPosition() { return rightTalon0.getPosition(); }
    
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
     * @param isLeftSide Whether or not it is the left joystick/side
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
        
        if(counter % 100 == 0)
        {
               System.out.println("joystickLevel: \t" + joystickLevel);
               System.out.println("out.left: \t\t" + out.left);
               System.out.println("out.right: \t\t" + out.right);
               System.out.println("diff: " + diff);
        }
        ++counter;
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