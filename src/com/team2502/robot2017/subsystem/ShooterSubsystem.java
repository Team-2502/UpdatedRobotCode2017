package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.FlywheelCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem
{
    public CANTalon.TalonControlMode follower = CANTalon.TalonControlMode.Follower;

    private final CANTalon leftFlywheelTalonTop;
    private final CANTalon leftFlywheelTalonBottom;
    private final CANTalon rightFlywheelTalonTop;
    private final CANTalon rightFlywheelTalonBottom;
    private final CANTalon colsonFeeder;
    private final CANTalon banebotFeeder;
    private final CANTalon agitator;

    double targetSpeedFlywheel = 2975;

    double autoTargetSpeed = targetSpeedFlywheel + 50;
    double agitatorSpeed = 1;
    double colsonSpeed = 1;
    double banebotSpeed = 1400;
    int error = 0;

    //PID is for Top Left Talon
    double kF = 0;
    double kP = 0.79;
    double kI = 0.0017;
    double kD = 1.0;


    public boolean isFlywheelActive;
    public boolean isFeederActive;
    private boolean shooterMode = false;
    private boolean isTriggerPressed = false;

    /**
     * Initialize shooter subsystem
     */
    public ShooterSubsystem()
    {
        leftFlywheelTalonTop = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_0);
        leftFlywheelTalonBottom = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_1);
        rightFlywheelTalonTop = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_2);
        rightFlywheelTalonBottom = new CANTalon(RobotMap.Motor.FLYWHEEL_TALON_3);
        colsonFeeder = new CANTalon(RobotMap.Motor.FEEDER_TALON_0);
        banebotFeeder = new CANTalon(RobotMap.Motor.FEEDER_TALON_1);
        agitator = new CANTalon(RobotMap.Motor.AGITATOR);
    }


    /**
     * Set FPID, encoder settings, talon settings, and the default command.
     */
    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new FlywheelCommand());

        // Set talon and encoder settings
        leftFlywheelTalonTop.changeControlMode(CANTalon.TalonControlMode.Speed);

        rightFlywheelTalonTop.changeControlMode(follower);
        leftFlywheelTalonBottom.changeControlMode(follower);
        rightFlywheelTalonBottom.changeControlMode(follower);

        rightFlywheelTalonTop.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        leftFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        rightFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
//
        leftFlywheelTalonTop.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        leftFlywheelTalonTop.configEncoderCodesPerRev(256);
        leftFlywheelTalonTop.reverseSensor(false);

        // Configure min and max voltages
        leftFlywheelTalonTop.configNominalOutputVoltage(0.0D, -0.0D);
        leftFlywheelTalonTop.configPeakOutputVoltage(12.0D, -0.0D);

        leftFlywheelTalonBottom.configNominalOutputVoltage(0.0D, -0.0D);
        leftFlywheelTalonBottom.configPeakOutputVoltage(12.0D, -0.0D);

        rightFlywheelTalonTop.configNominalOutputVoltage(0.0D, -0.0D);
        rightFlywheelTalonTop.configPeakOutputVoltage(12.0D, -0.0D);

        rightFlywheelTalonBottom.configNominalOutputVoltage(0.0D, -0.0D);
        rightFlywheelTalonBottom.configPeakOutputVoltage(12.0D, -0.0D);

        // Disables brakes
        leftFlywheelTalonBottom.enableBrakeMode(false);
        leftFlywheelTalonTop.enableBrakeMode(false);
        rightFlywheelTalonBottom.enableBrakeMode(false);
        rightFlywheelTalonTop.enableBrakeMode(false);

        // Set more encoder settings

        leftFlywheelTalonTop.setF(kF);
        leftFlywheelTalonTop.setP(kP);
        leftFlywheelTalonTop.setI(kI);
        leftFlywheelTalonTop.setD(kD);

        // Set banebot talon and encoder settings
        banebotFeeder.changeControlMode(CANTalon.TalonControlMode.Speed);
        banebotFeeder.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        banebotFeeder.configEncoderCodesPerRev(256);
        banebotFeeder.reverseSensor(false);

        // set banebot min and max voltages
        banebotFeeder.configNominalOutputVoltage(0.0D, -0.0D);
        banebotFeeder.configPeakOutputVoltage(12.0D, -12.0D);

        // set fpid for the banebot
        banebotFeeder.setProfile(0);
        banebotFeeder.setF(0.0);
        banebotFeeder.setP(0.7);
        banebotFeeder.setI(0.0);
        banebotFeeder.setD(0.0);
    }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The current velocity of the flywheel.
     */
    public int getSpeedFlywheel() { return leftFlywheelTalonTop.getEncVelocity(); }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The current velocity of the banebot feeder.
     */
    public int getSpeedFeeder() { return banebotFeeder.getEncVelocity(); }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The output voltage of the flywheel talon divided by its bus voltage
     */
    public double getMotorOutput() { return leftFlywheelTalonTop.getOutputVoltage() / leftFlywheelTalonTop.getBusVoltage(); }

    /**
     * @return the agitator target speed
     */
    public double getAgitatorTargetSpeed() { return agitatorSpeed; }

    /**
     * @return the colson target speed
     */
    public double getColsonTargetSpeed() { return colsonSpeed; }

    /**
     * @return the banebot target speed
     */
    public double getBanebotTargetSpeed() { return banebotSpeed; }

    /**
     * @return the target speed of the flywheel
     */
    public double getFlywheelTargetSpeed() { return targetSpeedFlywheel; }

    /**
     * <b>Actually turns on the flywheel</b>. Sets appropriate talon settings and FPID in the process.
     */
    public void turnOnFlywheel()
    {
        leftFlywheelTalonTop.changeControlMode(CANTalon.TalonControlMode.Speed);

        rightFlywheelTalonTop.changeControlMode(follower);
        leftFlywheelTalonBottom.changeControlMode(follower);
        rightFlywheelTalonBottom.changeControlMode(follower);

        leftFlywheelTalonTop.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        leftFlywheelTalonTop.configEncoderCodesPerRev(256);
        leftFlywheelTalonTop.reverseSensor(false);

        leftFlywheelTalonTop.configNominalOutputVoltage(0.0D, -0.0D);
        leftFlywheelTalonTop.configPeakOutputVoltage(12.0D, -0.0D);

        leftFlywheelTalonBottom.configNominalOutputVoltage(0.0D, -0.0D);
        leftFlywheelTalonBottom.configPeakOutputVoltage(12.0D, -0.0D);

        rightFlywheelTalonTop.configNominalOutputVoltage(0.0D, -0.0D);
        rightFlywheelTalonTop.configPeakOutputVoltage(12.0D, -0.0D);

        rightFlywheelTalonBottom.configNominalOutputVoltage(0.0D, -0.0D);
        rightFlywheelTalonBottom.configPeakOutputVoltage(12.0D, -0.0D);

        leftFlywheelTalonTop.setF(kF);
        leftFlywheelTalonTop.setP(kP);
        leftFlywheelTalonTop.setI(kI);
        leftFlywheelTalonTop.setD(kD);

        leftFlywheelTalonTop.set(autoTargetSpeed);
    }

    /**
     * Feed balls into flywheel according to the speeds set by the Drivers
     */
    public void feed()
    {
//        banebotFeeder.changeControlMode(follower);
        colsonFeeder.set(colsonSpeed);
        banebotFeeder.set(banebotSpeed);
        agitator.set(agitatorSpeed);
    }

    public void teleopFeed(boolean neg)
    {
//        colsonFeeder.set(neg ? -colsonSpeed: colsonSpeed);
//        banebotFeeder.set(neg ? banebotSpeed: -banebotSpeed);
        agitator.set(neg ? -agitatorSpeed : agitatorSpeed);

    }


    /**
     * @return the target speed
     */
    public double getTargetSpeedFlywheel() { return targetSpeedFlywheel; }

    /**
     * @return Error calculated in the flywheel FPID
     */
    public int getError() { return leftFlywheelTalonTop.getClosedLoopError(); }

    /**
     * @return Error calculated in the banebot FPID
     */
    public int getErrorFeeder() { return banebotFeeder.getClosedLoopError(); }

    /**
     * Change the speed of the agitator
     *
     * @param isAdd Should I increase the speed?
     */
    public void changeSpeedAgitator(boolean isAdd)
    {
        if(isAdd) { agitatorSpeed += 0.05; }
        if(!isAdd) { agitatorSpeed -= 0.05; }
    }

    /**
     * Change the speed of the colson
     *
     * @param isAdd Should I increase the speed?
     */
    public void changeSpeedColson(boolean isAdd)
    {
        if(isAdd) { colsonSpeed += 0.05; }
        if(!isAdd) { colsonSpeed -= 0.05; }
    }

    /**
     * Change the speed of the banebot
     *
     * @param isAdd Should I increase the speed?
     */
    public void changeSpeedBanebot(boolean isAdd)
    {
        if(isAdd) { banebotSpeed += 50; }
        if(!isAdd) { banebotSpeed -= 50; }
    }

    /**
     * Change the speed of the flywheel
     *
     * @param isAdd Should I increase the speed?
     */
    public void changeSpeedFlywheel(boolean isAdd)
    {
        if(isAdd) { targetSpeedFlywheel += 10; }
        if(!isAdd) { targetSpeedFlywheel -= 10; }
    }

    /**
     * Sets the speed of all the motors.
     *
     * @param Speed speed at which to set the motors at.
     */
    public void setSpeedOnAllFlyWheelMotors(double Speed)
    {
        leftFlywheelTalonTop.set(Speed);
//        leftFlywheelTalonBottom.set(-Speed);
//        rightFlywheelTalonTop.set(Speed);
//        rightFlywheelTalonBottom.set(-Speed);
    }

    /**
     * Allow Poorva to press buttons on the joystick to activate the flywheel
     */
    public void flywheelDrive()
    {
        // lets us tell the flywheel go a certain RPM
        leftFlywheelTalonTop.changeControlMode(CANTalon.TalonControlMode.Speed);

        rightFlywheelTalonTop.changeControlMode(follower);
        leftFlywheelTalonBottom.changeControlMode(follower);
        rightFlywheelTalonBottom.changeControlMode(follower);

        banebotFeeder.changeControlMode(CANTalon.TalonControlMode.Speed);

        // Toggle mode for flywheel. It is bound to button 5 on the Function stick.
        if(OI.JOYSTICK_FUNCTION.getRawButton(5) && !isTriggerPressed) { shooterMode = !shooterMode; }
        isTriggerPressed = OI.JOYSTICK_FUNCTION.getRawButton(5);

        if(shooterMode) { setSpeedOnAllFlyWheelMotors(targetSpeedFlywheel); } else { setSpeedOnAllFlyWheelMotors(0); }

        //Control for turning on/off the feeding mechanism.
        if(OI.JOYSTICK_FUNCTION.getTrigger())
        {
            colsonFeeder.set(colsonSpeed);
            banebotFeeder.set(banebotSpeed);
            agitator.set(agitatorSpeed);
        } else if(OI.JOYSTICK_FUNCTION.getRawButton(12)) { agitator.set(-agitatorSpeed); } else
        {
            colsonFeeder.set(0);
            banebotFeeder.set(0);
            agitator.set(0);
        }
    }

    /**
     * Kill flywheel by setting talons to 0
     */
    public void stop()
    {
        shooterMode = false;

        setSpeedOnAllFlyWheelMotors(0.0D);
        colsonFeeder.set(0.0D);
        banebotFeeder.set(0.0D);
        agitator.set(0.0D);

        // Causes problems with banebots starting after shooter is turned off
        // If we want to fix then we would have to enable it somewhere.
//        banebotFeeder.changeControlMode(CANTalon.TalonControlMode.Disabled);

        System.out.println("Stopping shooter and related motors.");


        isFlywheelActive = false;
        isFeederActive = false;
    }
}