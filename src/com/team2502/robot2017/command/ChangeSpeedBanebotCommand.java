package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ChangeSpeedBanebotCommand extends InstantCommand
{
    private final ShooterSubsystem shooterSubsystem;

    private boolean checkIsAdd;

    /**
     * Instantiate the command that changes the banebot's target speed
     *
     * @param isAdd Boolean that asks add or subtract?
     */
    public ChangeSpeedBanebotCommand(boolean isAdd)
    {
        requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;

        checkIsAdd = isAdd;
    }

    @Override
    protected void initialize()
    {
        shooterSubsystem.changeSpeedBanebot(checkIsAdd);
    }
}