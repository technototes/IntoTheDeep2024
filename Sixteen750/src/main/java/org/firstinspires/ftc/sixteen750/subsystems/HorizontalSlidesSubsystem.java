package org.firstinspires.ftc.sixteen750.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class HorizontalSlidesSubsystem implements Subsystem, Loggable {

    // Comments from CenterStage but may still be relevant for hang...
    //slide servo (link servo) - extend, retract, transfer
    //wrist servo - transfer, pickup, lift, wall pickup for specimen (no specimen yet)
    //claw servo - open, chomp (close)

    // We need to configure the liftMotor to work like a servo.
    // This entails switching to "RunMode.RUN_TO_POSITION" and then tuning PID(F) constants
        /*public void LiftHeightMedium() {
        //takes the arm to the third level
        leftPidController.setTargetPosition(HIGH_BUCKET);
    }*/

    public static double LinkServoExtend = 0.6;
    public static double LinkServoRetract = 1;
    public static double ClawServoClose = 0.3;
    public static double ClawServoOpen = 0.6;
    public static double WristServoTransfer = 0.3;
    public static double WristVertTransfer = 0.1;
    public static double WristServoPickup = 0.9;
    public static double WristServoIncrement = 0.15;
    public static double BIG_ADJUSTMENT = 0.01;
    public static double SMALL_ADJUSTMENT = 0.0001;

    public double wristResetPos; //test me!!
    @Log(name = "wristTarget")
    public double wristTargetPos;
    @Log(name = "clawTarget")
    public double clawTargetPos;

    @Log(name = "horizontalSlide")
    public double slidePos;

    public Servo wristServo;
    public Servo clawServo;
    public Servo linkServo;
    private final boolean isHardware; //not currently used, is there a use for it or should we delete?

    public HorizontalSlidesSubsystem(Hardware hw) {
        wristServo = hw.wristservo;
        clawServo = hw.clawservo;
        linkServo = hw.linkservo;


        isHardware = true;
    }

    public HorizontalSlidesSubsystem() {
        isHardware = false;
        linkServo = null;
        wristServo = null;
        clawServo = null;
    } //should be unused unless the subsystem is disconnected in setup

    //methods -> always call these in commands

    //setter and getter methods
    private void setClawPos(double w) {
        if (clawServo != null) {
            w = Range.clip(w, 0.0, 1.0);
            clawServo.setPosition(w);
            clawTargetPos = w;
        }
    }
    private void setWristPos(double w) {
        if (wristServo != null) {
            w = Range.clip(w, 0.0, 1.0);
            wristServo.setPosition(w);
            wristTargetPos = w;
        }
    }
    private void setSlidePos(double pos) {
        linkServo.setPosition(pos);
        slidePos = pos;
    }
    private void setManualSlidePos(double pos) {
        pos = Range.clip(pos, 0.5, 1.0);
        linkServo.setPosition(pos);
        slidePos = pos;
    } //manual

    //toggle methods
    public void slideToggle() {
        if (slidePos < 1){
            setSlidePos(LinkServoRetract);
            setWristPos(WristServoTransfer);
            setClawPos(ClawServoClose);
        }
        else {
            setSlidePos(LinkServoExtend);
            setWristPos(WristServoPickup);
            setClawPos(ClawServoOpen);
        }
    }
    public void clawToggle() {
        if (clawTargetPos == ClawServoClose){
            setClawPos(ClawServoOpen);
        }
        else {
            setClawPos(ClawServoClose);
        }
    }
    public void wristToggle() {
        if (wristTargetPos == WristServoPickup){
            setWristPos(WristServoTransfer);
        }
        else {
            setWristPos(WristServoPickup);
        }
    }

    //reset
    public void resetWristZero() { //resets wrist position to zero - helpful for when the wrist skips
        wristResetPos = wristServo.getPosition();//may need to adjust since wrist pickup is 1 not 0
        wristTargetPos = wristResetPos;
    }

    //intake methods
    public void slidesExtend() {
        setSlidePos(LinkServoExtend);
    }
    public void slidesRetract() {
        setSlidePos(LinkServoRetract);
    }

    public void ClawOpen() {
        setClawPos(ClawServoOpen); //opens claw for intake and release
    }
    public void ClawChomp() {
        // the intake system's position
        setClawPos(ClawServoClose);
    }

    public void WristServoTransfer() {
        setWristPos(WristServoTransfer);
    }
    public void WristServoPickup() {
        setWristPos(WristServoPickup); //lowers claw to intake
    }
    public void WristVertTransfer() { //gets claw out of the way to lift bucket
        setWristPos(WristVertTransfer);
    } //wrist pos when scoring

    //inc/dec methods
    public void WristServoIncrement() {
        setWristPos(wristTargetPos - WristServoIncrement);
    }
    public void WristServoDecrement() {
        setWristPos(wristTargetPos + WristServoIncrement);
    }

    //manual methods
    public void manualBigExtend() {
        setManualSlidePos(slidePos - BIG_ADJUSTMENT);
    }
    public void manualSmallExtend() {
        setManualSlidePos(slidePos - SMALL_ADJUSTMENT);
    }
    public void manualBigRetract() {
        setManualSlidePos(slidePos + BIG_ADJUSTMENT);
    }
    public void manualSmallRetract() {
        setManualSlidePos(slidePos + SMALL_ADJUSTMENT);
    }
}
