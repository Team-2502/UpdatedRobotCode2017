package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.DriveCommand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import logger.Log;

/**
 * Example Implementation, Many changes needed.
 */

public class DriveTrainSubsystem extends Subsystem
{
    private static final Pair<Double, Double> SPEED_CONTAINER = new Pair<Double, Double>();


    public final WPI_TalonSRX leftTalon0; //enc
    public final WPI_TalonSRX leftTalon1;
    public final WPI_TalonSRX rightTalon0;
    public final WPI_TalonSRX rightTalon1; //enc
    private final DifferentialDrive drive;
    public final SpeedControllerGroup leftDrive;
    public final SpeedControllerGroup rightDrive;
    private double lastLeft;
    private double lastRight;

    private boolean negative = false;
    private boolean isNegativePressed = false;

    // TODO: Remove if truly unnecessary.
    private double leftSpeed;
    private double rightSpeed;
    private DriveTrainTransmissionSubsystem DTTS;

    /**
     * Initialize the drive train subsystem
     */
    public DriveTrainSubsystem()
    {
        lastLeft = 0.0D;
        lastRight = 0.0D;

        leftTalon0 = new WPI_TalonSRX(RobotMap.Motor.LEFT_TALON_0);
        leftTalon1 = new WPI_TalonSRX(RobotMap.Motor.LEFT_TALON_1);
        rightTalon0 = new WPI_TalonSRX(RobotMap.Motor.RIGHT_TALON_0);
        rightTalon1 = new WPI_TalonSRX(RobotMap.Motor.RIGHT_TALON_1);

        leftDrive = new SpeedControllerGroup(leftTalon0, leftTalon1);
        rightDrive = new SpeedControllerGroup(rightTalon0, rightTalon1);

        drive = new DifferentialDrive(leftDrive, rightDrive);

        drive.setSafetyEnabled(true);
        setTeleopSettings();

        DTTS = Robot.DRIVE_TRAIN_GEAR_SWITCH;

        setTeleopSettings(leftTalon0);
        setTeleopSettings(rightTalon1);
    }

    /**
     * Set all talons into auton
     */
    public void setAutonSettings()
    {

        setAutonSettings(leftTalon0, true);
        leftTalon1.set(ControlMode.Follower, 0);
	    leftTalon1.set(RobotMap.Motor.LEFT_TALON_0);

        setAutonSettings(rightTalon1, false);
        rightTalon0.set(ControlMode.Follower, 0);
	    rightTalon0.set(RobotMap.Motor.RIGHT_TALON_1);
    }

    /**
     * Set the appropriate settings for autonomous
     *
     * @param talon the talon to set the settings of
     * @param reverseEnc // TODO: add JavaDoc For this
     */
    public void setAutonSettings(WPI_TalonSRX talon, boolean reverseEnc)
    {
        talon.set(ControlMode.Position, 0);
        talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,RobotMap.Motor.INIT_TIMEOUT);
        talon.setSensorPhase(reverseEnc);
        talon.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        talon.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        talon.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        talon.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);
        /* increase P until */
        talon.config_kP(0,2.5, RobotMap.Motor.INIT_TIMEOUT);
        talon.config_kI(0,2.5, RobotMap.Motor.INIT_TIMEOUT);
        talon.config_kD(0, 0, RobotMap.Motor.INIT_TIMEOUT); /* confirmed working -- Miguel certified */
        talon.setSelectedSensorPosition(0, 0, RobotMap.Motor.INIT_TIMEOUT);
//        talon.enableControl();

    }

    public void setAutonSettingsVolts(WPI_TalonSRX talon, boolean reverseEnc, double voltage)
    {
        setAutonSettings(talon, reverseEnc);
        voltage = voltage/12;
        talon.configPeakOutputForward(voltage, RobotMap.Motor.INIT_TIMEOUT);
        talon.configPeakOutputReverse(voltage, RobotMap.Motor.INIT_TIMEOUT);
    }

    /**
     * Set all talons into telepo
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
     *
     * @param talon the talon in question
     */

    /* What the is going on with the encoders? */
    // TODO: Learn how encoders work
    public void setTeleopSettings(WPI_TalonSRX talon)
    {
        talon.configNominalOutputForward(0.0D, RobotMap.Motor.INIT_TIMEOUT);
        talon.configNominalOutputReverse(0.0D, RobotMap.Motor.INIT_TIMEOUT);
        talon.configPeakOutputForward(1.0D, RobotMap.Motor.INIT_TIMEOUT);
        talon.configPeakOutputReverse(-1.0D, RobotMap.Motor.INIT_TIMEOUT);
        talon.set(ControlMode.PercentOutput, 0);
//        talon.disableControl(); // needed if switching from auton settings
    }

    public void setMotionProfileSettings(WPI_TalonSRX talon)
    {
        talon.set(ControlMode.MotionProfile, 0);
        talon.config_kF(0, 0.27053062082237783, RobotMap.Motor.INIT_TIMEOUT);
        talon.config_kP(0,0, RobotMap.Motor.INIT_TIMEOUT);
        talon.config_kI(0,0, RobotMap.Motor.INIT_TIMEOUT);
        talon.config_kD(0,0, RobotMap.Motor.INIT_TIMEOUT);
    }

    /**
     * @return the position of the left side of the drivetrain in inches
     */
    public double getEncLeftPosition() { return (leftTalon0.getSelectedSensorPosition(0) * Math.PI *  4)  / (1024); }

    /**
     * @return the position of the right side of the drivetrain in inches
     */
    public double getEncRightPosition() { return (rightTalon1.getSelectedSensorPosition(0) * Math.PI * 4) / (1024); }

    /**
     * @return the average position between the left and right side of the drivetrain
     */
    @Deprecated
    public double getEncAveg() { return (getEncRightPosition() + getEncLeftPosition())/2; }

    public double turningFactor() { return Math.abs(OI.JOYSTICK_DRIVE_LEFT.getY() - OI.JOYSTICK_DRIVE_RIGHT.getY());}

    public double avgVel()
    {
        return Math.abs((leftTalon0.getSelectedSensorVelocity(0) + rightTalon1.getSelectedSensorVelocity(0))/2);
//        return Math.abs(rightTalon1.getEncVelocity());
    }

    @Override
    protected void initDefaultCommand() { setDefaultCommand(new DriveCommand()); }

//    private static void debugSpeed(String format, Object... args)
//    {
//        Log.debug(String.format(format, args));
//    }

    private int logCounter = 0;

    @SuppressWarnings({ "SuspiciousNameCombination", "PointlessBooleanExpression", "ConstantConditions" })
    @Deprecated
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
        if(Math.abs(leftSpeed) < 0.1D) { leftSpeed = 0.0D; }

        if(Math.abs(rightSpeed) < 0.1D) { rightSpeed = 0.0D; }

        out.left = leftSpeed;
        out.right = rightSpeed;
        return out;
    }

    long counter = 0;

    @Deprecated
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

        if(negative) { drive.tankDrive(-speed.left, -speed.right, true); }

        else { drive.tankDrive(speed.left, speed.right, true); }
    }

    /**
     * Drive the robot. The equation x=-y must be true for the robot to drive straight.
     * <br>
     * Make sure to set the motors according to the control mode. In auton, it's position. In teleop, it's percent voltage.
     *
     * @param x Units for the left side of drivetrain
     * @param y Units for the right side of drivetrain
     */
    public void runMotors(double x, double y) // double z
    {
        System.out.println("Left Speed:  " + x);
        System.out.println("Right Speed:  " + y);
        System.out.println("\n\n");
        leftSpeed = x;
        rightSpeed = y;
        leftTalon0.set(x);
        leftTalon1.set(x);
        rightTalon0.set(y);
        rightTalon1.set(y);
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
    }

    public void disabledStop()
    {
        lastLeft = 0.0D;
        lastRight = 0.0D;
        drive.tankDrive(0.0D, 0.0D);
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