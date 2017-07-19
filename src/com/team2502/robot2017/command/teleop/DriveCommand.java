package com.team2502.robot2017.command.teleop;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainTransmissionSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Control the drivetrain during teleop.
 */
public class DriveCommand extends Command
{
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final DriveTrainTransmissionSubsystem transmission;
    private final AHRS navx;
    private double accel;
    private double turning;
    private double speed;


    public DriveCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.DRIVE_TRAIN_GEAR_SWITCH);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        transmission = Robot.DRIVE_TRAIN_GEAR_SWITCH;
    }

    @Override
    protected void initialize()
    {
        driveTrainSubsystem.setTeleopSettings();
    }

    boolean shiftedUp = false;

    @Override
    protected void execute() {
        driveTrainSubsystem.drive();


        // Check that at least 1/2 second has passed since last shifting
        if((System.currentTimeMillis()-Robot.SHIFTED) >= 500)
        {
            if(OI.JOYSTICK_DRIVE_RIGHT.getRawButton(RobotMap.Joystick.Button.SWITCH_DRIVE_TRANSMISSION))
            {
                System.out.println("Shifting " + (shiftedUp ? "down" : "up") + " forced by driver.");
                transmission.setGear(!shiftedUp);
            }
            // Make sure the driver isn't forcing anything
            else
            {
                if ( Math.abs(navx.getRawAccelY()) > 0.15 && driveTrainSubsystem.avgVel() > RobotMap.Motor.SHIFT_UP_THRESHOLD && OI.joysThreshold(0.8, true))
                {
                    if(!shiftedUp)
                    {
                        shiftedUp = true;
                        System.out.println("Shifting up.");
                    }
                    transmission.setGear(true);

                } else if (Math.abs(navx.getRawAccelY()) <= 0.1 && OI.joysThreshold(0.8, false))
                {
                    if(shiftedUp)
                    {
                        shiftedUp = false;
                        System.out.println("Shifting down.");
                    }
                    transmission.setGear(false);

                } else if (OI.joysThreshold(30, false) && driveTrainSubsystem.avgVel() < RobotMap.Motor.SHIFT_DOWN_THRESHOLD)
                {
                    if(shiftedUp)
                    {
                        shiftedUp = false;
                        System.out.println("Shifting down0.");
                    }
                    transmission.setGear(false);
                }
            }
            // Make sure that we're not turning super hard
            if(driveTrainSubsystem.turningFactor() < 0.1)
            {
            }

        }

    }

    @Override
    protected boolean isFinished() { return false; }

    @Override
    protected void end() { driveTrainSubsystem.stop(); }

    @Override
    protected void interrupted() { end(); }
}