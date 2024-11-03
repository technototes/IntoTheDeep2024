package org.firstinspires.ftc.sixteen750.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
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

    public static double LOW_BASKET = -950;
    public static double HIGH_BASKET = -1350;
    //    public static double HIGH_POS = 1000;
    public static double SLIDE_ZERO = 0;
    public static double SLIDE_POS = 0;
    public static double ARM_POS = 0;
    public static double MIN_SERVO_SPEED = -.5;
    public static double MAX_SERVO_SPEED = .5;
    public static double MIN_MOTOR_SPEED = -0.7;
    public static double MAX_MOTOR_SPEED = 1;
    //    public static double ScoreServo = 0.5;
    //    public static double ArmServo = 0.5;
    public static double ClawServoOpenShort = 0.4;
    public static double BucketServoTransfer = 0.55;
    public static double BucketServoEmpty = 0.05;
    public static double BucketServoLift = 0;
    public static double ArmServoInput = 0.545;
    public static double ArmServoEmpty = 1;
    public static double BucketServoIncrement = 0.05;
    public static double ArmServoIncrement = 0.555;
    public static double ArmServoTransfer = 0;

    @Log(name = "slidePos")
    public int slidePos;

    @Log(name = "slidePow")
    public double slidePow;

    @Log(name = "slideTarget")
    public int slideTargetPos;

    @Log(name = "armTarget")
    public double armTargetPos;

    @Log(name = "bucketTarget")
    public double bucketTargetPos;

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
        armServo = hw.armservo;
        bucketServo = hw.bucketservo;
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

    private void setArmPos(double w) {
        if (armServo != null) {
            armServo.setPosition(w);
            armTargetPos = w;
        }
    }

    private int getSlideTargetPosition() {
        return (int) slidePidController.getTargetPosition();
    }

    private int getSlideCurrentPos() {
        return getSlideUnmodifiedPosition() - slideResetPos;
    }

    private void setSlideTargetPosition(int p) {
        slidePidController.setTargetPosition(p);
    }

    public void slidesDown() {
        // lowers the bucket system
        //probably going to do the slide thing with the joysticks (negative of slidesup)
        slidePidController.setTargetPosition(SLIDE_ZERO);
    }

    public void slideBasketLow() {
        //takes the arm to the first level
        slidePidController.setTargetPosition(LOW_BASKET);
    }

    public void slideBasketHigh() {
        slidePidController.setTargetPosition(HIGH_BASKET);
    }

    public void slideChamberLow() {
        //takes the arm to the first level
        slidePidController.setTargetPosition(LOW_BASKET);
    }

    public void slideChamberHigh() {
        slidePidController.setTargetPosition(HIGH_BASKET);
    }

    /*public void LiftHeightMedium() {
        //takes the arm to the third level
        leftPidController.setTargetPosition(HIGH_BUCKET);
    }*/

    public void bucketServoIncrement() {
        // the arm's position to score
        setBucketPos(bucketTargetPos + BucketServoIncrement);
    }
    public void bucketServoDecrement() {
        // the arm's position to score
        setBucketPos(bucketTargetPos - BucketServoIncrement);
    }
    public void armServoIncrement() {
        // the arm's position to score
        setArmPos(armTargetPos + ArmServoIncrement);
    }
    public void armServoDecrement() {
        // the arm's position to score
        setArmPos(armTargetPos - ArmServoIncrement);
    }


    public void bucketServoTransfer() {
        // the intake system's position to score
        setBucketPos(BucketServoTransfer);
    }

    public void bucketServoLift() {
        bucketServo.setPosition(BucketServoLift);
    } //use if we need a position for lifting vertical slides

    public void bucketServoEmpty() {
        // positions for the arm of the bot
        setBucketPos(BucketServoEmpty);
    }

    public void armServoTransfer() {
        // positions for the arm of the bot
        setArmPos(ArmServoTransfer);
    }

    public void armServoEmpty() {
        armServo.setPosition(ArmServoEmpty);
    }

    //    public void ArmServoHighBucket() {
    //        // positions for the arm of the bot
    //        armServo.setPosition(ArmServoInput);
    //    }

    private void setSlideMotorPower(double speed) {
        if (isHardware) {
            slideMotor.setSpeed(speed);
        }
    }

    private int getSlideUnmodifiedPosition() {
        if (isHardware) {
            return (int) slideMotor.getSensorValue();
        } else {
            return 0;
        }
    }

    private void setBucketPos(double w) {
        if (bucketServo != null) {
            bucketServo.setPosition(w);
            bucketTargetPos = w;
        }
    }
}
