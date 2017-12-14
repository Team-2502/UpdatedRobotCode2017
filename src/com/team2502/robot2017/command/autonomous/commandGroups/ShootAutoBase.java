package com.team2502.robot2017.command.autonomous.commandGroups;

import com.team2502.robot2017.command.autonomous.DriveTimeCommand;
import com.team2502.robot2017.command.autonomous.EncoderDriveCommand;
import com.team2502.robot2017.command.autonomous.NavXRotateCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * The first part of shooting autons that start at shooting position
 */
class ShootAutoBase extends CommandGroup
{
    /**
     * The base command group for all autonomi(plural autonomous) that shoot.
     * <hr>
     * Shoots balls into boiler, FTC aligns against boiler, pulls away from boiler, and straightens to be parallel with the long side of the field
     * <hr>
     *
     * @param allianceColor The color of the alliance("red" or "blue"). If it is not either "red" or "blue", the robot will turn 180.
     */
    ShootAutoBase(String allianceColor)
    {
        double angle = 135;
        String red = "RED";
        String blue = "BLUE";
        allianceColor = allianceColor.toUpperCase();

	    // FTC align against boiler

        addSequential(new DriveTimeCommand(2, 1, 0));
        addSequential(new WaitCommand(0.25));

        // Pull away from boiler
        addSequential(new EncoderDriveCommand(-20, 1)); //TODO: Convert to Encoders

        if (allianceColor.hashCode() == red.hashCode())
        {
            addSequential(new NavXRotateCommand(-angle, 2));
        } else if (allianceColor.hashCode() == blue.hashCode()) { addSequential(new NavXRotateCommand(angle, 2)); } else
        {
            addSequential(new EncoderDriveCommand(100000000, 999999999));
        } //TODO: Convert to Encoders
    }
}