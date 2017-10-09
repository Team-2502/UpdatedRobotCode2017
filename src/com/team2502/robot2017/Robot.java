package com.team2502.robot2017;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.subsystem.*;
import com.team2502.robot2017.vision.VisionServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import logger.Log;

public final class Robot extends IterativeRobot
{

    // Makes all the stuff
    public static DriveTrainSubsystem DRIVE_TRAIN;
    public static PressureSensorSubsystem PRESSURE_SENSOR;
    public static VisionSubsystem VISION;
    public static Compressor COMPRESSOR;
    public static ShooterSubsystem SHOOTER;
    public static ActiveIntakeSubsystem ACTIVE;
    public static DriveTrainTransmissionSubsystem DRIVE_TRAIN_GEAR_SWITCH;
    public static ClimberSubsystem CLIMBER;
    public static HopperSubsystem HOPPER;
    public static VisionServer VISIONSERVER;

    public static long SHIFTED;

    // NavX Subsystem
    public static AHRS NAVX;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */

    public void robotInit()
    {
//        Log.createLogger();

        // This creates a static instance of the server and runs it
        // If you look in the constructor, it starts a new vision thread
        // So we don't have to worry about starting it here
        VISIONSERVER = VisionServer.getInstance();

        DRIVE_TRAIN = new DriveTrainSubsystem();
        DRIVE_TRAIN_GEAR_SWITCH = new DriveTrainTransmissionSubsystem();
        PRESSURE_SENSOR = new PressureSensorSubsystem();
        VISION = new VisionSubsystem();
        COMPRESSOR = new Compressor();
        SHOOTER = new ShooterSubsystem();
        ACTIVE = new ActiveIntakeSubsystem();
        CLIMBER = new ClimberSubsystem();
        HOPPER = new HopperSubsystem();
        NAVX = new AHRS(SPI.Port.kMXP);

        AutoSwitcher.putToSmartDashboard();

        DashboardData.setup();


        VISIONSERVER.addVisionUpdateReceiver(VISION);

        OI.init();

        NAVX.resetDisplacement();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {}

    public void disabledPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
        DRIVE_TRAIN.disabledStop();
        logVision();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings and commands.
     */
    public void autonomousInit()
    {

        Scheduler.getInstance().add(AutoSwitcher.getAutoInstance());
        NAVX.reset();
        VISION.turnOnVisionLight();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
        logVision();
    }

    public void teleopInit() { VISION.turnOffVisionLight(); }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic()
    {
        Scheduler.getInstance().run();
        DashboardData.update();
        logVision();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic()
    {
        LiveWindow.run();
        DashboardData.update();
        logVision();
    }

    private void logVision()
    {

        if(false)
        {
            System.out.println("[Vision] Target Height: " + VISION.getHeight());
            System.out.println("[Vision] Target Offset: " + VISION.getOffset());
            System.out.println("[Vision] FPS: " + VISION.getFPS());
            System.out.println("\n\n\n");
        }

    }
}