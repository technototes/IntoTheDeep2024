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

    public static double EXTENSION_M_FULL = 0.5;
    public static double R_SHOULDER_M = 0.5;
    public static double L_SHOULDER_M = 0.5;



    public ArmSubsystem(Hardware hw) {
        this.extension_m = hw.extension_m;
        this.r_shoulder_m = hw.r_shoulder_m;
        this.l_shoulder_m = hw.l_shoulder_m;
        this.r_shoulder_m.setBackward();
    }

    public void setExtension_m(){extension_m.setPosition(EXTENSION_M_FULL);}
    public void rotateToTop(){
        r_shoulder_m.setPosition(R_SHOULDER_M);
        l_shoulder_m.setPosition(L_SHOULDER_M);
    }

    @Override
    public void periodic() {

    }
}
