package com.team2502.robot2017;

import com.team2502.robot2017.command.RunMultipleMotors;
import com.team2502.robot2017.command.autonomous.*;
import com.team2502.robot2017.command.autonomous.commandGroups.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

class AutoSwitcher
{
    private static SendableChooser<AutoMode> autoChooser;

    public enum AutoMode
    {
        BLUE_BOILER("Blue hopper + boiler", BoilerBlue.class),
        TESTTRAJECTORY("Test Trajectory", TrajectoryTest.class),
        GEAR_CENTER("Center Gear", GearAutoCenter.class),
        GEAR_LEFT("Left Gear", GearAutoLeft.class),
        GEAR_RIGHT("Right Gear", GearAutoRight.class),

        RED_SHOOT_AND_GEAR("Red shoot and Gear", ShootAndGearAutoRed.class),
        RED_SHOOT_AND_BASELINE("Red shoot and Baseline", ShootAndBaseLineRed.class),

        BLUE_SHOOT_AND_GEAR("Blue shoot and Gear", ShootAndGearAutoBlue.class),
        BLUE_SHOOT_AND_BASELINE("Blue shoot and Baseline", ShootAndBaseLineBlue.class),


        RED_BOILER("Red hopper + boiler", BoilerRed.class),

        BASELINE("Baseline only", DriveTimeCommand.class),
        TESTAUTO("Boiler Test Vision", BoilerDistCommand.class);


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

    static void putToSmartDashboard()
    {
        autoChooser = new SendableChooser<AutoMode>();

        for(int i = 0; i < AutoMode.values().length; i++)
        {
            AutoMode mode = AutoMode.values()[i];
            if(i == 0) { autoChooser.addDefault(mode.name, mode); }
            else { autoChooser.addObject(mode.name, mode); }
        }

        SmartDashboard.putData("auto_modes", autoChooser);
    }

    static Command getAutoInstance() { return autoChooser.getSelected().getInstance(); }
}