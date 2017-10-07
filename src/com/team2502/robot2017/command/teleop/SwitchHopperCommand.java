package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class SwitchHopperCommand extends InstantCommand
{
<<<<<<< HEAD
    private final HopperSubsystem hopperSubsystem;
=======
    private final HopperSubsystem Hopper;
>>>>>>> develop-Mky135

    public SwitchHopperCommand()
    {
        requires(Robot.HOPPER);
<<<<<<< HEAD
        hopperSubsystem = Robot.HOPPER;
    }

    @Override
    protected void initialize() { hopperSubsystem.switchHopper();}
=======
        Hopper = Robot.HOPPER;
    }

    @Override
    protected void initialize() { Hopper.setHopper(!Hopper.getOut()); }
>>>>>>> develop-Mky135
}