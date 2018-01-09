package com.team2502.robot2017.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.FlywheelCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem
{
    public ControlMode follower = ControlMode.Follower;

    private final WPI_TalonSRX leftFlywheelTalonTop;
    private final WPI_TalonSRX leftFlywheelTalonBottom;
    private final WPI_TalonSRX rightFlywheelTalonTop;
    private final WPI_TalonSRX rightFlywheelTalonBottom;
    private final WPI_TalonSRX colsonFeeder;
    private final WPI_TalonSRX banebotFeeder;
    private final WPI_TalonSRX agitator;

    double targetSpeedFlywheel = 3005;

    double autoTargetSpeed = targetSpeedFlywheel - 50;
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
        leftFlywheelTalonTop = new WPI_TalonSRX(RobotMap.Motor.FLYWHEEL_TALON_0);
        leftFlywheelTalonBottom = new WPI_TalonSRX(RobotMap.Motor.FLYWHEEL_TALON_1);
        rightFlywheelTalonTop = new WPI_TalonSRX(RobotMap.Motor.FLYWHEEL_TALON_2);
        rightFlywheelTalonBottom = new WPI_TalonSRX(RobotMap.Motor.FLYWHEEL_TALON_3);
        colsonFeeder = new WPI_TalonSRX(RobotMap.Motor.FEEDER_TALON_0);
        banebotFeeder = new WPI_TalonSRX(RobotMap.Motor.FEEDER_TALON_1);
        agitator = new WPI_TalonSRX(RobotMap.Motor.AGITATOR);
    }


    /**
     * Set FPID, encoder settings, talon settings, and the default command.
     */
    @Override
    protected void initDefaultCommand()
    {
        this.setDefaultCommand(new FlywheelCommand());

        // Set talon and encoder settings
        leftFlywheelTalonTop.set(ControlMode.Velocity,0);

        rightFlywheelTalonTop.set(follower,0);
        leftFlywheelTalonBottom.set(follower,0);
        rightFlywheelTalonBottom.set(follower,0);

        rightFlywheelTalonTop.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        leftFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        rightFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
//
        leftFlywheelTalonTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,RobotMap.Motor.INIT_TIMEOUT );
        leftFlywheelTalonTop.setSensorPhase(false);

        // Configure min and max voltages
        leftFlywheelTalonTop.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);


        leftFlywheelTalonBottom.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonBottom.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonBottom.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonBottom.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        rightFlywheelTalonTop.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonTop.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonTop.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonTop.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        rightFlywheelTalonBottom.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonBottom.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonBottom.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonBottom.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        // Disables brakes
        leftFlywheelTalonBottom.setNeutralMode(NeutralMode.Coast);
        leftFlywheelTalonTop.setNeutralMode(NeutralMode.Coast);
        rightFlywheelTalonBottom.setNeutralMode(NeutralMode.Coast);
        rightFlywheelTalonTop.setNeutralMode(NeutralMode.Coast);

        // Set more encoder settings

        leftFlywheelTalonTop.config_kF(0, kF, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.config_kP(0, kP, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.config_kI(0, kI, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.config_kD(0, kD, RobotMap.Motor.INIT_TIMEOUT);

        // Set banebot talon and encoder settings
        banebotFeeder.set(ControlMode.Velocity,0 );
        banebotFeeder.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.setSensorPhase(false);

        // set banebot min and max voltages
        banebotFeeder.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        // set fpid for the banebot
        banebotFeeder.config_kF(0, 0, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.config_kP(0, 0.7, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.config_kI(0, 0, RobotMap.Motor.INIT_TIMEOUT);
        banebotFeeder.config_kD(0, 0, RobotMap.Motor.INIT_TIMEOUT);
    }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The current velocity of the flywheel.
     */
    public int getSpeedFlywheel() { return leftFlywheelTalonTop.getSelectedSensorVelocity(0); }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The current velocity of the banebot feeder.
     */
    public int getSpeedFeeder() { return banebotFeeder.getSelectedSensorVelocity(0); }

    /**
     * This information is pulled from the CANTalon class, NOT THE ENCODER CLASS!
     *
     * @return The output voltage of the flywheel talon divided by its bus voltage
     */
    public double getMotorOutput() { return leftFlywheelTalonTop.getMotorOutputVoltage() / leftFlywheelTalonTop.getBusVoltage(); }

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
        leftFlywheelTalonTop.set(ControlMode.Velocity, 0);
        rightFlywheelTalonTop.set(follower,0);
        leftFlywheelTalonBottom.set(follower,0);
        rightFlywheelTalonBottom.set(follower,0);

        rightFlywheelTalonTop.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        leftFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
        rightFlywheelTalonBottom.set(RobotMap.Motor.FLYWHEEL_TALON_0);
//
        leftFlywheelTalonTop.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,RobotMap.Motor.INIT_TIMEOUT );
        leftFlywheelTalonTop.setSensorPhase(false);

        // Configure min and max voltages
        leftFlywheelTalonTop.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);


        leftFlywheelTalonBottom.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonBottom.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonBottom.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonBottom.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        rightFlywheelTalonTop.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonTop.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonTop.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonTop.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        rightFlywheelTalonBottom.configNominalOutputForward(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonBottom.configNominalOutputReverse(0, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonBottom.configPeakOutputForward(1, RobotMap.Motor.INIT_TIMEOUT);
        rightFlywheelTalonBottom.configPeakOutputReverse(1, RobotMap.Motor.INIT_TIMEOUT);

        // Disables brakes
        leftFlywheelTalonBottom.setNeutralMode(NeutralMode.Coast);
        leftFlywheelTalonTop.setNeutralMode(NeutralMode.Coast);
        rightFlywheelTalonBottom.setNeutralMode(NeutralMode.Coast);
        rightFlywheelTalonTop.setNeutralMode(NeutralMode.Coast);

        // Set more encoder settings

        leftFlywheelTalonTop.config_kF(0, kF, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.config_kP(0, kP, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.config_kI(0, kI, RobotMap.Motor.INIT_TIMEOUT);
        leftFlywheelTalonTop.config_kD(0, kD, RobotMap.Motor.INIT_TIMEOUT);

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
    public int getError() { return leftFlywheelTalonTop.getClosedLoopError(0); }

    /**
     * @return Error calculated in the banebot FPID
     */
    public int getErrorFeeder() { return banebotFeeder.getClosedLoopError(0); }

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
        leftFlywheelTalonTop.set(ControlMode.Velocity, 0);

        rightFlywheelTalonTop.set(follower, 0);
        leftFlywheelTalonBottom.set(follower, 0);
        rightFlywheelTalonBottom.set(follower, 0);

        banebotFeeder.set(ControlMode.Velocity, 0);

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