package com.team2502.robot2017.subsystem;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.Relay;

// Implementing ITableListener is necessary for having the listener work, do not remove!
public class VisionSubsystem extends Subsystem
{
    static NetworkTable visionTable;
    
    private Relay visionLight = new Relay(0);

    double offset;
    double width;
    double height;

    public VisionSubsystem(){
        NetworkTable.setServerMode();
        NetworkTable.shutdown();
        visionTable = NetworkTable.getTable("PiVision");
    }
    
    @Override
    public void initDefaultCommand() {}

    //offset will be negative if to left, positive if to right
    
    /**
     * @return the offset calculated by the pi
     */
    public double getOffset() { return visionTable.getNumber("robot_offset", 1023); }
    
    /**
     * @return the FPS of the Pi's vision processing
     */
    public double getFPS() { return visionTable.getNumber("fps", 1023); }
    
    public void turnOffVisionLight() { visionLight.set(Relay.Value.kOff); }
    
    public void turnOnVisionLight() { visionLight.set(Relay.Value.kOn); }
}