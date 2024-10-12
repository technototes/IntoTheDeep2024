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
public class HorizontalSlidesSubsystem implements Subsystem, Loggable {

    //    public static double HIGH_POS = 1000;
    public static double WRIST_POS = 0;
    public static double MIN_MOTOR_SPEED = -0.7;
    public static double MAX_MOTOR_SPEED = 1;

    //    public static double ScoreServo = 0.5;

    //    public static double ArmServo = 0.5;
    //slides motor - outstretched, retracted, transfer/neutral?
    //wrist servo - transfer, pickup, neutral, wall pickup for specimen
    //claw servo - drop (open), pickup (long and short)(close)
    //camera - red and blue and yellow based on if red and if blue alliance
    //LEDs - different colors for different collectionsb

    public static double ClawServoOpenShort = 0.4;
    public static double ClawServoClose = 0.55;
    public static double ClawServoOpenLong = 0;
    public static double ScoreServoFlat = 0.33;
    public static double ArmServoInput = 0.545;
    public static double WristServoPickup = 0.05;
    public static double WristServoDrop = 0.555; //drops in bucket
    public static double WristServoIncrement = 0.555;

    public static PIDCoefficients PID = new PIDCoefficients(0.0027, 0.0, 0.00015);
    public Servo wristServo;
    public Servo clawServo;
    public EncodedMotor<DcMotorEx> horizSlideMotor;
    private boolean isHardware;
    private PIDFController leftPidController;

    public HorizontalSlidesSubsystem(Hardware hw) {
        wristServo = hw.wristservo;
        clawServo = hw.clawservo;
        // We need to configure the liftMotor to work like a servo.
        // This entails switching to "RunMode.RUN_TO_POSITION" and then tuning PID(F) constants
        // Comment from CenterStage but may still be relevant? for hang
        horizSlideMotor = hw.horizslidemotor;
        isHardware = true;
        leftPidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
    }

    public HorizontalSlidesSubsystem() {
        isHardware = false;
        horizSlideMotor = null;
        wristServo = null;
        clawServo = null;
    }

    private int get___CurrentPosition() {
        if (Setup.Connected.HORIZSLIDES) {
            return (int) horizSlideMotor.getSensorValue();
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

    public void slidesout() {
        // extends slides to pickup

    }

    public void slidesin() {
        // retracts slides to transfer

    }

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
            horizSlideMotor.setSpeed(speed);
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
