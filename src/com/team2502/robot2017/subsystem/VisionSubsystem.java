package com.team2502.robot2017.subsystem;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


// Implementing ITableListener is necessary for having the listener work, do not remove!
public class VisionSubsystem extends Subsystem
{
    static NetworkTable visionTable;

    double offset;
    double width;
    double height;

    @Override
    public void initDefaultCommand()
    {
        NetworkTable.setServerMode();
        NetworkTable.shutdown();
        visionTable = NetworkTable.getTable("PiVision");

    }

    //offset will be negative if to left, positive if to right
    public double getOffset() { return visionTable.getNumber("robot_offset", 1023); }
    public double getFPS() { return visionTable.getNumber("fps", 1023); }


}