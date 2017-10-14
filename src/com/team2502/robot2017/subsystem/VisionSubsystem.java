package com.team2502.robot2017.subsystem;


import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.TeleopVisionCommand;
import com.team2502.robot2017.vision.TargetInfo;
import com.team2502.robot2017.vision.VisionUpdate;
import com.team2502.robot2017.vision.VisionUpdateReceiver;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Relay;

import java.util.List;


// Implementing ITableListener is necessary for having the listener work, do not remove!
public class VisionSubsystem extends Subsystem implements VisionUpdateReceiver
{

    double height;
    double offset;
    double fps;
    double logCounter = 0;

    private Relay visionLight = new Relay(0);

    public VisionSubsystem()
    {
    }

    /**
     * Same as Math.sqrt but absolute values the number first
     * @param a the number
     * @return its square root
     */
    private double sqrt(double a)
    {
//        return Math.sqrt(Math.abs(a));
        return a;
    }

    private double f(double x, double kp)
    {
//        double result = Math.abs((1)/(1 + Math.pow(Math.E, x/50))) + 0.1;
        double result = x * x;
        System.out.println(result);
        return Math.max(result, 0.22 / kp);
    }

    @Override
    public void initDefaultCommand() { setDefaultCommand(new TeleopVisionCommand()); }

    /**
     * Align the robot to the shiny thing
     * <br>
     * Does not work if no shiny thing <b>or no Pi</b>
     *
     * @param dt        An instance of the drivetrain
     * @param lowSpeed  The speed that the slower side should go at
     * @param highSpeed The speed that the faster side should go at
     * @param alignOnly Are we aligning only, or are we also going forward if we are perfect?
     */
    public void alignWidth(DriveTrainSubsystem dt, double lowSpeed, double highSpeed, boolean alignOnly, boolean autonomous)
    {
        double kP = 0.0021;
//        double kP = 1;
        double tolerance = 5;
        double minimumSpeed = 0.22;
        double px_to_ticks = 1.89;

        if(autonomous || OI.JOYSTICK_DRIVE_LEFT.getRawButton(RobotMap.Joystick.Button.VISION_ALIGN))
        {
            double offset = getOffset();
            double sign = offset / Math.abs(offset);

            if(offset > tolerance)
            {
//                dt.runMotors(highSpeed, lowSpeed);
                dt.runMotors(Math.max(minimumSpeed, sqrt(offset * kP)), Math.max(minimumSpeed, sqrt(offset * kP)));
//                dt.runMotors(f(offset, kP), f(offset, kP));

//                dt.rightTalon1.set(offset * px_to_ticks);
//                dt.leftTalon0.set(offset * px_to_ticks);
            } else if(offset < -tolerance)
            {
//                dt.runMotors(-lowSpeed, -highSpeed);
                dt.runMotors(-Math.max(minimumSpeed, Math.abs(sqrt(offset * kP))), -Math.max(sqrt(offset * kP), minimumSpeed));
//                dt.runMotors(f(offset, kP), f(offset, kP));

//                dt.rightTalon1.set(offset * (px_to_ticks));
//                dt.leftTalon0.set(offset * (px_to_ticks));
            } else if((-0.1 < offset) && (offset < 0.1) && !alignOnly)
            {
                dt.runMotors(.5D, -.5D);
            }
        }
    }


    /**
     * @return the FPS of the Pi's vision processing
     */
    public double getFPS() { return fps; }

    /**
     * @return the offset calculated by the pi
     */
    public double getOffset() { return offset; }

    /**
     * @return the percieved height of the target
     */
    public double getHeight() { return height; }

    public void turnOffVisionLight() { visionLight.set(Relay.Value.kOff); }

    public void turnOnVisionLight() { visionLight.set(Relay.Value.kOn); }


    @Override
    public void gotUpdate(VisionUpdate update)
    {
        logCounter++;
        if(logCounter % 10 == 1)
        {
            System.out.println("num targets: " + update.getTargets().size());
        }
        for(int i = 0; i < update.getTargets().size(); i++)
        {
            TargetInfo target = update.getTargets().get(i);
            height = target.getHeight();
            offset = target.getOffset();
            if(logCounter % 10 == 1)
            {
                System.out.println("Target (height, offset): " + height + ", " + offset);
            }
        }

    }


}