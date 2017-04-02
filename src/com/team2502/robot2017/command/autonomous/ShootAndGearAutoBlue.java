package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoBlue extends CommandGroup
{
    public ShootAndGearAutoBlue()
    {
<<<<<<< HEAD
        addSequential(new ShootCommand(2D,false));
        addSequential(new ShootCommand(5.0D, true));   
        addSequential(new NavXMoveCommand(112,1,1));
        addSequential(new DriveTimeCommand(1, 0.66));
        addSequential(new NavXMoveCommand(62, 1.25));
        addSequential(new WaitCommand(1));
        addSequential(new AutoVCommand(5, false, -.1, .3));
        addSequential(new NavXMoveCommand(10,1, .2));
        addSequential(new NavXMoveCommand(-10,1, .2));
=======
      addSequential(new ShootCommand(1, false));  
      addSequential(new ShootCommand(3.5, true));
      addSequential(new NavXMoveCommand(-15, .75, .75));//-15,1,.5
      addSequential(new WaitCommand(1));
      addSequential(new DriveTimeCommand(.2, -1));
      addSequential(new NavXMoveCommand(90, 1, .5));//90,1,.5
      addSequential(new DriveTimeCommand(.85, 1));//this is for distance of 114.3 in
      addSequential(new NavXMoveCommand(60, 1, .5));
      addSequential(new AutoVCommand(3, false, -.2, .3));
//    addSequential(new DriveTimeCommand(.55, -.2));
      addSequential(new NavXMoveCommand(10, 1, .2));
      addSequential(new WaitCommand(.5));
      addSequential(new NavXMoveCommand(-10, 1, .3));
//      addSequential(new NavXMoveCommand(10, 1, .2));
      addSequential(new DriveTimeCommand(.5, .2));
        
>>>>>>> develop-ritikm
    }
}
