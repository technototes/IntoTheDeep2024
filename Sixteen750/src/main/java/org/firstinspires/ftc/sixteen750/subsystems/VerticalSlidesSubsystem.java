package org.firstinspires.ftc.sixteen750.subsystems;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Setup;

@Config
public class VerticalSlidesSubsystem implements Subsystem, Loggable {

    //    public static double ArmServo = 0.5;
    //slides motor - receive/down/reset, first basket, second basket
    //movebuck servo - transfer, pickup, neutral
    //bucket servo - drop, pickup (long and short)
    //camera - red and blue
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
    public static double ScoreServoFlat = 0.33;
    public static double ArmServoInput = 0.545;
    public static double WristServoPickup = 0.05;
    public static double WristServoDrop = 0.555; //drops in bucket
    public static double WristServoIncrement = 0.555;

    public static PIDCoefficients PID = new PIDCoefficients(0.0, 0.0, 0.0);
    public Servo wristServo;
    public Servo clawServo;
    public EncodedMotor<DcMotorEx> vertSlideMotor;
    private boolean isHardware;
    private PIDFController leftPidController;

    public VerticalSlidesSubsystem(Hardware hw) {
        // We need to configure the liftMotor to work like a servo.
        // This entails switching to "RunMode.RUN_TO_POSITION" and then tuning PID(F) constants
        // Comment from CenterStage but may still be relevant? for hang
        vertSlideMotor = hw.vertslidemotor;
        isHardware = true;
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
    }

    public VerticalSlidesSubsystem() {
        isHardware = false;
        vertSlideMotor = null;
        wristServo = null;
        clawServo = null;
    }

    private int get___CurrentPosition() {
        if (Setup.Connected.VERTSLIDES) {
            return (int) vertSlideMotor.getSensorValue();
        } else {
            return 0;
        }
    }

    private int get___TargetPosition() {
        return (int) leftPidController.getTargetPosition();
    } //was lift

    private void set___TargetPosition(int p) {
        leftPidController.setTargetPosition(p);
    } //was lift

    public void slidesup() {
        // lift bucket system up

    }

    public void slidesdown() {
        // lowers the bucket system

    }

    public void LiftHeightLow() {
        //takes the arm to the first level
        leftPidController.setTargetPosition(LOW_BUCKET);
    }

    public void LiftHeightHigh() {
        leftPidController.setTargetPosition(HIGH_BUCKET);
    }

    /*public void LiftHeightMedium() {
        //takes the arm to the third level
        leftPidController.setTargetPosition(HIGH_BUCKET);
    }*/

    public void LiftHeightIntake() {
        //brings the arm all the way down
        leftPidController.setTargetPosition(WRIST_POS);
        //        armServo.setPosition(0);
        //        scoreServo.setPosition(0);
    }

    public void periodic() {
        double targetSpeed = leftPidController.update(get___CurrentPosition());
        double clippedSpeed = Range.clip(targetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
        setSlideMotorPower(clippedSpeed);
        //        setLiftPosition_OVERRIDE(
        //                leftPidController.getTargetPosition(),
        //                rightPidController.getTargetPosition()
        //        );

    }

    private void setSlideMotorPower(double speed) {
        if (isHardware) {
            vertSlideMotor.setSpeed(speed);
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

    public void ScoreServoFlat() {
        clawServo.setPosition(ScoreServoFlat);
    }
}
