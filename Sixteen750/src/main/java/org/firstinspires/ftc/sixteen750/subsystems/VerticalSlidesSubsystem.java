package org.firstinspires.ftc.sixteen750.subsystems;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class VerticalSlidesSubsystem implements Subsystem, Loggable {

    //    public static double ArmServo = 0.5;
    //slides motor - receive/down/reset, first basket, second basket
    //arm servo - transfer, pickup, neutral
    //bucket servo - drop, pickup (long and short)

    public static double LOW_BUCKET = -950;
    public static double HIGH_BUCKET = -1350;
    //    public static double HIGH_POS = 1000;
    public static double WRIST_POS = 0;
    public static double ARM_POS = 0;
    public static double MIN_MOTOR_SPEED = -0.7;
    public static double MAX_MOTOR_SPEED = 1;

    //    public static double ScoreServo = 0.5;

    //    public static double ArmServo = 0.5;

    public static double ClawServoOpenShort = 0.4;
    public static double ClawServoClose = 0.55;
    public static double ClawServoOpenLong = 0;
    public static double ArmServoInput = 0.545;
    public static double WristServoPickup = 0.05;
    public static double WristServoDrop = 0.555; //drops in bucket
    public static double WristServoIncrement = 0.555;
    @Log(name = "slidePos")
    public int slidePos;

    @Log(name = "slidePow")
    public double slidePow;

    @Log(name = "slideTarget")
    public int slideTargetPos;

    @Log(name = "wristPos")
    public double wristPos;
    @Log(name = "wristTarget")
    public double wristTargetPos;
    public static PIDCoefficients PID = new PIDCoefficients(0.0, 0.0, 0.0);
    public Servo armServo;
    public Servo bucketServo;
    public EncodedMotor<DcMotorEx> slideMotor;
    private boolean isHardware;
    public static PIDCoefficients slidePID = new PIDCoefficients(0.0, 0.0, 0.0);
    private PIDFController slidePidController;
    public int slideResetPos;

    public VerticalSlidesSubsystem(Hardware hw) {
        // We need to configure the liftMotor to work like a servo.
        // This entails switching to "RunMode.RUN_TO_POSITION" and then tuning PID(F) constants
        // Comment from CenterStage but may still be relevant? for hang
        slideMotor = hw.slidemotor;
        isHardware = true;
        slidePidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        resetSlideZero();
    }
    public void resetSlideZero() {
        slideResetPos = getSlideUnmodifiedPosition();
        // We don't want the destination to go nuts, so update the target with the new zero
        slideTargetPos = slideResetPos;
    }

    public VerticalSlidesSubsystem() {
        isHardware = false;
        slideMotor = null;
        armServo = null;
        bucketServo = null;
    }

    @Override
    public void periodic() {
        slidePos = getSlideCurrentPos();
        slidePow = slidePidController.update(slidePos);
        setSlideMotorPower(slidePow);
    }

    private void setSlidePos(int e) {
        slidePidController.setTargetPosition(e);
        slideTargetPos = e;
    }

    private void setWristPos(double w) {
        if (armServo != null) {
            armServo.setPosition(w);
            wristTargetPos = w;
        }
    }
    private int getSlideTargetPosition() {
        return (int) slidePidController.getTargetPosition();
    }
    private int getSlideCurrentPos() {
        return getSlideUnmodifiedPosition() - slideResetPos;
    }

    private int getSlideUnmodifiedPosition() {
        if (isHardware) {
            return (int) slideMotor.getSensorValue();
        } else {
            return 0;
        }
    }

    private void setSlideTargetPosition(int p) {
        slidePidController.setTargetPosition(p);
    }

    public void slidesup() {
        // lift bucket system up

    }

    public void slidesdown() {
        // lowers the bucket system

    }

    public void LiftHeightLow() {
        //takes the arm to the first level
        slidePidController.setTargetPosition(LOW_BUCKET);
    }

    public void LiftHeightHigh() {
        slidePidController.setTargetPosition(HIGH_BUCKET);
    }

    /*public void LiftHeightMedium() {
        //takes the arm to the third level
        leftPidController.setTargetPosition(HIGH_BUCKET);
    }*/

    public void LiftHeightIntake() {
        //brings the arm all the way down
        slidePidController.setTargetPosition(WRIST_POS);
        //        armServo.setPosition(0);
        //        scoreServo.setPosition(0);
    }

    private void setSlideMotorPower(double speed) {
        if (isHardware) {
            slideMotor.setSpeed(speed);
        }
    }

    public void WristServoIncrement() {
        // the arm's position to score
        wristServo.setPosition(WristServoIncrement);
    }

    public void ScoreServoOutput() {
        // the intake system's postion to score
        clawServo.setPosition(ClawServoClose);
    }

    public void ScoreServoHold() {
        clawServo.setPosition(ClawServoOpenLong);
    }

    public void WristServoPickup() {
        wristServo.setPosition(WristServoPickup);
    }

    public void WristServoDrop() {
        // positions for the arm of the bot
        wristServo.setPosition(ArmServoInput);
    }

    public void ClawServoDrop() {
        // positions for the arm of the bot
        clawServo.setPosition(ClawServoOpenLong);
    }

}
