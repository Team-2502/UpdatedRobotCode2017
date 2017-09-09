package com.team2502.robot2017.command;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Created by ritik on 8/22/17.
 */
public class RunMultipleMotors extends CommandGroup
{
    public RunMultipleMotors()
    {
        for (int id = 0; id <= 14; id++)
        {
            addSequential(new RunAMotor(id));
            addSequential(new WaitCommand(5));
            System.out.println("Motor ID " + id);
        }
    }
}
