package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by Mky135 on 9/13/17.
 */
public class SetHopperCommand extends Command
{
    public SetHopperCommand(boolean out)
    {
        hopper = Robot.HOPPER;
        this.out = out;
        requires(hopper);
    }

    HopperSubsystem hopper;
    boolean out;

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