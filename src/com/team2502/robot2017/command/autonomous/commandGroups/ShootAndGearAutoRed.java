package com.team2502.robot2017.command.autonomous.commandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team2502.robot2017.command.autonomous.*;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndGearAutoRed extends CommandGroup
{
    public ShootAndGearAutoRed()
    {
      addSequential(new ShootAutoBase("red"));
      addSequential(new EncoderDrive(60, 2));
      addSequential(new NavXMoveCommand(-60, 1));
      addSequential(new AutoVCommand(4, false, -0.2, 0.3));
      addSequential(new Wiggle());
    }
}