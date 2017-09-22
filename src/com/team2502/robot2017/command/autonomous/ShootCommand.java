package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.TimedCommand;

public class ShootCommand extends TimedCommand
{
    private ShooterSubsystem shooterSubsystem;
    private boolean both = false;

    /**
     * @param runTime Time to run for in seconds.
     */

    public ShootCommand(double runTime, boolean both)
    {
        super(runTime);
        requires(Robot.SHOOTER);
        this.shooterSubsystem = Robot.SHOOTER;
        this.both = both;
    }

    @Override
    protected void execute()
    {
        if (both) { shooterSubsystem.feed(); }
        shooterSubsystem.turnOnFlywheel();
    }

    @Override
    protected void end() { shooterSubsystem.stop(); }

    @Override
    protected void interrupted() { end(); }
}