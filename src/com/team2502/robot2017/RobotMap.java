package com.team2502.robot2017;

@SuppressWarnings({ "WeakerAccess" })
public class RobotMap
{

    private static final int UNDEFINED = -1;

    private RobotMap() {}

    public static final class Joystick
    {
        public static final int JOYSTICK_DRIVE_LEFT = 1;
        public static final int JOYSTICK_DRIVE_RIGHT = 0;
        public static final int JOYSTICK_FUNCTION = 2;

        private Joystick() {}

        public static final class Button
        {
            public static final int SWITCH_DRIVE_TRANSMISSION = 1;
            public static final int SHOOTER_TOGGLE = 5;

            public static final int CLIMBER =  8;
            public static final int SHOOTER = 1;
            public static final int RESET_ENC_POS = 9; // temporary and only exists for auton development
            
            // For increasing/decreasing the feed rates on various portions of the shooter. Good4Tuning
            public static final int ADD_AGITATOR_SPEED = 6;
            public static final int SUB_AGITATOR_SPEED = 7;
            public static final int ADD_COLSON_SPEED = 8;
            public static final int SUB_COLSON_SPEED = 9;
            public static final int ADD_BANEBOT_SPEED = 11;
            public static final int SUB_BANEBOT_SPEED = 10;

            public static final int ADD_FLYWHEEL_SPEED = 10;
            public static final int SUB_FLYWHEEL_SPEED = 9;
            
            public static final int AUTO_SELECT = 11;
            


            private Button() {}
        }
    }

    public static final class Electrical
    {
        private Electrical() {}

        public static final int PRESSURE_SENSOR = 0;
        public static final int DISTANCE_SENSOR = 1;
    }

    public static final class Motor
    {
    	public static final int LEFT_TALON_0 = 2;
        public static final int LEFT_TALON_1 = 4;
        public static final int RIGHT_TALON_0 = 1;
        public static final int RIGHT_TALON_1 = 3;
        public static final int FLYWHEEL_TALON_0 = 5;
        public static final int FEEDER_TALON_0 = 6; //colson
        public static final int FEEDER_TALON_1 = 7; //banebot
        public static final int ACTIVE_INTAKE = 8;
        public static final int AGITATOR = 9;

        private Motor() {}
    }

    public static final class Solenoid
    {

        // TRANSMISSION is for shifting drivetrain gear ratio.
        public static final int TRANSMISSION_SWITCH = 0;

        // GEARBOX is for the actual box that carries gears.
        public static final int CLIMBER_SOLENOID = 1;

        private Solenoid() {}
    }
}
