package com.team2502.robot2017.command.autonomous;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command
{
    public ShooterSubsystem shooterSubsystem;
    private long runTime;
    private long startTime;
    public boolean both = false;

    /**
     * @param runTime Time to run for in milliseconds.
     */
    public ShootCommand(long runTime, boolean both)
    {
        this.runTime = runTime;
        requires(Robot.SHOOTER);
        this.shooterSubsystem = Robot.SHOOTER;
        this.both = both;
    }

    /**
     * @param runTime Time to run for in seconds.
     */
    public ShootCommand(double runTime, boolean both)
    {
        this((long) (runTime * 1000), both);
    }

    @Override
    protected void initialize()
    {
        startTime = System.currentTimeMillis();
    }

    @Override
    protected void execute()
    {   
        if(both)
        {
            
            shooterSubsystem.feed();
        }
        shooterSubsystem.turnOnFlywheel(); 
        
    }

    @Override
    protected boolean isFinished()
    {
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