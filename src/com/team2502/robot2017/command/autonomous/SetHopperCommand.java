package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.HopperSubsystem;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Created by 64009334 on 9/2/17.
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
	}
	@Override
	protected boolean isFinished()
	{
		return true;
	}
}
