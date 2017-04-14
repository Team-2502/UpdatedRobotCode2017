package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TestAutoCommand extends CommandGroup
{
	/**
	 * Handy autonomous command to use for testing
	 */
    public TestAutoCommand() { addSequential(new DriveTimeCommand(3D,.7)); }
}   