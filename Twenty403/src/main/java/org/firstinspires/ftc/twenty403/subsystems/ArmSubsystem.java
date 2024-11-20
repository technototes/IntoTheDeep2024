package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.helpers.IEncoder;

@Config
public class ArmSubsystem implements Subsystem, Loggable {

    private EncodedMotor<DcMotorEx> rotate1, rotate2, slides;
    private IEncoder armEncoder;
    private boolean isHardware;
    public int slideResetPos;
    public static double FEEDFORWARD_COEFFICIENT = 0.00014; //11-19-24
    public static int ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION = 100;
    public static int ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION = 200;
    public static int ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_LOW = 300;
    public static int ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_HIGH = 300;
    public static double MIN_ARM_MOTOR_SPEED = -0.2;
    public static double MAX_ARM_MOTOR_SPEED = 0.7;
    public static int ROTATE_MOTOR_INTAKE_POSITION = 400;
    public static int SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION = 500;
    public static int SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION = 600;
    public static int SLIDES_MOTOR_SPECIMEN_SCORING_POSITION = 2500;
    public static int SLIDES_MOTOR_INTAKE_POSITION = 800;
    public static int ARM_VERTICAL = 3100;
    public static int ARM_HORIZONTAL = 1000;
    public static int INITIAL_POSITION = 150;
    public static int INCREMENT_DECREMENT = 120;
    public static int SLIDE_INC_DEC = 100;
    public static int SLIDE_MAX_POS = 3100;
    public static int SLIDE_OFFSET = 2000;
    public static double MIN_SLIDE_MOTOR_POWER = -0.3;
    public static double MAX_SLIDE_MOTOR_POWER = 0.5;
    public static double SLIDE_FEEDFORWARD_GRAVITY_VALUE = 0.3;
    public static double SLIDE_FEEDFORWARD_INTAKE_POS = 0;

    // This is "5 degrees" if our numbers are correct:
    public static int ARM_POS_CLOSE_ENOUGH = Math.abs(ARM_HORIZONTAL - ARM_VERTICAL) / 18;

    // as of now, we arent having a D
    public static PIDCoefficients armPID = new PIDCoefficients(0.0007, 0.0, 0.000);
    public static PIDCoefficients slidePID = new PIDCoefficients(0.001, 0.0, 0.000);

    @Log(name = "armPow")
    public double armPow;

    @Log(name = "armTarget")
    public int armTargetPos;

    @Log(name = "armPos")
    public int armPos;

    @Log(name = "slidePow")
    public double slidePow;

    @Log(name = "slideTarget")
    public int slideTargetPos;

    @Log(name = "slidePos")
    public int slidePos;

    @Log(name = "armFdFwdVal")
    public double armFeedFwdValue;

    @Log(name = "slideFdFwdVal")
    public double slideFeedFwdValue;

    private PIDFController armPidController;
    private PIDFController slidePidController;

    private void setArmPos(int e) {
        armPidController.setTargetPosition(e);
        armTargetPos = e;
    }

    private void setSlidePos(int e) {
        e = Range.clip(e, 0, SLIDE_MAX_POS);
        slidePidController.setTargetPosition(e);
        slideTargetPos = e;
    }

    public ArmSubsystem(Hardware hw) {
        rotate1 = hw.rotate1;
        rotate2 = hw.rotate2;
        rotate1.coast();
        rotate2.coast();
        slides = hw.slides;
        armEncoder = hw.armEncoder;
        isHardware = true;
        armPidController = new PIDFController(
            armPID,
            0,
            0,
            0,
            /*

            The function arguments for the Feed Forward function are Position (ticks) and
            Velocity (units?). So, for the arm, we want to check to see if which side of
            center we're on, and if the velocity is pushing us down, FF should probably be
            low (negative?) while if velocity is pushing us *up*, FF should be high (right?)
            Someone who's done physics and/or calculus recently should write some real equations

            Braelyn got the math right

            For angle T through this range where we start at zero:
                       /
                      / T
            180 _____/_____ 0
            The downward torque due to gravity is cos(T) * Gravity (9.8m/s^2)

            If we're moving from 0 to 180 degrees, then:
                While T is less than 90, the "downward torque" is working *against* the motor
                When T is greater than 90, the "downward torque" is working *with* the motor

             */

            (ticks, velocity) -> {
                armFeedFwdValue =
                    FEEDFORWARD_COEFFICIENT * Math.cos(getArmAngle(ticks)) * getSlideLength();

                if (Math.abs(armFeedFwdValue) < 0.1) {
                    armFeedFwdValue = 0.0;
                }

                return armFeedFwdValue;
            }
        );
        setArmPos(INITIAL_POSITION);
        slidePidController = new PIDFController(slidePID, 0, 0, 0, (ticks, velocity) -> {
            if (isArmHorizontal()) {
                slideFeedFwdValue = 0.0;
            } else if (isArmVertical()) {
                slideFeedFwdValue = SLIDE_FEEDFORWARD_GRAVITY_VALUE;
            } else {
                slideFeedFwdValue = SLIDE_FEEDFORWARD_INTAKE_POS;
            }
            return slideFeedFwdValue;
        });
        resetSlideZero();
    }

    private static double getArmAngle(double ticks) {
        // our horizontal value starts at ARM_HORIZONTAL, so we need to
        // subtract it
        return ((Math.PI / 2.0) * (ticks - ARM_HORIZONTAL)) / (ARM_VERTICAL - ARM_HORIZONTAL);
    }

    private double getSlideLength() {
        return getSlideUnmodifiedPosition() + SLIDE_OFFSET;
    }

    private boolean isArmHorizontal() {
        return Math.abs(getArmCurrentPos() - ARM_HORIZONTAL) < ARM_POS_CLOSE_ENOUGH;
    }

    private boolean isArmVertical() {
        return Math.abs(getArmCurrentPos() - ARM_VERTICAL) < ARM_POS_CLOSE_ENOUGH;
    }

    public ArmSubsystem() {
        armPidController = new PIDFController(armPID, 0, 0, 0, (x, y) -> 0.0);
        slidePidController = new PIDFController(slidePID, 0, 0, 0, (x, y) -> 0.0);
        isHardware = false;
        slides = null;
        rotate1 = null;
        rotate2 = null;
    }

    public void increment() {
        setArmPos(getArmCurrentPos() + INCREMENT_DECREMENT);
        if (armTargetPos > 3100) {
            setArmPos(3100);
        }
    }

    public void decrement() {
        setArmPos(getArmCurrentPos() - INCREMENT_DECREMENT);
        if (armTargetPos < 0) {
            setArmPos(0);
        }
    }

    public void resetSlideZero() {
        slideResetPos = getSlideUnmodifiedPosition();
        slideTargetPos = slideResetPos;
    }

    public void slideIncrement() {
        setSlidePos(slideTargetPos + SLIDE_INC_DEC);
    }

    public void slideDecrement() {
        setSlidePos(slideTargetPos - SLIDE_INC_DEC);
    }
    public void slideSpecimen() {
        setSlidePos(SLIDES_MOTOR_SPECIMEN_SCORING_POSITION);
    }

    private int getCurrentSlidePos() {
        return getSlideUnmodifiedPosition() - slideResetPos;
    }

    private int getArmCurrentPos() {
        if (isHardware) {
            return -armEncoder.getPosition();
        }
        return 0;
    }

    private int getSlideUnmodifiedPosition() {
        if (isHardware) {
            return (int) slides.getSensorValue();
        } else {
            return 0;
        }
    }

    //low basket scoring
    public void lowBasket() {
        setArmPos(ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    public void lowBasketSlides() {
        setSlidePos(SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    //high basket scoring
    public void highBasket() {
        setArmPos(ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    public void highBasketSlides() {
        setSlidePos(SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    //specimen scoring
    public void lowSpecimen() {
        setArmPos(ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_LOW);
    }

    public void highSpecimen() {
        setArmPos(ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_HIGH);
    }

    public void specimenSlides() {
        setSlidePos(SLIDES_MOTOR_SPECIMEN_SCORING_POSITION);
    }

    //intake position
    public void setArmToIntake() {
        setArmPos(INITIAL_POSITION);
    }

    @Override
    public void periodic() {
        armPos = getArmCurrentPos();
        armPow = armPidController.update(armPos);
        setArmMotorPower(armPow);
        slidePos = getCurrentSlidePos();
        slidePow = slidePidController.update(slidePos);
        setSlideMotorPower(slidePow);
    }

    private void setSlideMotorPower(double speedSlide) {
        if (isHardware) {
            double clippedSpeed = Range.clip(
                speedSlide,
                MIN_SLIDE_MOTOR_POWER,
                MAX_SLIDE_MOTOR_POWER
            );
            slides.setPower(clippedSpeed);
        }
    }

    private void setArmMotorPower(double speed) {
        double clippedSpeed = Range.clip(speed, MIN_ARM_MOTOR_SPEED, MAX_ARM_MOTOR_SPEED);
        rotate1.setPower(clippedSpeed);
        rotate2.setPower(-clippedSpeed);
    }

    public void horizontal() {
        setArmPos(ARM_HORIZONTAL);
    }

    public void vertical() {
        setArmPos(ARM_VERTICAL);
    }
}
