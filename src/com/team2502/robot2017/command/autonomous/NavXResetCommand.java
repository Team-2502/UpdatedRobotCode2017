package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class NavXResetCommand extends InstantCommand
{
    protected void initialize()
    {
        Robot.NAVX.reset();
    }
}
