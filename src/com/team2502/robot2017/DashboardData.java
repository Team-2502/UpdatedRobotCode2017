package com.team2502.robot2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

final class DashboardData
{

    private DashboardData() {}
    
    static void update()
    {
    	updatePressure();
    	updateDriveTrain();
        updateNavX();
        updateShooter();
    }

    static void setup() {}
    
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
        SmartDashboard.putNumber("DT: leftTalon1", Robot.DRIVE_TRAIN.leftTalon1.getPosition());
	    SmartDashboard.putNumber("DT: rightTalon1", Robot.DRIVE_TRAIN.rightTalon1.getPosition());
	    SmartDashboard.putBoolean("Drive Team will win us the match", false);

	    SmartDashboard.putNumber("DT: Average Speed", Robot.DRIVE_TRAIN.avgVel());
        SmartDashboard.putNumber("DT: Turning Factor", Robot.DRIVE_TRAIN.turningFactor());
//        SmartDashboard.putNumber("DT: Threshold above 80", OI.joysThreshold(80, true));
//        SmartDashboard.putNumber("DT: Threshold below 80", OI.joysThreshold(80, false));
        SmartDashboard.putNumber("DT: Acceleration in G's", Robot.NAVX.getRawAccelY());

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