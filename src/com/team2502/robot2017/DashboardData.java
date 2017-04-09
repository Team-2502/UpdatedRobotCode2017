package com.team2502.robot2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@SuppressWarnings({ "WeakerAccess" })
public final class DashboardData
{
    private DashboardData() {}
    
    public static void update()
    {
    	updatePressure();
    	updateDriveTrain();
        updateNavX();
        updateShooter();
    }

    public static void setup()
    {
        // versioning
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
    
    private static void updateShooter()
    {
        SmartDashboard.putNumber("FW: Current Flywheel Speed", Robot.SHOOTER.getSpeedFlywheel());
        SmartDashboard.putNumber("FW: Target Speed", Robot.SHOOTER.getFlywheelTargetSpeed());
        SmartDashboard.putNumber("FW: Loop Error", Robot.SHOOTER.getError());
        SmartDashboard.putNumber("FW: Motor Output", Robot.SHOOTER.getMotorOutput());
        
        SmartDashboard.putNumber("BB: Current Feeder Speed", Robot.SHOOTER.getSpeedFeeder());
        SmartDashboard.putNumber("BB: Target Speed", Robot.SHOOTER.getBanebotTargetSpeed());
        SmartDashboard.putNumber("BB: Loop Error", Robot.SHOOTER.getErrorFeeder());

        SmartDashboard.putNumber("FEED: Agitator Target Speed", Robot.SHOOTER.getAgitatorTargetSpeed());
        SmartDashboard.putNumber("FEED: Colson Target Speed", Robot.SHOOTER.getColsonTargetSpeed());
        SmartDashboard.putNumber("FEED: Banebot Target Speed", Robot.SHOOTER.getBanebotTargetSpeed());
    }
    
    private static void updateDriveTrain()
    {
    	SmartDashboard.putNumber("aDT: DriveTrainLeft", Robot.DRIVE_TRAIN.getEncLeftPosition());
        SmartDashboard.putNumber("aDT: DriveTrainRight", Robot.DRIVE_TRAIN.getEncRightPosition());
        SmartDashboard.putNumber("aDT: DriveTrainAveg", Robot.DRIVE_TRAIN.getEncAveg());
    }

    private static void updateNavX()
    {
    	SmartDashboard.putNumber("NavX: Yaw", Robot.NAVX.getYaw());
    	SmartDashboard.putNumber("NavX: X Displacement", Robot.NAVX.getDisplacementX());
    	SmartDashboard.putNumber("NavX: Y Displacement", Robot.NAVX.getDisplacementY());
    	SmartDashboard.putNumber("NavX: Z Displacement", Robot.NAVX.getDisplacementZ());
    }

    private static void updatePressure()
    {
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