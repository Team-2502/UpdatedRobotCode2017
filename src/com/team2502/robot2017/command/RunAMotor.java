package com.team2502.robot2017.command;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Created by ritik on 8/22/17.
 */

public class RunAMotor extends TimedCommand
{
    CANTalon aMotor;
    public RunAMotor(int id)
    {

        super(0.5);
        aMotor = new CANTalon(id);
        aMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
    }

    protected void execute()
    {
        aMotor.set(0.5);
    }

    protected void end()
    {
        aMotor.set(0);
    }

    protected void interrupted() { end(); }
}
