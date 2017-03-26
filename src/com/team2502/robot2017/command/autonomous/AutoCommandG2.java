package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.ShootCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommandG2 extends CommandGroup
{
    public AutoCommandG2()
    {
//        addSequential(new Climber
//        addSequential(new PushGearCommand(true));
//    	addSequential(new DriveTimeCommand((1/196.5)*114.3));
//    	addSequential(new NavXMoveCommand(-90, 3D));
//    	addSequential(new DriveTimeCommand((1/196.5)*78.5));

      
      addSequential(new ShootCommand(2D,false));
      addSequential(new ShootCommand(5.0D, true));
      addSequential(new EncDriveTurn(1, -1));
//      addSequential(new NavXMoveCommand(-90,2D));
      addSequential(new EncDriveDistanceCommand());
    }
}
    