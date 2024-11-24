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

    //slide servo (link servo) - extend, retract, transfer
    //wrist servo - transfer, pickup, lift, wall pickup for specimen (no specimen yet)
    //claw servo - open, chomp (close)

    public static double BIGADJUSTMENT = 0.01;
    public static double SMALLADJUSTMENT = 0.0001;

    public static double LinkServoExtend = 0.6;
    public static double LinkServoRetract = 1;
    public static double ClawServoClose = 0.3;
    public static double ClawServoOpen = 0.6;
    public static double WristServoTransfer = 0.3;
    public static double WristVertTransfer = 0.1;
    public static double WristServoPickup = 0.9;
    public static double WristServoIncrement = 0.15;
    public double wristResetPos;

    @Log(name = "wristTarget")
    public double wristTargetPos;
    @Log(name = "clawTarget")
    public double clawTargetPos;

    @Log(name = "horizontalSlide")
    public double slidePos;

    public Servo wristServo;
    public Servo clawServo;
    public Servo linkServo;

    private boolean isHardware;

    public HorizontalSlidesSubsystem(Hardware hw) {
        wristServo = hw.wristservo;
        clawServo = hw.clawservo;
        linkServo = hw.linkservo;

        // We need to configure the liftMotor to work like a servo.
        // This entails switching to "RunMode.RUN_TO_POSITION" and then tuning PID(F) constants
        // Comment from CenterStage but may still be relevant? for hang
        isHardware = true;
    }

    public HorizontalSlidesSubsystem() {
        isHardware = false;
        linkServo = null;
        wristServo = null;
        clawServo = null;
    }

    //these are methods, needed to be called in a command
    public void slideToggle() {
        if (slidePos < 1){
            setSlides(LinkServoRetract);
            setWristPos(WristServoTransfer);
            setClawPos(ClawServoClose);
        }
        else {
            setSlides(LinkServoExtend);
            setWristPos(WristServoPickup);
            setClawPos(ClawServoOpen);
        }
    }
    public void slidesout() {
        setSlides(LinkServoExtend);
    }

    public void slidesin() {
        setSlides(LinkServoRetract);
    }

    public void BigExtending() {
        setSlide(slidePos - BIGADJUSTMENT);
    }

    public void SmallExtending() {
        setSlide(slidePos - SMALLADJUSTMENT);
    }

    public void BigRetracting() {
        setSlide(slidePos + BIGADJUSTMENT);
    }

    public void SmallRetracting() {
        setSlide(slidePos + SMALLADJUSTMENT);
    }

    public void clawToggle() {
        if (clawTargetPos == ClawServoClose){
            setClawPos(ClawServoOpen);
        }
        else {
            setClawPos(ClawServoClose);
        }
    }
    private void setClawPos(double w) {
        if (clawServo != null) {
            Range.clip(w, 0.0, 1.0);
            clawServo.setPosition(w);
            clawTargetPos = w;
        }
    }
    public void ClawChomp() {
        // the intake system's position
        setClawPos(ClawServoClose);
    }

    public void ClawOpen() {
        setClawPos(ClawServoOpen); //opens claw for intake and release
    }
    public void wristToggle() {
        if (wristTargetPos == WristServoPickup){
            setWristPos(WristServoTransfer);
        }
        else {
            setWristPos(WristServoPickup);
        }
    }

    public void WristServoPickup() {
        setWristPos(WristServoPickup); //lowers claw to intake
    }

    public void WristServoTransfer() {
        setWristPos(WristServoTransfer);
    }
    public void WristVertTransfer() { //gets claw out of the way to lift bucket
        setWristPos(WristVertTransfer);
    }

    public void WristServoIncrement() {
        setWristPos(wristTargetPos + WristServoIncrement);
    }

    public void ClawWristServoDecrement() {
        setWristPos(wristTargetPos - WristServoIncrement);
    }
    public void resetWristZero() { //resets wrist position to zero - helpful for when the wrist skips
        wristResetPos = wristServo.getPosition();//may need to adjust since wrist pickup is 1 not 0
        wristTargetPos = wristResetPos;
    }

    private void setWristPos(double w) {
        if (wristServo != null) {
            Range.clip(w, 0.0, 1.0);
            wristServo.setPosition(w);
            wristTargetPos = w;
        }
    }

    private void setSlide(double pos) {
        Range.clip(pos, 0.5, 1.0);
        linkServo.setPosition(pos);
        slidePos = pos;
    }

    private void setSlides(double pos) {
        linkServo.setPosition(pos);
        slidePos = pos;
    }
}
