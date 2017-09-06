package com.team2502.robot2017.command.teleop;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;


public class ClimberDriveCommand extends Command
{
    public ClimberDriveCommand()
    {
        requires(Robot.CLIMBER);
    }

    @Override
    protected void execute()
    {
        Robot.CLIMBER.drive();
    }

    @Override
    protected boolean isFinished()
    {
        return false;
    }
}
