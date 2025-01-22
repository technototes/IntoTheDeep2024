package org.firstinspires.ftc.hoops;

import com.acmerobotics.dashboard.config.Config;

public class Setup {

    @Config
    public static class Connected {

        public static boolean DRIVEBASE = true;
        public static boolean EXTERNAL_IMU = true;
        public static boolean LAUNCHER = true;
        public static boolean INTAKE = true;
    }

    @Config
    public static class HardwareNames {

        public static String FLMOTOR = "fl";
        public static String FRMOTOR = "fr";
        public static String RLMOTOR = "rl";
        public static String RRMOTOR = "rr";
        public static String IMU = "imu";
        public static String EXTERNAL_IMU = "adafruit-imu";

        public static String TOP_LAUNCH = "top";
        public static String BOTTOM_LAUNCH = "bottom";

        public static String INTAKE = "slurp";
    }

    @Config
    public static class GlobalSettings {

        public static double STICK_DEAD_ZONE = 0.1;
        public static double STRAIGHTEN_SCALE_FACTOR = 0.25;
        public static double STRAIGHTEN_RANGE = .15; // Fraction of 45 degrees...
        public static double TRIGGER_THRESHOLD = 0.7;
    }
}
