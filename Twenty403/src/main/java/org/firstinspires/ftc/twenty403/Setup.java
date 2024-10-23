package org.firstinspires.ftc.twenty403;

import com.acmerobotics.dashboard.config.Config;

public class Setup {

    @Config
    public static class Connected {

        public static boolean DRIVEBASE = true;
        public static boolean ODOSUBSYSTEM = false;
        public static boolean SAFETYSUBSYSTEM = false;
        public static boolean KIDSSHAMPOOSUBSYSTEM = true;
        public static boolean HANGSUBSYSTEM = true;
        public static boolean ARMSUBSYSTEM = false;
    }

    @Config
    public static class HardwareNames {

        public static String FLMOTOR = "fl";
        public static String FRMOTOR = "fr";
        public static String RLMOTOR = "rl";
        public static String RRMOTOR = "rr";
        public static String IMU = "imu";
        public static String ODOF = "odof";
        public static String ODOR = "odor";
        public static String INTAKE = "intake";
        public static String RETAINER = "retainer";
        public static String JAW = "jaw";
        public static String WRIST = "wrist";
        public static String COLOR1 = "color";
        public static String DIST1 = "dist";
        public static String SUSPEND = "suspend";
        public static String ARML = "armL";
        public static String ARMR = "armR";
        public static String SLIDEMOTOR = "slide";
    }

    @Config
    public static class OtherSettings {

        public static int AUTOTIME = 25;
        public static double STRAIGHTEN_DEAD_ZONE = 0.015;
    }
}
