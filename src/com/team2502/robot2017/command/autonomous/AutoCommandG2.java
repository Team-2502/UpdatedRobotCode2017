package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.autonomous.ShootCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG2 extends CommandGroup
{
    /** 
     * For shooting
     */
    public AutoCommandG2()
    {
      addSequential(new ShootCommand(2D,false));
      addSequential(new ShootCommand(5.0D, true));
      addSequential(new EncDriveTurn(1, -1));
//      addSequential(new NavXMoveCommand(-90,2D));
      addSequential(new EncDriveDistanceCommand());
    }
}