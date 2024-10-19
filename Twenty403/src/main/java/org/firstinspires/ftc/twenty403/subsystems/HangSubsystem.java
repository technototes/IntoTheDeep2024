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


    public static double SUSPEND_POSITION = .4;

    public HangSubsystem(Hardware hw) {
        suspend = hw.suspend;
    }


    public void Suspend() {
        suspend.setPosition(SUSPEND_POSITION);
    }


}