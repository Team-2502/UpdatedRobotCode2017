package com.team2502.robot2017;

import com.team2502.robot2017.command.*;
import com.team2502.robot2017.command.autonomous.AutoVCommand;
import com.team2502.robot2017.command.setup.SwitchAutonomousCommand;

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

    public static final Button RESET_ENC_POSITION = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.RESET_ENC_POS);
    public static final Button CLIMBER = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.CLIMBER);
    
    public static final Button ADD_AGITATOR_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.ADD_AGITATOR_SPEED);
    public static final Button SUB_AGITATOR_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.SUB_AGITATOR_SPEED);
    
    public static final Button ADD_COLSON_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.ADD_COLSON_SPEED);
    public static final Button SUB_COLSON_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.SUB_COLSON_SPEED);
    
    public static final Button ADD_BANEBOT_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.ADD_BANEBOT_SPEED);
    public static final Button SUB_BANEBOT_SPEED = new JoystickButton(JOYSTICK_DRIVE_LEFT, RobotMap.Joystick.Button.SUB_BANEBOT_SPEED);
    
    public static final Button ADD_FLYWHEEL_SPEED = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.ADD_FLYWHEEL_SPEED);
    public static final Button SUB_FLYWHEEL_SPEED = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.SUB_FLYWHEEL_SPEED);
    
    public static final Button VISION_ALIGN = new JoystickButton(JOYSTICK_DRIVE_RIGHT, RobotMap.Joystick.Button.VISION_ALIGN);
    
    public static final Button SET_AUTO = new JoystickButton(JOYSTICK_FUNCTION, RobotMap.Joystick.Button.AUTO_SELECT);

    static
    {
        SWITCH_DRIVE_TRANSMISSION.whenPressed(new SwitchDriveTransmissionCommand());

		RESET_ENC_POSITION.whenPressed(new ResetEncodersCommand()); 
		
        CLIMBER.whenPressed(new ClimberCommand());

        SET_AUTO.whenPressed(new SwitchAutonomousCommand());

        ADD_AGITATOR_SPEED.whenPressed(new ChangeSpeedAgitatorCommand(true));
        SUB_AGITATOR_SPEED.whenPressed(new ChangeSpeedAgitatorCommand(false));
        
        ADD_COLSON_SPEED.whenPressed(new ChangeSpeedColsonCommand(true));
        SUB_COLSON_SPEED.whenPressed(new ChangeSpeedColsonCommand(false));
        
        ADD_BANEBOT_SPEED.whenPressed(new ChangeSpeedBanebotCommand(true));
        SUB_BANEBOT_SPEED.whenPressed(new ChangeSpeedBanebotCommand(false));
        
        ADD_FLYWHEEL_SPEED.whenPressed(new ChangeSpeedFlywheelCommand(true));
        SUB_FLYWHEEL_SPEED.whenPressed(new ChangeSpeedFlywheelCommand(false));
        
        VISION_ALIGN.toggleWhenPressed(new AutoVCommand(2));
        
        
    }

    public static void init() {}

    private OI() {}
}
