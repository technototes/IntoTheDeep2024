package org.firstinspires.ftc.sixteen750;

import com.acmerobotics.dashboard.config.Config;

public class Setup {

    @Config
    public static class Connected {
        public static boolean DRIVEBASE = true;
        public static boolean VERTSLIDES = true;
        public static boolean HORIZSLIDES = true;
    }

    @Config
    public static class HardwareNames {
        public static String FL_DRIVE_MOTOR = "fl";
        public static String FR_DRIVE_MOTOR = "fr";
        public static String RL_DRIVE_MOTOR = "rl";
        public static String RR_DRIVE_MOTOR = "rr";
        public static String IMU = "imu";

        public static String CAMERA = "webcam";
        public static String INTAKESERVO = "intakeservo";
        public static String VERTSLIDEMOTOR = "slidemotor";

        public static String CLAWSERVO = "clawservo";
        public static String WRISTSERVO = "wristservo";

    }

    @Config
    public static class OtherSettings {
        public static double STRAIGHTEN_DEAD_ZONE = 0.08;
    }
}
