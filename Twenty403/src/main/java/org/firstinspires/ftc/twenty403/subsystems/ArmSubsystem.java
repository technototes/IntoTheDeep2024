package org.firstinspires.ftc.twenty403.subsystems;

//By Preston Lin

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.ColorSensor;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class ArmSubsystem implements Subsystem, Loggable {

    private EncodedMotor<DcMotorEx> extension_m;
    private EncodedMotor<DcMotorEx> r_shoulder_m;
    private EncodedMotor<DcMotorEx> l_shoulder_m;

    public static double RETAINER_OPEN_POSITION = -.2;
    public static double RETAINER_EAT_POSITION = -.1;
    public static double RETAINER_CLOSE_POSITION = .1;

    public static double JAW_BITE_POSITION = .1;

    public static double JAW_RELEASE_POSITION = -.1;
    public static double INTAKE_SLURP = -.1;

    public static double INTAKE_SPIT = .1;

    @Log(name = "distance value ")
    public double distance_value;
    @Log(name = "color value ")
    public double color_value;
    public ArmSubsystem(Hardware hw) {
        this.extension_m = hw.extension_m;
        this.r_shoulder_m = hw.r_shoulder_m;
        this.l_shoulder_m = hw.l_shoulder_m;

    }

    public void extend(double length) {
        extension_m
    }

    @Override
    public void periodic() {
        distance_value = rev2MDistanceSensor.getDistance(DistanceUnit.CM);
        color_value = colorSensor.rgb();
    }
}
