package com.team2502.robot2017;

import com.team2502.robot2017.command.autonomous.TestAutoCommand;
import com.team2502.robot2017.command.autonomous.group.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class AutoSwitcher
{
    private static SendableChooser<AutoMode> autoChooser;

    static void putToSmartDashboard()
    {
        autoChooser = new SendableChooser<>();

        for (int i = 0; i < AutoMode.values().length; i++)
        {
            AutoMode mode = AutoMode.values()[i];
            if (i == 0) { autoChooser.addDefault(mode.name, mode); } else { autoChooser.addObject(mode.name, mode); }
        }

        SmartDashboard.putData("auto_modes", autoChooser);
    }

    static Command getAutoInstance() { return autoChooser.getSelected().getInstance(); }

    public enum AutoMode
    {

        GEAR_BACKUP_CENTER("Center Backup gear", GearBackupCenter::new),
        BASE_LINE("Base Line", BaseLineAuto::new),
        GEAR_CENTER("Center Gear", GearAutoCenter::new),
        GEAR_LEFT("Left Gear", GearAutoLeft::new),
        GEAR_RIGHT("Right Gear", GearAutoRight::new),

        BOILER_RED("Red Boiler", BoilerRed::new),
        TESTAUTO("DO NOT USE ME!", TestAutoCommand::new);

        private CommandFactory commandFactory;
        private String name;

        AutoMode(String name, CommandFactory commandFactory)
        {
            this.commandFactory = commandFactory;
            this.name = name;
        }

        public Command getInstance()
        {
            return commandFactory.getInstance();
        }
    }

    private interface CommandFactory{
        Command getInstance();
    }
}