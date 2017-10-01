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

    private Relay visionLight = new Relay(0);

    public VisionSubsystem()
    {
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
    public void align(DriveTrainSubsystem dt, double lowSpeed, double highSpeed, boolean alignOnly, boolean autonomous)
    {
        if(autonomous || OI.JOYSTICK_DRIVE_LEFT.getRawButton(RobotMap.Joystick.Button.VISION_ALIGN))
        {
            double offset = getOffset();

            if(offset > 0.1)
            {
                dt.runMotors(highSpeed, lowSpeed);
            }
            else if(offset < 0.1)
            {
                dt.runMotors(-lowSpeed, -highSpeed);
            }
            else if((-0.1 < offset) && (offset < 0.1) && !alignOnly)
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
        List<TargetInfo> targets = update.getTargets();
        try
        {
            this.height = targets.get(0).getHeight();
            this.offset = targets.get(0).getOffset();

            if(this.height == 0)
            {
                this.height = 1023;
            }

            if(this.offset == 0)
            {
                this.offset = 1023;
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            this.height = 1023;
            this.offset = 1023;
        }


        this.fps = 1 / (update.getCapturedAgoMs() * 1000);
    }


}