package com.team2502.robot2017.command.setup;

import com.team2502.robot2017.DashboardData;
import edu.wpi.first.wpilibj.command.Command;

public class SwitchDriveControlCommand extends Command
{
    @Override
    protected void execute()
    {
        DashboardData.SELECTED_DRIVE_TYPE += 1;
        if(DashboardData.SELECTED_DRIVE_TYPE >= DashboardData.DRIVE_CONTROL_SELECTOR.size()) DashboardData.SELECTED_DRIVE_TYPE = 0;
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }
}
