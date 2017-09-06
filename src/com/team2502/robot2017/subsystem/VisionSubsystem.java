package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.OI;
import com.team2502.robot2017.RobotMap;
import com.team2502.robot2017.command.teleop.TeleopVisionCommand;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

// Implementing ITableListener is necessary for having the listener work, do not remove!
public class VisionSubsystem extends Subsystem implements PIDSource
{
    static NetworkTable visionTable;

    private Relay visionLight = new Relay(0);

    PIDSourceType sourceType = PIDSourceType.kDisplacement;

    public VisionSubsystem()
    {
        NetworkTable.setServerMode();
        NetworkTable.shutdown();
        visionTable = NetworkTable.getTable("PiVision");
    }

    @Override
    public void initDefaultCommand() { setDefaultCommand(new TeleopVisionCommand()); }


    /**
     * @return the offset calculated by the pi
     */
    public double getOffset() { return visionTable.getNumber("robot_offset", 1023); }

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
    public double getFPS() { return visionTable.getNumber("fps", 1023); }

    public double getHeight() { return visionTable.getNumber("height", 1023); }
    
    public void turnOffVisionLight() { visionLight.set(Relay.Value.kOff); }

    public void turnOnVisionLight() { visionLight.set(Relay.Value.kOn); }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource)
    {
        sourceType = pidSource;
    }

    @Override
    public PIDSourceType getPIDSourceType()
    {
        return sourceType;
    }

    @Override
    public double pidGet()
    {
        return getOffset();
    }
}