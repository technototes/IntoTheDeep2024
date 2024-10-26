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
    public static double ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION1 = 3;
    public static double ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION2 = 3;
    public static double ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION1 = 3;
    public static double ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION2 = 3;
    public static double ROTATE_MOTOR_INTAKE_SAMPLE_POSITION = 4;
    public static double ROTATE_MOTOR_INTAKE_SPECIMEN_POSITION = 4;
    public static double SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION = 5;
    public static double SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION = 6;
    public static double SLIDES_MOTOR_LOW_SPECIMEN_SCORING_POSITION1 = 7;
    public static double SLIDES_MOTOR_LOW_SPECIMEN_SCORING_POSITION2 = 7;
    public static double SLIDES_MOTOR_HIGH_SPECIMEN_SCORING_POSITION1 = 7;
    public static double SLIDES_MOTOR_HIGH_SPECIMEN_SCORING_POSITION2 = 7;
    public static double SLIDES_MOTOR_INTAKE_SAMPLE_POSITION = 8;
    public static double SLIDES_MOTOR_INTAKE_SPECIMEN_POSITION = 8;
    public static double ROTATE_MOTOR_NEUTRAL_POSITION = 10;
    public static double SLIDES_MOTOR_NEUTRAL_POSITION = 10;


    public ArmSubsystem(Hardware hw) {
        rotate1 = hw.rotate1;
        rotate2 = hw.rotate2;
        slides = hw.slides;
    }

    //low basket scoring
    public void lowBasketRotate() {
        rotate1.setPosition(ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION);
        rotate2.setPosition(ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    public void lowBasketSlides() {
        slides.setPosition(SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    //high basket scoring
    public void highBasketRotate() {
        rotate1.setPosition(ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION);
        rotate2.setPosition(ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    public void highBasketSlides() {
        slides.setPosition(SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    //specimen scoring
    public void lowSpecimenRotate1() {
        rotate1.setPosition(ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION1);
        rotate2.setPosition(ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION1);
    }

    public void lowSpecimenSlides1() {
        slides.setPosition(SLIDES_MOTOR_LOW_SPECIMEN_SCORING_POSITION1);
    }

    public void lowSpecimenRotate2() {
        rotate1.setPosition(ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION2);
        rotate2.setPosition(ROTATE_MOTOR_LOW_SPECIMEN_SCORING_POSITION2);
    }

    public void lowSpecimenSlides2() {
        slides.setPosition(SLIDES_MOTOR_LOW_SPECIMEN_SCORING_POSITION2);
    }

    public void highSpecimenRotate1() {
        rotate1.setPosition(ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION1);
        rotate2.setPosition(ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION1);
    }

    public void highSpecimenSlides1() {
        slides.setPosition(SLIDES_MOTOR_HIGH_SPECIMEN_SCORING_POSITION1);
    }

    public void highSpecimenRotate2() {
        rotate1.setPosition(ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION2);
        rotate2.setPosition(ROTATE_MOTOR_HIGH_SPECIMEN_SCORING_POSITION2);
    }

    public void highSpecimenSlides2() {
        slides.setPosition(SLIDES_MOTOR_HIGH_SPECIMEN_SCORING_POSITION2);
    }

    //intake position
    public void intakeRotateSample() {

        rotate1.setPosition(ROTATE_MOTOR_INTAKE_SAMPLE_POSITION);
        rotate2.setPosition(ROTATE_MOTOR_INTAKE_SAMPLE_POSITION);
    }

    public void intakeSlidesSample() {
        slides.setPosition(SLIDES_MOTOR_INTAKE_SAMPLE_POSITION);
    }

    public void intakeRotateSpecimen() {

        rotate1.setPosition(ROTATE_MOTOR_INTAKE_SPECIMEN_POSITION);
        rotate2.setPosition(ROTATE_MOTOR_INTAKE_SPECIMEN_POSITION);
    }

    public void intakeSlidesSpecimen() {
        slides.setPosition(SLIDES_MOTOR_INTAKE_SPECIMEN_POSITION);
    }

    //neutral position

    public void neutralRotate() {

        rotate1.setPosition(ROTATE_MOTOR_NEUTRAL_POSITION);
        rotate2.setPosition(ROTATE_MOTOR_NEUTRAL_POSITION);
    }

    public void neutralSlides() {
        slides.setPosition(SLIDES_MOTOR_NEUTRAL_POSITION);
    }

}
