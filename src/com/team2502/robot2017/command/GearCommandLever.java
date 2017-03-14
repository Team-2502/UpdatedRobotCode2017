package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import edu.wpi.first.wpilibj.command.Command;
import com.team2502.robot2017.subsystem.GearBoxSubsystem;

public class GearCommandLever extends Command
{   
    private final GearBoxSubsystem GearBoxSubsystem;
    public GearCommandLever()
    {
        requires(Robot.GEAR_BOX);
        GearBoxSubsystem = Robot.GEAR_BOX;   
    }
    
    protected void initialize()
    {

    }

    @Override
    protected void execute()
    {
        GearBoxSubsystem.switchReleased();
    }

    @Override
    protected boolean isFinished()
    {
        return true;
    }

    @Override
    protected void end()
    {
    }

    @Override
    protected void interrupted()
    {

    }
}