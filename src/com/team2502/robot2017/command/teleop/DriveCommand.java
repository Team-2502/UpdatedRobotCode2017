package com.team2502.robot2017.command.teleop;

import com.kauailabs.navx.frc.AHRS;
import com.team2502.robot2017.OI;
import com.team2502.robot2017.Robot;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.subsystem.DriveTrainSubsystem;
import com.team2502.robot2017.subsystem.DriveTrainTransmissionSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import logger.Log;

/**
 * Takes care of all Drivetrain related operations during Teleop, including automatic shifting
 * Automatic shifting will:
 * <li>
 * <ul>Space out shifting by at least 1/2 second</ul>
 * <ul>Invert itself if the driver holds a special button</ul>
 * <ul>Only shift when going mostly straight</ul>
 * <ul>Shift up if accelerating, going fast, and the driver is pushing hard on the sticks</ul>
 * <ul>Shift down if the sticks are being pushed but there is no acceleration</ul>
 * <ul>Shift down if the sticks aren't being pushed hard and the robot is going slow</ul>
 * </li>
 */
public class DriveCommand extends Command
{
    private static boolean disabledAutoShifting = false;
    private final DriveTrainSubsystem driveTrainSubsystem;
    private final DriveTrainTransmissionSubsystem transmission;
    private final AHRS navx;
    private boolean shiftedUp = false;

    public DriveCommand()
    {
        requires(Robot.DRIVE_TRAIN);
        requires(Robot.DRIVE_TRAIN_GEAR_SWITCH);
        driveTrainSubsystem = Robot.DRIVE_TRAIN;
        navx = Robot.NAVX;
        transmission = Robot.DRIVE_TRAIN_GEAR_SWITCH;
        shiftedUp = false;
    }

    public static void toggleAutoShifting()
    {
        disabledAutoShifting = !disabledAutoShifting;
    }

    @Override
    protected void initialize() {driveTrainSubsystem.setTeleopSettings();}

    @Override
    protected void execute()
    {
        SmartDashboard.putBoolean("DT: AutoShifting Enabled?", !disabledAutoShifting);
        driveTrainSubsystem.drive();

        if (!disabledAutoShifting)
        {
            // Check that at least 1/2 second has passed since last shifting
            if ((System.currentTimeMillis() - Robot.SHIFTED) >= 500)
            {

                // Do the opposite if the driver is forcing a shift
                if (OI.JOYSTICK_DRIVE_RIGHT.getRawButton(RobotMap.Joystick.Button.SWITCH_DRIVE_TRANSMISSION))
                {
                    Log.warn("Shifting down forced by driver.");
                    transmission.setGear(false);
                }
                // If the driver is cool with auto shifting doing its thing
                else
                {
                    // Make sure that we're going mostly straight
                    if (driveTrainSubsystem.turningFactor() < 0.1)
                    {
                        double accel = navx.getRawAccelY();
                        double speed = driveTrainSubsystem.avgVel();

                        // Shift up if we are accelerating and going fast and the driver is putting the joystick at least 80% forward or backward
                        if (Math.abs(accel) > 0.15 && speed > RobotMap.Motor.SHIFT_UP_THRESHOLD && OI.joysThreshold(0.8, true))
                        {

                            if (!shiftedUp)
                            {
                                shiftedUp = true;
                                Log.info("Shifting up.");
                            }
                            transmission.setGear(true);
                        } else if (!transmission.signsame(accel, driveTrainSubsystem.rightTalon1.getEncVelocity()) && OI.joysThreshold(0.8, false)) /* If we are not accelerating very fast but the driver is still pushing forward we shift down because it is probably a pushing match */
                        {
                            if (shiftedUp)
                            {
                                shiftedUp = false;
                                Log.info("Shifting down because you're a bad driver.");
                            }
                            transmission.setGear(false);
                        } else if (OI.joysThreshold(30, false) && speed < RobotMap.Motor.SHIFT_DOWN_THRESHOLD) /* If we're going slow and the driver wants it to be that way we shift down */
                        {
                            if (shiftedUp)
                            {
                                shiftedUp = false;
                                Log.info("Shifting down because slow.");
                            }
                            transmission.setGear(false);
                        }
                    }
                }
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