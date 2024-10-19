package org.firstinspires.ftc.sixteen750.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class BucketSubsystem implements Subsystem, Loggable {

    public static double ArmServoInput = 0.545;
    public static double ArmServoScore = .6;
    public static double BucketServoInput = .5;
    public static double BucketServoScore = .6;

    public Servo armServo;
    public Servo bucketServo;
    public boolean isHardware;

    public BucketSubsystem(Hardware hw) {
        isHardware = true;
        armServo = hw.armservo;
        bucketServo = hw.bucketservo;
    }

    public BucketSubsystem() {
        isHardware = false;
        armServo = null;
        bucketServo = null;
    }
}
