package com.team2502.robot2017.command.autonomous;
import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ActiveIntakeCommand extends Command
{
    public ActiveIntakeCommand(double timeout)
    {
        super(timeout);
        requires(Robot.ACTIVE);
    }

    protected void execute()
    {
        Robot.ACTIVE.activeTalon.set(1);
    }

    protected void end()
    {
        Robot.ACTIVE.activeTalon.set(0);
    }

    @Override
    protected boolean isFinished()
    {
        return isTimedOut();
    }
}
