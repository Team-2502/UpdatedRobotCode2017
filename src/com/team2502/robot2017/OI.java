package com.team2502.robot2017;

import com.team2502.robot2017.command.*;

import com.team2502.robot2017.command.autonomous.OneMethodCommand;

import com.team2502.robot2017.command.teleop.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public final class OI
{
    public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_LEFT);
    public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_RIGHT);
    public static final Joystick JOYSTICK_FUNCTION = new Joystick(RobotMap.Joystick.JOYSTICK_FUNCTION);

    private static final Button SWITCH_DRIVE_TRANSMISSION = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.SWITCH_DRIVE_TRANSMISSION);
    private static final Button SWITCH_HOPPER = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.SWITCH_HOPPER);

    private static final Button RESET_ENC_POSITION = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.RESET_ENC_POS);

    private static final Button ADD_AGITATOR_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.ADD_AGITATOR_SPEED);
    private static final Button SUB_AGITATOR_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.SUB_AGITATOR_SPEED);

    private static final Button ADD_COLSON_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.ADD_COLSON_SPEED);
    private static final Button SUB_COLSON_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.SUB_COLSON_SPEED);

    private static final Button ADD_BANEBOT_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.ADD_BANEBOT_SPEED);
    private static final Button SUB_BANEBOT_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.SUB_BANEBOT_SPEED);

    private static final Button ADD_FLYWHEEL_SPEED = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.ADD_FLYWHEEL_SPEED);
    private static final Button SUB_FLYWHEEL_SPEED = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.SUB_FLYWHEEL_SPEED);

    private static final Button DISABLE_AUTOSHIFTING = new JoystickButton(JOYSTICK_DRIVE_RIGHT, 7);

	private static final Button VISION_ALIGN = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.VISION_ALIGN);
	private static final Button VISION_BOILER = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.VISION_BOILER);

    private static final Button CLIMBER = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.CLIMBER);


    static
    {
//        SWITCH_DRIVE_TRANSMISSION.whenPressed(new SwitchDriveTransmissionCommand());
        // Above is obsolete due to automatic transmission

//        SWITCH_HOPPER.whenPressed(new SwitchHopperCommand());
        SWITCH_HOPPER.whenPressed(new OneMethodCommand(Robot.HOPPER, (() -> Robot.HOPPER.setHopper(true))));

        SWITCH_HOPPER.whenPressed(new SwitchHopperCommand());

        RESET_ENC_POSITION.whenPressed(new ResetEncodersCommand());

        ADD_AGITATOR_SPEED.whenPressed(new ChangeSpeedAgitatorCommand(true));
        SUB_AGITATOR_SPEED.whenPressed(new ChangeSpeedAgitatorCommand(false));

        ADD_COLSON_SPEED.whenPressed(new ChangeSpeedColsonCommand(true));
        SUB_COLSON_SPEED.whenPressed(new ChangeSpeedColsonCommand(false));

        ADD_BANEBOT_SPEED.whenPressed(new ChangeSpeedBanebotCommand(true));
        SUB_BANEBOT_SPEED.whenPressed(new ChangeSpeedBanebotCommand(false));

        ADD_FLYWHEEL_SPEED.whenPressed(new ChangeSpeedFlywheelCommand(true));
        SUB_FLYWHEEL_SPEED.whenPressed(new ChangeSpeedFlywheelCommand(false));

        DISABLE_AUTOSHIFTING.whenPressed(new DisableAutoShifting());

        VISION_ALIGN.whileHeld(new TeleopVisionCommand());
        VISION_BOILER.whileHeld(new TeleopBoilerDist());
    }

    public static void init() {}

    public static boolean joysThreshold(double threshold, boolean above)
    {
        if(above)
        {
            return Math.abs(OI.JOYSTICK_DRIVE_RIGHT.getY()) > threshold && Math.abs(OI.JOYSTICK_DRIVE_LEFT.getY()) > threshold;
        }
        else
        {
            return Math.abs(OI.JOYSTICK_DRIVE_RIGHT.getY()) < threshold && Math.abs(OI.JOYSTICK_DRIVE_LEFT.getY()) < threshold;
        }
    }

    private OI() {}
}