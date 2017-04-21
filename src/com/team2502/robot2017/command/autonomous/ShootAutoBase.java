package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAutoBase extends CommandGroup {
	/**
	 * The base command group for all autonomi(plural autonomous) that shoot.
	 * <hr>
	 * Shoots balls into boiler, FTC aligns against boiler, pulls away from boiler, and straightens to be parallel with the long side of the field
	 * <hr>
	 * @param allianceColor The color of the alliance("red" or "blue"). If it is not either "red" or "blue", the robot will turn 180.
	 */
	private double angle = -60;
	static String red = "red";
	static String blue = "blue";
	static String redU = red.toUpperCase();
	static String blueU = blue.toUpperCase();
	public ShootAutoBase(String allianceColor)
	{
		// shoot balls into boiler
	    addSequential(new ShootCommand(1, false));  
	    addSequential(new ShootCommand(3, true));

	    // FTC align against boiler
	    addSequential(new DriveTimeCommand(1, 1));  	
	    addSequential(new WaitCommand(0.25));
	    
	    // Pull away from boiler
	    addSequential(new DriveTimeCommand(.5/3, -1));
	    String allianceColorU = allianceColor.toUpperCase();
	    if(allianceColorU.hashCode() == redU.hashCode()) { addSequential(new NavXMoveCommand(-angle, 1.5)); }
	    else if(allianceColorU.hashCode() == blueU.hashCode()) { addSequential(new NavXMoveCommand(angle, 1.5)); }
	    else { addSequential(new NavXMoveCommand(180, 10)); }
	}
}