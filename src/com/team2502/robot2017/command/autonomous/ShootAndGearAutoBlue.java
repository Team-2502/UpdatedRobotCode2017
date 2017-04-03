package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoBlue extends CommandGroup
{
    public ShootAndGearAutoBlue()
    {
      
      addSequential(new ShootAutoBase("blue"));
         
      
      //Forward
      addSequential(new DriveTimeCommand(.85/1.5, 1));
      
      // Point at gear peg
      addSequential(new NavXMoveCommand(75, 1));
      
      // Drive at gear peg
      addSequential(new DriveTimeCommand(0.25, 0.7));
      
      // Stare at and intimidate the gear peg
      addSequential(new AutoVCommand(2, false, -.2, .3));

      addSequential(new NavXMoveCommand(10, 0.1)); // wiggle
      addSequential(new NavXMoveCommand(-20, 0.1)); // woggle
      
        

    }
}
