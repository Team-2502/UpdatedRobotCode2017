package com.team2502.robot2017.command.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class RightGearAutoBackupG extends CommandGroup {

    public RightGearAutoBackupG() 
    {
        addSequential(new DriveTimeCommand(.90, 0.75));
        addSequential(new WaitCommand(1));
        addSequential(new NavXMoveCommand(-60, 2));
        addSequential(new WaitCommand(0.5D));
        addSequential(new AutoVCommand(2, false, 0, 0.3));
    }
    
}
