package org.firstinspires.ftc.sixteen750.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class VerticalSlidesSubsystem implements Subsystem, Loggable {

    //slides motor - transfer, low basket, high basket
    //arm servo - transfer, empty
    //bucket servo - transfer, lift, empty

    public static int HIGH_BASKET = -1150;
    public static int LOW_BASKET = -450;
    public static int SLIDE_ZERO = 0;
    public static double BucketServoTransfer = 0.8;
    public static double BucketServoLift = 0.6; //carry position for scoring
    public static double BucketSecondLift = 0.4;
    public static double BucketServoEmpty = 0.25;
    public static double ArmServoEmpty = 1;
    public static double ArmServoLift = 0.5;
    public static double ArmServoTransfer = 0.05;
    public static double BucketServoIncrement = 0.05;
    public static double ArmServoIncrement = 0.05;

    // @Log(name = "slidePos")
    public int slidePos;

    //@Log(name = "slidePow")
    public double slidePow;

    //@Log(name = "slideTarget")
    public int slideTargetPos; //should call this one for the slide toggle instead

    //@Log(name = "armTarget")
    public double armTargetPos;

    @Log(name = "bucketTarget")
    public double bucketTargetPos;

    public Servo bucketServo;
    public Servo armServo;
    public EncodedMotor<DcMotorEx> slideMotor;
    private boolean isHardware;
    public static PIDCoefficients slidePID = new PIDCoefficients(0.0015, 0.0, 0.0);
    private PIDFController slidePidController;
    public static double FEEDFORWARD_COEFFICIENT = -0.13;
    public static double FEEDFORWARD_DOWN = 0.07;
    public static double FEEDFORWARD_UP = -0.13;
    public static int BIG_ADJUSTMENT = 0; //need to test
    public static int SMALL_ADJUSTMENT = 0; //need to test
    public int slideResetPos;

    //this is for smoother servo movement
    public ElapsedTime timer;
    public double startPos;
    public double endPos;
    public double totalTime;
    public boolean smoothServoRunning;

    @Log(name = "dumbCounter1")
    public int dumbCounter;

    @Log(name = "dumbCounter2")
    public int dumbCounter2;

    public VerticalSlidesSubsystem(Hardware hw) {
        // We need to configure the liftMotor to work like a servo.
        // This entails switching to "RunMode.RUN_TO_POSITION" and then tuning PID(F) constants
        // Comment from CenterStage but may still be relevant? for hang
        slideMotor = hw.slidemotor;
        armServo = hw.armservo;
        bucketServo = hw.bucketservo;
        isHardware = true;
        slidePidController = new PIDFController(slidePID, 0, 0, 0, (ticks, velocity) ->
            FEEDFORWARD_COEFFICIENT
        );
        resetSlideZero();

        timer = new ElapsedTime();
        smoothServoRunning = false;
    }

    public VerticalSlidesSubsystem() {
        isHardware = false;
        slideMotor = null;
        armServo = null;
        bucketServo = null;
    } //should be unused unless the subsystem is disconnected in setup

    @Override
    public void periodic() {
        dumbCounter++;
        slidePos = getSlideCurrentPos();
        slidePow = slidePidController.update(slidePos);
        setSlideMotorPower(slidePow);

        if (smoothServoRunning) {
            dumbCounter2++;
            double curTime = getTimer();
            double calcPower = curTime * ((endPos - startPos) / totalTime) + startPos;
            double clippedPower = Range.clip(
                calcPower,
                Math.min(startPos, endPos),
                Math.max(startPos, endPos)
            );
            setBucketPos(clippedPower);
            // This is backward, but instead, let's just use the elapsed time
            // smoothServoRunning = calcPower != clippedPower;
            smoothServoRunning = curTime <= totalTime;
        }
    }

    //methods -> always call these in commands

    //timer methods
    private void startTimer() {
        timer.reset();
    }

    private double getTimer() {
        return timer.seconds();
    }

    //setter and getter methods
    private void setBucketPos(double w) {
        if (bucketServo != null) {
            bucketServo.setPosition(w);
            bucketTargetPos = w;
            // Let's cancel any smooth-servo running code just to be safe
            // The periodic function will restore it if it's updating
            smoothServoRunning = false;
        }
    }

    private void setBucketPosUsingTimer(double seconds, double startPos, double endPos) {
        this.startPos = startPos;
        this.endPos = endPos;
        totalTime = seconds;
        startTimer();
        smoothServoRunning = true;
    }

    private void setArmPos(double w) {
        if (armServo != null) {
            armServo.setPosition(w);
            armTargetPos = w;
        }
    }

    private void setSlidePos(int e) {
        if (getSlideCurrentPos() < e) {
            FEEDFORWARD_COEFFICIENT = FEEDFORWARD_DOWN;
        } else {
            FEEDFORWARD_COEFFICIENT = FEEDFORWARD_UP;
        }
        slidePidController.setTargetPosition(e);
        slideTargetPos = e;
    }

    // private void setManualSlidePos(int f) {
    //     f = Range.clip(f, -900, 0);
    //     if (getSlideCurrentPos() < f) {
    //         FEEDFORWARD_COEFFICIENT = FEEDFORWARD_DOWN;
    //     } else {
    //         FEEDFORWARD_COEFFICIENT = FEEDFORWARD_UP;
    //     }
    //     slidePidController.setTargetPosition(f);
    //     slideTargetPos = f;
    // } //manual

    private void setSlideMotorPower(double speed) {
        if (isHardware) {
            slideMotor.setSpeed(speed);
        }
    }

    private void setSlideTargetPosition(int p) {
        slidePidController.setTargetPosition(p);
    } //do we need this?

    private int getSlideTargetPosition() {
        return (int) slidePidController.getTargetPosition();
    } //do we need this?

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

    //toggle methods
    public void slideToggle() {
        if (slidePos > -5) {
            setSlidePos(HIGH_BASKET);
            setBucketPos(BucketServoLift);
            setArmPos(ArmServoEmpty);
        } else {
            setSlidePos(SLIDE_ZERO);
            setArmPos(ArmServoTransfer);
            setBucketPos(BucketServoTransfer);
        }
    }

    public void bucketToggle() {
        if (bucketTargetPos == BucketServoLift) {
            setBucketPos(BucketServoEmpty);
        } else if (bucketTargetPos == BucketServoTransfer) {
            setBucketPos(BucketServoLift);
        } else { //need to make this do the thing if we want to toggle bucket
            setBucketPos(BucketServoLift);
            new WaitCommand(.5);
            setBucketPos(BucketServoTransfer);
        }
    }

    public void armToggle() {
        if (armTargetPos == ArmServoTransfer) {
            setArmPos(ArmServoEmpty);
        } else {
            setArmPos(ArmServoTransfer);
        }
    }

    //reset
    public void resetSlideZero() {
        slideResetPos = getSlideUnmodifiedPosition();
        // We don't want the destination to go nuts, so update the target with the new zero
        setSlideTargetPosition(slideResetPos);
    }

    //scoring methods
    public void slideBasketHigh() {
        setSlidePos(HIGH_BASKET);
    }

    public void slideBasketLow() {
        setSlidePos(LOW_BASKET);
    }

    public void slidesDown() {
        // lowers the bucket system
        //probably going to do the slide thing with the joysticks (negative of slidesup)
        setSlidePos(SLIDE_ZERO);
    }

    public void slideChamberLow() {
        setSlidePos(LOW_BASKET);
    }

    public void slideChamberHigh() {
        slidePidController.setTargetPosition(HIGH_BASKET);
    }

    public void bucketServoTransferAtStart() {
        setBucketPos(BucketServoTransfer);
    }

    public void bucketServoTransfer() {
        // the intake system's position to score
        setBucketPosUsingTimer(5.0, bucketTargetPos, BucketServoTransfer);
    }

    public void bucketServoLift() {
        setBucketPos(BucketServoLift);
    } //use if we need a position for lifting vertical slides

    public void bucketServoEmpty() {
        // positions for the arm of the bot
        setBucketPos(BucketServoEmpty);
    }

    public void BucketSecondLift() {
        // positions for the arm of the bot
        setBucketPos(BucketSecondLift);
    }

    public void armServoTransfer() {
        // positions for the arm of the bot
        setArmPos(ArmServoTransfer);
    }

    public void armServoLift() {
        // positions for the arm of the bot
        setArmPos(ArmServoLift);
    }

    public void armServoEmpty() {
        setArmPos(ArmServoEmpty);
    }

    //inc/dec methods
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
    // slides manual methods
    // public void manualBigExtend() {
    //     setManualSlidePos(slidePos - BIG_ADJUSTMENT);
    // }
    //
    // public void manualSmallExtend() {
    //     setManualSlidePos(slidePos - SMALL_ADJUSTMENT);
    // }
    //
    // public void manualBigRetract() {
    //     setManualSlidePos(slidePos + BIG_ADJUSTMENT);
    // }
    //
    // public void manualSmallRetract() {
    //     setManualSlidePos(slidePos + SMALL_ADJUSTMENT);
    // }
}
