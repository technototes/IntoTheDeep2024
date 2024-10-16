package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.hardware.motor.EncodedMotor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class HangSubsystem implements Subsystem, Loggable {

    private EncodedMotor suspend;

    public static double SUSPEND_1_POSITION = .1;
    public static double SUSPEND_2_POSITION = .2;
    public static double SUSPEND_3_POSITION = .4;

    @Log(name = "distance value ")
    public double distance_value;
    @Log(name = "color value ")
    public double color_value;
    public HangSubsystem(Hardware hw) {
        suspend = hw.suspend;
    }

    public void Sigmapos1() {
        suspend.setPosition(SUSPEND_1_POSITION);
    }

    public void Sigmapos2() {
        suspend.setPosition(SUSPEND_2_POSITION);
    }
    public void Sigmapos3() {
        suspend.setPosition(SUSPEND_3_POSITION);
    }


    @Override
    public void periodic() {

    }
}
