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
import logger.Log;

/**
 * Example Implementation, Many changes needed.
 */

public class DriveTrainSubsystem extends Subsystem
{
    private static final Pair<Double, Double> SPEED_CONTAINER = new Pair<Double, Double>();
    private static final double DELAY_TIME = 5.77D + 43902.0D / 9999900.0D;
    public final CANTalon leftTalon0; //enc
    public final CANTalon leftTalon1;
    public final CANTalon rightTalon0;
    public final CANTalon rightTalon1; //enc
    private final RobotDrive drive;
    private double lastLeft;
    private double lastRight;
    private boolean negative = false;
    private boolean isNegativePressed = false;
    private double leftSpeed;
    private double rightSpeed;

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

        setTeleopSettings(leftTalon0);
        setTeleopSettings(rightTalon1);
    }

    /**
     * Set all talons into auton
     */
    public void setAutonSettings()
    {

        setAutonSettings(leftTalon0, true);
        leftTalon1.changeControlMode(TalonControlMode.Follower);
        leftTalon1.set(RobotMap.Motor.LEFT_TALON_0);

        setAutonSettings(rightTalon1, false);
        rightTalon0.changeControlMode(TalonControlMode.Follower);
        rightTalon0.set(RobotMap.Motor.RIGHT_TALON_1);
    }

    /**
     * Set the appropriate settings for autonomous
     *
     * @param talon      the talon to set the settings of
     * @param reverseEnc // TODO: add JavaDoc For this
     */
    public void setAutonSettings(CANTalon talon, boolean reverseEnc)
    {
        talon.changeControlMode(TalonControlMode.Position);
        talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        talon.configEncoderCodesPerRev(256);
        talon.reverseSensor(reverseEnc);
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0, -12.0);//8
        /* increase P until */
        talon.setPID(2.5, 0, 0); // confirmed working -- Miguel certified
        talon.setEncPosition(0);
        talon.enableControl();
    }

    public void setAutonSettingsVolts(CANTalon talon, boolean reverseEnc, double voltage)
    {
        setAutonSettings(talon, reverseEnc);
        talon.configPeakOutputVoltage(voltage, -voltage);
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
    public void setTeleopSettings(CANTalon talon)
    {
        talon.configNominalOutputVoltage(0.0D, -0.0D);
        talon.configPeakOutputVoltage(12.0D, -12.0D);
        talon.changeControlMode(TalonControlMode.PercentVbus);
        talon.disableControl(); // needed if switching from auton settings
    }

    public void setMotionProfileSettings(CANTalon talon)
    {
        talon.changeControlMode(TalonControlMode.MotionProfile);
        talon.setF(0.27053062082237783);
        talon.setP(0);
        talon.setI(0);
        talon.setD(0);
    }

    public void setMotionProfileSettings()
    {
        setMotionProfileSettings(leftTalon1);
        setMotionProfileSettings(rightTalon0);
        setMotionProfileSettings(rightTalon1);
        setMotionProfileSettings(leftTalon0);
    }

    public void feedTrajectoryPoints(double[][] profile, int totalCnt)
    {
        CANTalon.MotionProfileStatus status = new CANTalon.MotionProfileStatus();
        rightTalon1.getMotionProfileStatus(status);
        CANTalon.TrajectoryPoint point = new CANTalon.TrajectoryPoint();
        if (status.hasUnderrun)
        {
            Log.warn("We have underrun!");
            rightTalon1.clearMotionProfileHasUnderrun();
        }
        rightTalon1.clearMotionProfileTrajectories();
        rightTalon0.clearMotionProfileTrajectories();
        leftTalon0.clearMotionProfileTrajectories();
        leftTalon1.clearMotionProfileTrajectories();


        for (int i = 0; i < totalCnt; i++)
        {
            point.position = profile[i][0];
            point.velocity = profile[i][1];
            point.timeDurMs = (int) profile[i][2];
            point.profileSlotSelect = 0;
            point.velocityOnly = false;
            point.zeroPos = (i == 0);
            point.isLastPoint = (i + 1 == totalCnt);

            rightTalon1.pushMotionProfileTrajectory(point);
            rightTalon0.pushMotionProfileTrajectory(point);
            leftTalon1.pushMotionProfileTrajectory(point);
            leftTalon0.pushMotionProfileTrajectory(point);
        }
    }

    /**
     * @return the position of the left side of the drivetrain in feet
     */
    public double getEncLeftPosition() { return (leftTalon0.getPosition() * Math.PI * 4) / (1024 * 12); }

    /**
     * @return the position of the right side of the drivetrain in feet
     */
    public double getEncRightPosition() { return (rightTalon1.getPosition() * Math.PI * 4) / (1024 * 12); }

    /**
     * @return the average position between the left and right side of the drivetrain
     */
    @Deprecated
    public double getEncAveg() { return (getEncRightPosition() + getEncLeftPosition()) / 2; }

    public double turningFactor() { return Math.abs(OI.JOYSTICK_DRIVE_LEFT.getY() - OI.JOYSTICK_DRIVE_RIGHT.getY());}

    public double avgVel()
    {
        return Math.abs((leftTalon0.getEncVelocity() + rightTalon1.getEncVelocity()) / 2);
    }

    @Override
    protected void initDefaultCommand() { setDefaultCommand(new DriveCommand()); }

    @SuppressWarnings({"SuspiciousNameCombination", "PointlessBooleanExpression", "ConstantConditions"})
    @Deprecated
    private Pair<Double, Double> getSpeedArcade(Pair<Double, Double> out)
    {
        // Get the base speed of the robot

        double yLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();
        double leftSpeed = yLevel;
        double rightSpeed = yLevel;

        double xLevel = -OI.JOYSTICK_DRIVE_RIGHT.getX();

        // Should invert the left/right to be more intuitive while driving backwards.
        if (yLevel < 0.0D) { xLevel = -xLevel;}

        if (xLevel > 0.0D) { leftSpeed -= xLevel; } else if (xLevel < 0.0D) { rightSpeed += xLevel; }

        // Sets the speed to 0 if the speed is less than 0.05 or larger than -0.05
        if (Math.abs(leftSpeed) < 0.1D) { leftSpeed = 0.0D; }

        if (Math.abs(rightSpeed) < 0.1D) { rightSpeed = 0.0D; }

        out.left = leftSpeed;
        out.right = rightSpeed;
        return out;
    }

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
        if (negative) { joystickLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY(); } else
        {
            joystickLevel = -OI.JOYSTICK_DRIVE_LEFT.getY();
        }

        // Only increase the speed by a small amount
        double diff = joystickLevel - lastLeft;
        if (diff > 0.1D) { joystickLevel = lastLeft + 0.1D; } else if (diff < 0.1D) { joystickLevel = lastLeft - 0.1D; }

        lastLeft = joystickLevel;
        out.left = joystickLevel;

        if (negative) { joystickLevel = -OI.JOYSTICK_DRIVE_LEFT.getY(); } else
        {
            joystickLevel = -OI.JOYSTICK_DRIVE_RIGHT.getY();
        }

        diff = joystickLevel - lastRight;
        if (diff > 0.1D) { joystickLevel = lastRight + 0.1D; } else if (diff < 0.1D)
        {
            joystickLevel = lastRight - 0.1D;
        }

        lastRight = joystickLevel;
        out.right = joystickLevel;

        // Sets the speed to 0 if the speed is less than 0.05 or larger than
        // -0.05
        if (Math.abs(out.left) < 0.05D) { out.left = 0.0D; }

        if (Math.abs(out.right) < 0.05D) { out.right = 0.0D; }

        return out;
    }

    private Pair<Double, Double> getSpeed() { return getSpeed(SPEED_CONTAINER); }

    public void drive()
    {
        Pair<Double, Double> speed = getSpeed();

        //reverse drive
        if (OI.JOYSTICK_DRIVE_LEFT.getRawButton(1) && !isNegativePressed) { negative = !negative; }

        isNegativePressed = OI.JOYSTICK_DRIVE_LEFT.getRawButton(1);

        if (negative) { drive.tankDrive(-speed.left, -speed.right, true); } else
        {
            drive.tankDrive(speed.left, speed.right, true);
        }
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
        leftSpeed = x;
        rightSpeed = y;
        leftTalon0.set(x);
        leftTalon1.set(x);
        rightTalon0.set(y);
        rightTalon1.set(y);
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

    @Deprecated
    public void disabledStop()
    {
        rightTalon1.enableBrakeMode(false);
        rightTalon0.enableBrakeMode(false);
        leftTalon1.enableBrakeMode(false);
        leftTalon0.enableBrakeMode(false);

        lastLeft = 0.0D;
        lastRight = 0.0D;
        drive.tankDrive(0.0D, 0.0D);
//        ClimberCommand.setStopped(true);
        Timer.delay(0.3D);
    }

    public enum DriveTypes
    {
        DUAL_STICK, ARCADE;
    }

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
            if (this == o) { return true; }
            if (o instanceof Pair)
            {
                Pair pair = (Pair) o;
                return (left != null ? left.equals(pair.left) : pair.left == null)
                        && (left != null ? left.equals(pair.left) : pair.left == null);
            }
            return false;
        }
    }
}