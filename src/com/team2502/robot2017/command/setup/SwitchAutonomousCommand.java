package com.team2502.robot2017.command.setup;

import com.team2502.robot2017.DashboardData;
import edu.wpi.first.wpilibj.command.Command;

public class SwitchAutonomousCommand extends Command
{
    @Override
    protected void execute()
    {
        DashboardData.SELECTED_AUTONOMOUS += 1;
        if(DashboardData.SELECTED_AUTONOMOUS >= DashboardData.AUTONOMOUS_SELECTOR.size())
        {
        	DashboardData.SELECTED_AUTONOMOUS = 0;
        }
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }
}
