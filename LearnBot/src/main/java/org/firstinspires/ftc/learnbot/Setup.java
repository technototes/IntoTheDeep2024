package org.firstinspires.ftc.learnbot;

import com.acmerobotics.dashboard.config.Config;

public class Setup {

    @Config
    public static class Connected {

        public static boolean DRIVEBASE = false;
        public static boolean TESTSUBSYSTEM = true;
        public static boolean MOTOR = false;
        public static boolean SERVO = true;
        public static boolean DISTANCE_SENSOR = false;
        public static boolean COLOR_SENSOR = false;
        public static boolean FLYWHEEL = false;
        public static boolean WEBCAM = false;
    }

    @Config
    public static class HardwareNames {

        public static String MOTOR = "liftmotor";
        public static String FLMOTOR = "fl";
        public static String FRMOTOR = "fr";
        public static String RLMOTOR = "rl";
        public static String RRMOTOR = "rr";
        public static String FLYWHEELMOTOR = "fly";
        public static String SERVO = "s";
        public static String IMU = "imu";
        public static String DISTANCE = "d";
        public static String COLOR = "c";
        public static String CAMERA = "camera";
    }

    @Config
    public static class GlobalSettings {

        public static double STICK_DEAD_ZONE = 0.1;
        public static double STRAIGHTEN_SCALE_FACTOR = 0.25;
        public static double STRAIGHTEN_RANGE = .15; // Fraction of 45 degrees...
        public static double TRIGGER_THRESHOLD = 0.7;
    }
}
