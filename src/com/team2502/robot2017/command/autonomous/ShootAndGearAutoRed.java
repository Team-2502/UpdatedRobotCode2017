package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoRed extends CommandGroup
{
    public ShootAndGearAutoRed()
    {
      addSequential(new ShootAutoBase("red"));

      //Forward
      addSequential(new DriveTimeCommand(.85/1.5, 1));

      // Point at gear peg
      addSequential(new NavXMoveCommand(-75, 1));

      // Drive at gear peg
      addSequential(new DriveTimeCommand(0.25, 0.7));

      // Stare at and intimidate the gear peg
      addSequential(new AutoVisionCommand(2, -.2/.3));
      addSequential(new WaitCommand(1));
      addSequential(new NavXMoveCommand(10, 0.5)); // wiggle
      addSequential(new NavXMoveCommand(-20, 0.5)); // woggle
    }
}