package com.team2502.robot2017.subsystem;

import com.ctre.CANTalon;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.FlywheelCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem
{
    private final CANTalon leftFlywheelTalonTop;
    private final CANTalon leftFlywheelTalonBottom;
    private final CANTalon rightFlywheelTalonTop;
    private final CANTalon rightFlywheelTalonBottom;
    private final CANTalon colsonFeeder;
    private final CANTalon banebotFeeder;
    private final CANTalon agitator;
    public CANTalon.TalonControlMode follower = CANTalon.TalonControlMode.Follower;
    public boolean isFlywheelActive;
    public boolean isFeederActive;
    double targetSpeedFlywheel = 3525;

    double agitatorSpeed = 1;
    double colsonSpeed = 1;
    double banebotSpeed = 1400;

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
     * @return Error calculated in the flywheel FPID
     */
    public int getError() { return leftFlywheelTalonTop.getClosedLoopError(); }

    /**
     * @return Error calculated in the banebot FPID
     */
    public int getErrorFeeder() { return banebotFeeder.getClosedLoopError(); }

    private void configureBanebotFeeder() {
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

    private void configureFlywheel() {
        // Set Master
        leftFlywheelTalonTop.changeControlMode(CANTalon.TalonControlMode.Speed);

        // Configure Encoder
        leftFlywheelTalonTop.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        leftFlywheelTalonTop.configEncoderCodesPerRev(256);
        leftFlywheelTalonTop.reverseSensor(false);

        // Configure Slaves
        rightFlywheelTalonTop.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftFlywheelTalonBottom.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightFlywheelTalonBottom.changeControlMode(CANTalon.TalonControlMode.Follower);

        // Tell Slaves to follow orders from the master
        rightFlywheelTalonTop.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        leftFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        rightFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
    }

    private void configureFlywheelVoltages() {
        leftFlywheelTalonTop.configNominalOutputVoltage(0.0D, -0.0D);
        leftFlywheelTalonTop.configPeakOutputVoltage(12.0D, -2.0D);

        leftFlywheelTalonBottom.configNominalOutputVoltage(0.0D, -0.0D);
        leftFlywheelTalonBottom.configPeakOutputVoltage(12.0D, -2.0D);

        rightFlywheelTalonTop.configNominalOutputVoltage(0.0D, -0.0D);
        rightFlywheelTalonTop.configPeakOutputVoltage(12.0D, -2.0D);

        rightFlywheelTalonBottom.configNominalOutputVoltage(0.0D, -0.0D);
        rightFlywheelTalonBottom.configPeakOutputVoltage(12.0D, -2.0D);
    }

    private void setFlywheelPID() {

        leftFlywheelTalonTop.setProfile(0);
        leftFlywheelTalonTop.setF(0);
        leftFlywheelTalonTop.setP(0.7);
        leftFlywheelTalonTop.setI(0.0);
        leftFlywheelTalonTop.setD(0.0);
    }


    /**
     * <b>Actually turns on the flywheel</b>. Sets appropriate talon settings and FPID in the process.
     */
    public void turnOnFlywheel()
    {
        configureBanebotFeeder();

        configureFlywheel();

        configureFlywheelVoltages();

        setFlywheelPID();
        leftFlywheelTalonTop.set(targetSpeedFlywheel);
    }

    /**
     * Feed balls into flywheel according to the speeds set by the Drivers
     */
    public void feed()
    {

        colsonFeeder.set(colsonSpeed);
        banebotFeeder.set(-banebotSpeed);
        agitator.set(-agitatorSpeed);
    }

    public void teleopFeed(boolean neg)
    {
        agitator.set(neg ? agitatorSpeed : -agitatorSpeed);

    }


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
     * Allow Poorva to press buttons on the joystick to activate the flywheel
     */
    public void flywheelDrive()
    {

        // Toggle mode for flywheel. It is bound to button 5 on the Function stick.
        if(OI.JOYSTICK_FUNCTION.getRawButton(5) && !isTriggerPressed) { shooterMode = !shooterMode; }
        isTriggerPressed = OI.JOYSTICK_FUNCTION.getRawButton(5);

        if(shooterMode) { leftFlywheelTalonTop.set(targetSpeedFlywheel); }
        else { leftFlywheelTalonTop.set(0); }

        //Control for turning on/off the feeding mechanism.
        if(OI.JOYSTICK_FUNCTION.getTrigger())
        {
            colsonFeeder.set(colsonSpeed);
            banebotFeeder.set(banebotSpeed);
            agitator.set(-agitatorSpeed);
        }
        else if(OI.JOYSTICK_FUNCTION.getRawButton(12)) { agitator.set(agitatorSpeed); }
        else
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

        leftFlywheelTalonTop.set(0);
        colsonFeeder.set(0.0D);
        banebotFeeder.set(0.0D);
        agitator.set(0.0D);


        System.out.println("Stopping shooter and related motors.");


        isFlywheelActive = false;
        isFeederActive = false;
    }
}