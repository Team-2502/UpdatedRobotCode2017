package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.autonomous.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoCommandG1 extends CommandGroup
{
    /**
     * For testing 
     */
    public AutoCommandG1()
    {
<<<<<<< HEAD

        addSequential(new DriveTimeCommand(1.35D, .7));//1.5
        addSequential(new WaitCommand(1D));

        
=======
        
        addSequential(new EncDriveDistanceCommand());       
>>>>>>> develop-ritikm
    }
}
