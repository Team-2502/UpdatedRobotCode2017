package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.command.GearCommandPushGear;
import com.team2502.robot2017.command.LeverCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

@SuppressWarnings("WeakerAccess")
public class GearCommandG extends CommandGroup
{
    public GearCommandG(boolean on, boolean lever)
    {
        addSequential(new LeverCommand(lever));
        addSequential(new WaitCommand(1D));
        addSequential(new PushGearCommand(on));
        addSequential(new PushBoxCommand(on));
    }
}
