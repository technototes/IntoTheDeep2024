package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class KidShampoo implements Subsystem, Loggable {
    private Servo retainer, jaw;
    private CRServo intake;

    public static double RETAINER_OPEN_POSITION = -.2;
    public static double RETAINER_EAT_POSITION = -.1;
    public static double RETAINER_CLOSE_POSITION = .1;

    public KidShampoo(Hardware hw){
        intake = hw.intake;
        retainer = hw.retainer;
        jaw = hw.jaw;
    }

    public void openRetainer(){
        retainer.setPosition(RETAINER_OPEN_POSITION);
    }
    public void eatRetainer(){
        retainer.setPosition(RETAINER_EAT_POSITION);

    }
    public void closeRetainer(){
        retainer.setPosition(RETAINER_CLOSE_POSITION);
    }
    public void BiteJaw(){
        retainer.setPosition(RETAINER_OPEN_POSITION);
    }
    public void ReleaseJaw(){
        retainer.setPosition(RETAINER_CLOSE_POSITION);
    }
}
