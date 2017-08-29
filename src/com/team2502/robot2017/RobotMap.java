package com.team2502.robot2017;

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
            public static final int SWITCH_HOPPER = 2; //TODO: Figure out which button to use
            public static final int SHOOTER_TOGGLE = 5;
            public static final int VISION_ALIGN = 3;

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


            private Button() {}
        }
    }

    public static final class Electrical
    {
        public static final int PRESSURE_SENSOR = 0;
        public static final int DISTANCE_SENSOR = 1;

        private Electrical() {}
    }

    public static final class Motor
    {

    	public static final int LEFT_TALON_0 = 4;
        public static final int LEFT_TALON_1 = 12;
        public static final int RIGHT_TALON_0 = 8;
        public static final int RIGHT_TALON_1 = 7;
        public static final int FLYWHEEL_TALON_0 = 5; //Top left
        public static final int FLYWHEEL_TALON_1 = 11;//Bottom Left
        public static final int FLYWHEEL_TALON_2 = 1;//Top Right
        public static final int FLYWHEEL_TALON_3 = 2;//Bottom Right
        public static final int FEEDER_TALON_0 = 6; //colson8
        public static final int FEEDER_TALON_1 = 13; //banebot
        public static final int ACTIVE_INTAKE = 9;
        public static final int AGITATOR = 10;
		public static final int CLIMBER_LEFT = 14;
		public static final int CLIMBER_RIGHT = 3;

		public static final int ALLOWABLE_LOOP_ERR = 5; // in encoder pulses
	    public static final int TIME_TO_STOP = 1500; // amount of milliseconds to be on target to stop contolling with PID4

        // Feet per second
        public static final int LOW_GEAR_SPEED = 10;
        public static final int HIGH_GEAR_SPEED = 16;

        // RPM
        public static final double SHIFT_UP_THRESHOLD = ((8.5D) * 60 * 12) / (4*Math.PI) ;
        public static final double SHIFT_DOWN_THRESHOLD = ((8.4D) * 60 * 12) / (4*Math.PI);
        // 850rmpm

        private Motor() {}
    }

    public static final class Solenoid
    {
        public static final int TRANSMISSION_SWITCH = 0;

        public static final int HOPPER_SOLENOID = 1;

        private Solenoid() {}
    }
}