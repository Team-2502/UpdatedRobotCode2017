package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RightGearAutoBackupG extends CommandGroup {
    /**
     * BackUp - Gets the right gear in autonomous 
     */
    public RightGearAutoBackupG() 
    {
        addSequential(new DriveTimeCommand(1, 0.66));
        addSequential(new NavXMoveCommand(-62, 1.25));
        addSequential(new WaitCommand(1));
        addSequential(new AutoVCommand(10, false, .0, .3));
    }
    
}
