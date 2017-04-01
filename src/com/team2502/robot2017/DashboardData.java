package com.team2502.robot2017;

import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({ "WeakerAccess" })
public final class DashboardData
{
    public static final List<Command> AUTONOMOUS_SELECTOR = new ArrayList<>();
    public static final List<DriveTrainSubsystem.DriveTypes> DRIVE_CONTROL_SELECTOR = new ArrayList<>();

    public static int SELECTED_AUTONOMOUS = 0;
    public static int SELECTED_DRIVE_TYPE = 0;

    private DashboardData() {}

    public static void update()
    {
        updatePressure();
        updateNavX();
        updateSelectors();
    }

    public static void setup()
    {
//        AUTONOMOUS_SELECTOR.addDefault("Gear", new AutoCommandG1());
//        AUTONOMOUS_SELECTOR.addDefault("Shoot", new AutoCommandG2());
        AUTONOMOUS_SELECTOR.add(new AutoCommandG3());

        DRIVE_CONTROL_SELECTOR.add(DriveTrainSubsystem.DriveTypes.DUAL_STICK);
        DRIVE_CONTROL_SELECTOR.add(DriveTrainSubsystem.DriveTypes.ARCADE);

        updateSelectors();

        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(DashboardData.class.getResourceAsStream("/version.properties")));
            String line;
            while((line = br.readLine()) != null)
            {
                if(line.startsWith("version="))
                {
                    String[] split = line.split("=");
                    if((split.length < 2) || (split[1] == null) || split[1].isEmpty()) { throw new Exception(); }
                    SmartDashboard.putString("Version", split[1]);
                    break;
                }
            }
            br.close();
        } catch(Exception e) { }
    }

    public static Command getAutonomous()
    {
        return AUTONOMOUS_SELECTOR.get(SELECTED_AUTONOMOUS);
    }

    public static DriveTrainSubsystem.DriveTypes getDriveType()
    {
        return DRIVE_CONTROL_SELECTOR.get(SELECTED_DRIVE_TYPE);
    }

    public static void updateSelectors()
    {
        if(Enabler.AUTONOMOUS.enabler[0])
        {
            SmartDashboard.putNumber("Auto Mode", SELECTED_AUTONOMOUS);
        }

        if(Enabler.DRIVE_CONTROL.enabler[0])
        {
            SmartDashboard.putNumber("Drive Control Mode", SELECTED_DRIVE_TYPE);
        }
    }

    private static void updateNavX()
    {
//    	SmartDashboard.putNumber("NavX: Yaw", Robot.NAVX.getYaw());
//    	SmartDashboard.putNumber("NavX: Roll", Robot.NAVX.getRoll());
//    	SmartDashboard.putNumber("NavX: Pitch", Robot.NAVX.getPitch());
//    	SmartDashboard.putNumber("NavX: Angle", Robot.NAVX.getAngle());
        SmartDashboard.putNumber("FW: Current Flywheel Speed", Robot.SHOOTER.getSpeed());
        SmartDashboard.putNumber("FW: Target Speed", Robot.SHOOTER.getTargetSpeed());
        SmartDashboard.putNumber("FW: Loop Error", Robot.SHOOTER.getError());
        SmartDashboard.putNumber("FW: Motor Output", Robot.SHOOTER.getMotorOutput());
    }

    private static void updatePressure()
    {
    	
        SmartDashboard.putNumber("FW: Current Flywheel Speed", Robot.SHOOTER.getSpeed());
        SmartDashboard.putNumber("FW: Target Speed", Robot.SHOOTER.getTargetSpeed());
        SmartDashboard.putNumber("FW: Loop Error", Robot.SHOOTER.getError());
        SmartDashboard.putNumber("FW: Motor Output", Robot.SHOOTER.getMotorOutput());
        
//        SmartDashboard.putNumber("aDT: DriveTrainLeft", Robot.DRIVE_TRAIN.getEncLeftPosition());
//        SmartDashboard.putNumber("aDT: DriveTrainRight", Robot.DRIVE_TRAIN.getEncRightPosition());
        
//        SmartDashboard.putNumber("NavX: Pitch", Robot.NAVX.getPitch());
//        SmartDashboard.putNumber("NavX: Roll", Robot.NAVX.getRoll());
//        SmartDashboard.putNumber("NavX: Yaw", Robot.NAVX.getYaw());
//        SmartDashboard.putNumber("NavX: Raw Accel X", Robot.NAVX.getRawAccelX());


        SmartDashboard.putNumber ("DS:Current Distance (in)", Robot.DISTANCE_SENSOR.getSensorDistance());

        if(Enabler.PRESSURE.enabler[0])
        {
            if(Enabler.PRESSURE.enabler[1]) { SmartDashboard.putNumber("Current Tank Pressure", Robot.PRESSURE_SENSOR.getPressure()); }
            if(Enabler.PRESSURE.enabler[2]) { SmartDashboard.putBoolean("Is Compressor Enabled", Robot.COMPRESSOR.enabled()); }
            if(Enabler.PRESSURE.enabler[3]) { SmartDashboard.putBoolean("Is Compressor Low", Robot.COMPRESSOR.getPressureSwitchValue()); }
            if(Enabler.PRESSURE.enabler[4]) { SmartDashboard.putNumber("Current Air Compression Rate", Robot.COMPRESSOR.getCompressorCurrent()); }
        }
    }

    private enum Enabler
    {
        AUTONOMOUS(true),
        PRESSURE(true, true, true, false, true),
        DRIVE_CONTROL(true);

        public final boolean[] enabler;

        Enabler(boolean... enabler) { this.enabler = enabler; }
    }
}
