package com.team2502.robot2017.subsystem;

import com.team2502.robot2017.command.autonomous.*;
import com.team2502.robot2017.command.autonomous.commandGroups.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoSwitcherSubsystem
{
	private static SendableChooser<AutoMode> autoChooser;
	
	public enum AutoMode
	{
		GEAR_CENTER("Center Gear", GearAutoCenter.class),
		GEAR_LEFT("Left Gear", GearAutoLeft.class),
		GEAR_RIGHT("Right Gear", GearAutoRight.class),
		
		RED_SHOOT_AND_GEAR("Red shoot and Gear", ShootAndGearAutoRed.class),
		BLUE_SHOOT_AND_GEAR("Blue shoot and Gear", ShootAndGearAutoBlue.class),
		BASELINE("Baseline only", DriveTimeCommand.class),
	    ENC_DRIVE("Encoder testing", EncoderDrive.class);

		private Class<? extends Command> autoCommand;
		private String name;

		AutoMode(String name, Class<? extends Command> autoCommand)
		{
			this.autoCommand = autoCommand;
			this.name = name;
		}
		
		public Command getInstance()
		{
			Command instance;
			try { instance = autoCommand.newInstance(); }
			catch(InstantiationException | IllegalAccessException e) { return null; }
			return instance;
		}
	}
	
	public static void putToSmartDashboard()
	{
        autoChooser = new SendableChooser<AutoMode>();
        
        for (int i = 0; i < AutoMode.values().length; i++)
        {
            AutoMode mode = AutoMode.values()[i];
            if (i == 0) { autoChooser.addDefault(mode.name, mode); }
            else { autoChooser.addObject(mode.name, mode); }
        }

        SmartDashboard.putData("auto_modes", autoChooser);
    }

    public static Command getAutoInstance() { return autoChooser.getSelected().getInstance(); }
}