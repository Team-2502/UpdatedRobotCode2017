package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class SetHopperCommand extends Command
{
    HopperSubsystem hopper;
    boolean out;
    public SetHopperCommand(boolean out)
    {
        hopper = Robot.HOPPER;
        this.out = out;
        requires(hopper);
    }

    @Override
    protected void initialize()
    {
        hopper.setHopper(out);
        System.out.println(out ? "Did a Hopper Expand" : "Did a Hopper Contract");
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }
}