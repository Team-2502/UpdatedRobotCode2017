package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoBlue extends CommandGroup
{
    public ShootAndGearAutoBlue()
    {

      addSequential(new ShootCommand(1, false));  
      addSequential(new ShootCommand(3, true));
      
//      addSequential(new NavXMoveCommand(-15, .75, .15)); // turn against the boiler
      addSequential(new DriveTimeCommand(1, 1));
    	
      addSequential(new WaitCommand(0.25));
      
      addSequential(new DriveTimeCommand(.5/3, -1));
      
      addSequential(new NavXMoveCommand(140, 1.5));
      addSequential(new DriveTimeCommand(.85/1.5, 1));  //this is for distance of 114.3 in
      addSequential(new NavXMoveCommand(75, 1));
      
      addSequential(new DriveTimeCommand(0.25, 0.7));
      addSequential(new AutoVCommand(2, false, -.2, .3));
//      
//      
      addSequential(new NavXMoveCommand(10, 1, .2));
      addSequential(new WaitCommand(.5));
      addSequential(new NavXMoveCommand(-10, 1, .3));
      addSequential(new DriveTimeCommand(.5, .2));
        

    }
}
