package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoBlue extends CommandGroup
{
    public ShootAndGearAutoBlue()
    {
      addSequential(new ShootAutoBase("blue"));

      //Forward
      addSequential(new EncoderDrive(53.254, 2));

      // Point at gear peg
      addSequential(new NavXMoveCommand(60, 2));

	  addSequential(new AutoVCommand(4, false, -0.2, 0.3));
      addSequential(new Wiggle());
    }
}