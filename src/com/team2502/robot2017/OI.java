package com.team2502.robot2017;

import com.team2502.robot2017.command.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

@SuppressWarnings("WeakerAccess")
public final class OI
{
    public static final Joystick JOYSTICK_DRIVE_LEFT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_LEFT);
    public static final Joystick JOYSTICK_DRIVE_RIGHT = new Joystick(RobotMap.Joystick.JOYSTICK_DRIVE_RIGHT);
    public static final Joystick JOYSTICK_FUNCTION = new Joystick(RobotMap.Joystick.JOYSTICK_FUNCTION);
    public static final Button SWITCH_DRIVE_TRANSMISSION = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.SWITCH_DRIVE_TRANSMISSION);
    public static final Button TOP_GEAR_BOX = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.MOVE_TOP);
    public static final Button Kick_Gear = new JoystickButton (JOYSTICK_FUNCTION,RobotMap.Joystick.Button.KICK_GEAR);
    public static final Button LEVER = new JoystickButton (JOYSTICK_FUNCTION,RobotMap.Joystick.Button.LEVER);
    public static final Button MOVE_BOX = new JoystickButton (JOYSTICK_FUNCTION,RobotMap.Joystick.Button.MOVE_BOX);
    public static final Button CLIMBER = new  JoystickButton (JOYSTICK_FUNCTION,RobotMap.Joystick.Button.CLIMBER);
    public static final Button ONLY_AGITATOR = new  JoystickButton (JOYSTICK_FUNCTION,RobotMap.Joystick.Button.ONLY_AGITATOR);
    
    static
    {
        SWITCH_DRIVE_TRANSMISSION.whenPressed(new SwitchDriveTransmissionCommand());
        TOP_GEAR_BOX.whenPressed(new GearCommandTop()); // looks functional ish
        LEVER.whenPressed(new GearCommandLever());
        MOVE_BOX.whenPressed(new GearCommandPushBox());
        Kick_Gear.whenPressed(new GearCommandPushGear());
        CLIMBER.whenPressed(new ClimberCommand());
//        ONLY_AGITATOR.whileHeld(new OnlyAgitatorCommand());
    }

    public static void init() {}

    private OI() {}
}
