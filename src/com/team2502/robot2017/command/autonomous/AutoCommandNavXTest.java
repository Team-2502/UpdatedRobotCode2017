package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandNavXTest extends CommandGroup
{
    /**
     * For NavX testing
     */
    public AutoCommandNavXTest()
    {
//    	addSequential(new NavXMoveCommand());
//    	addSequential(new WaitCommand(1));
//    	addSequential(new NavXMoveCommand(18));
    	addSequential(new NavXMoveCommand(90,0)); // turns left
    }
}