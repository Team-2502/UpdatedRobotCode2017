package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class SwitchHopperCommand extends InstantCommand
{
    private final HopperSubsystem hopperSubsystem;

    public SwitchHopperCommand()
    {
        requires(Robot.HOPPER);
        hopperSubsystem = Robot.HOPPER;
    }

    @Override
    protected void initialize() { hopperSubsystem.switchHopper();}
}