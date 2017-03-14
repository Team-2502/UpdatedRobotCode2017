package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class FeedShooterCommand extends Command
{
    public ShooterSubsystem shooterSubsystem;
    private long runTime;
    private long startTime;

    /**
     * @param runTime Time to run for in milliseconds.
     */
    public FeedShooterCommand(long runTime)
    {
        this.runTime = runTime;
        requires(Robot.SHOOTER);
        this.shooterSubsystem = Robot.SHOOTER;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public FeedShooterCommand(double runTime)
    {
        this((long) (runTime * 1000));
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {
        shooterSubsystem.feed();
    }

    @Override
    protected boolean isFinished()
    {
//        System.out.println("Time@" + this.hashCode() + ": " + (System.currentTimeMillis() - startTime));
        return (System.currentTimeMillis() - startTime > runTime);
    }

    @Override
    protected void end()
    {
        shooterSubsystem.stop();
    }

    @Override
    protected void interrupted()
    {
        end();
    }
}