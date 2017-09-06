package com.team2502.robot2017.command;

import com.team2502.robot2017.Robot;
import com.team2502.robot2017.subsystem.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ChangeSpeedFlywheelCommand extends InstantCommand
{
    private final ShooterSubsystem shooterSubsystem;

    private boolean checkIsAdd;

    /**
     * Manually change the flywheel speed
     *
     * @param isAdd Boolean to indicate whether to increase or decrease the flywheel speed
     */
    public ChangeSpeedFlywheelCommand(boolean isAdd)
    {
        requires(Robot.SHOOTER);
        shooterSubsystem = Robot.SHOOTER;

        checkIsAdd = isAdd;
    }

    @Override
    protected void initialize() { shooterSubsystem.changeSpeedFlywheel(checkIsAdd); }

}