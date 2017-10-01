package com.team2502.robot2017.command.autonomous;


import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Created by ritik on 10/1/17.
 */
public class OneMethodCommand extends InstantCommand
{

    final Runnable method;
    /**
     * Fun fact: All method references and lambda expressions are Runnables
     * <br  /><br  />
     * So, if you wanted to do HopperSubsystem.switchHopper with this command, you'd do
     * <br  /><br  />
     * new OneMethodCommand(Robot.HOPPER, Robot.HOPPER::switchHopper);
     * <br  /><br  />
     * If your method happens to have parameters you'd like to fill, you may use a lambda expression. For example,
     * <br  /><br  />
     * new OneMethodCommand(Robot.HOPPER, (() -> Robot.Hopper.setHopper(true)));
     * <br  /><hr  />
     * @param subsystem The subsystem to require
     * @param method The method from subsystem to run
     */
    public OneMethodCommand(Subsystem subsystem, Runnable method)
    {
        requires(subsystem);
        this.method = method;
    }

    @Override
    protected void initialize()
    {
        method.run();
    }
}
