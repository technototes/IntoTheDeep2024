package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Setup;

@Config
public class ArmSubsystem implements Subsystem, Loggable {

    private EncodedMotor<DcMotorEx> rotate1, rotate2, slides;
    private OctoQuad octoquad;
    public static double FEEDFORWARD_COEFFICIENT = 0.13;
    public static int ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION = 100;
    public static int ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION = 200;
    public static int ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_LOW = 300;
    public static int ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_HIGH = 300;
    public static double MIN_ARM_MOTOR_SPEED = -0.5;
    public static double MAX_ARM_MOTOR_SPEED = 0.5;
    public static int ROTATE_MOTOR_INTAKE_POSITION = 400;
    public static double SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION = 500;
    public static double SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION = 600;
    public static double SLIDES_MOTOR_SPECIMEN_SCORING_POSITION = 700;
    public static double SLIDES_MOTOR_INTAKE_POSITION = 800;
    public static int ARM_VERTICAL = 3000;
    public static int ARM_HORIZONTAL = 850;
    public static int INITIAL_POSITION = 150;
    public static PIDCoefficients armPID = new PIDCoefficients(0.004, 0.00002, 0.00027);

    @Log(name = "armPow")
    public double armPow;

    @Log(name = "armTarget")
    public int armTargetPos;

    @Log(name = "armPos")
    public int armPos;

    @Log(name = "wristTarget")
    public double wristTargetPos;

    @Log(name = "wristPos")
    public double wristPos;

    private PIDFController armPidController;

    private void setArmPos(int e) {
        armPidController.setTargetPosition(e);
        armTargetPos = e;
    }

    public ArmSubsystem(Hardware hw) {
        rotate1 = hw.rotate1;
        rotate2 = hw.rotate2;
        rotate1.coast();
        rotate2.coast();
        slides = hw.slides;
        octoquad = hw.octoquad;
        octoquad.resetAllPositions();

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

            (ticks, velocity) ->
                FEEDFORWARD_COEFFICIENT *
                Math.cos(
                    (Math.PI * (ticks - ARM_HORIZONTAL)) / (2 * (ARM_VERTICAL - ARM_HORIZONTAL))
                )
        );
        setArmPos(INITIAL_POSITION);
    }

    public ArmSubsystem() {
        armPidController = new PIDFController(armPID, 0, 0, 0, (x, y) -> 0.0);
    }

    private int getArmCurrentPos() {
        return octoquad.readSinglePosition(Setup.OctoQuadPorts.ARMENCODER);
    }

    //low basket scoring
    public void lowBasket() {
        setArmPos(ROTATE_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    public void lowBasketSlides() {
        slides.setPosition(SLIDES_MOTOR_LOW_BASKET_SCORING_POSITION);
    }

    //high basket scoring
    public void highBasket() {
        setArmPos(ROTATE_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    public void highBasketSlides() {
        slides.setPosition(SLIDES_MOTOR_HIGH_BASKET_SCORING_POSITION);
    }

    //specimen scoring
    public void lowSpecimen() {
        setArmPos(ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_LOW);
    }

    public void highSpecimen() {
        setArmPos(ROTATE_MOTOR_SPECIMEN_SCORING_POSITION_HIGH);
    }

    public void specimenSlides() {
        slides.setPosition(SLIDES_MOTOR_SPECIMEN_SCORING_POSITION);
    }

    //intake position
    public void setArmToIntake() {
        setArmPos(ROTATE_MOTOR_INTAKE_POSITION);
    }

    @Override
    public void periodic() {
        armPos = getArmCurrentPos();
        armPow = armPidController.update(armPos);
        setArmMotorPower(armPow);
    }

    private void setArmMotorPower(double speed) {
        double clippedSpeed = Range.clip(speed, MIN_ARM_MOTOR_SPEED, MAX_ARM_MOTOR_SPEED);
        rotate1.setPower(clippedSpeed);
        rotate2.setPower(-clippedSpeed);
    }
}
