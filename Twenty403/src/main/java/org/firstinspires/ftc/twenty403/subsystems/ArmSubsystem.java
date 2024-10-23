package org.firstinspires.ftc.twenty403.subsystems;

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

    private EncodedMotor<DcMotorEx> rotate1, rotate2, slides;

    public static double ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION = 1;
    public static double ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION = 2;
    public static double ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION = 3;
    public static double ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION = 3;
    public static double ROTATE_MOTOR_INTAKE_POSITION = 4;
    public static double SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION = 5;
    public static double SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION = 6;
    public static double SLIDES_MOTOR_LOW_SPECIMEN_SCORING_POSITION = 7;
    public static double SLIDES_MOTOR_HIGH_SPECIMEN_SCORING_POSITION = 7;
    public static double SLIDES_MOTOR_INTAKE_POSITION = 8;

    public ArmSubsystem(Hardware hw) {
        rotate1 = hw.rotate1;
        rotate2 = hw.rotate2;
        slides = hw.slides;
    }

    //low basket scoring
    public void lowBasketRotate1() {
        rotate1.setPosition(ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    public void lowBasketRotate2() {
        rotate2.setPosition(ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    public void lowBasketSlides() {
        slides.setPosition(SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    //high basket scoring
    public void highBasketRotate() {
        rotate1.setPosition(ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    public void highBasketRotate2() {
        rotate2.setPosition(ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    public void highBasketSlides() {
        slides.setPosition(SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    //specimen scoring
    public void lowSpecimenRotate1() {
        rotate1.setPosition(ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION);
    }

    public void lowSpecimenRotate2() {
        rotate2.setPosition(ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION);
    }

    public void lowSpecimenSlides() {
        slides.setPosition(SLIDES_MOTOR_LOW_SPECIMEN_SCORING_POSITION);
    }
    public void highSpecimenRotate1() {
        rotate1.setPosition(ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION);
    }

    public void highSpecimenRotate2() {
        rotate2.setPosition(ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION);
    }

    public void highSpecimenSlides() {
        slides.setPosition(SLIDES_MOTOR_HIGH_SPECIMEN_SCORING_POSITION);
    }

    //intake position
    public void intakeRotate1() {
        rotate1.setPosition(ROTATE_MOTOR_INTAKE_POSITION);
    }

    public void intakeRotate2() {
        rotate2.setPosition(ROTATE_MOTOR_INTAKE_POSITION);
    }

    public void intakeSlides() {
        slides.setPosition(SLIDES_MOTOR_INTAKE_POSITION);
    }
}
