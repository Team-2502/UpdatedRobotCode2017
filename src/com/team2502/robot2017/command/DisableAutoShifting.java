package com.team2502.robot2017.command;

import com.team2502.robot2017.command.teleop.DriveCommand;
import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Created by Miguel on 8/5/17.
 */

public class DisableAutoShifting extends InstantCommand
{
    public DisableAutoShifting() {}

    @Override
    protected void initialize() { DriveCommand.toggleAutoShifting(); }

}
