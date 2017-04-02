package com.team2502.robot2017.command.autonomous;

<<<<<<< HEAD
import com.team2502.robot2017.command.DriveTimeCommand;
import com.team2502.robot2017.command.FlywheelCommand;
<<<<<<< HEAD

=======
>>>>>>> origin/Develop
=======
import com.team2502.robot2017.command.autonomous.*;

>>>>>>> origin/Develop
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG1 extends CommandGroup
{
    public AutoCommandG1()
    {
<<<<<<< HEAD
        addSequential(new EncDriveDistanceCommand());
        addSequential(new WaitCommand(1D));
<<<<<<< HEAD
   
        addSequential(new WaitCommand(1D));

=======
        addSequential(new PushGearCommand(true));
        addSequential(new WaitCommand(1D));
        addSequential(new PushGearCommand(false));
>>>>>>> origin/Develop
        addSequential(new DriveTimeCommand(1D));
        
=======

        addSequential(new DriveTimeCommand(1.35D, .7));//1.5
        addSequential(new WaitCommand(1D));
>>>>>>> origin/Develop

        
    }
}
