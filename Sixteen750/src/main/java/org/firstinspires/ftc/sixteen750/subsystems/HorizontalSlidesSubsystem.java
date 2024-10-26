package org.firstinspires.ftc.sixteen750.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class HorizontalSlidesSubsystem implements Subsystem, Loggable {

    //slides servo (link servo) - outstretched, retracted, transfer/neutral?
    //wrist servo - transfer, pickup, neutral, wall pickup for specimen
    //claw servo - drop (open), pickup (long and short)(close)

    //camera - red and blue and yellow based on if red and if blue alliance (vision?)
    //LEDs - different colors for different collections (vision?)
    public static double CLAW_POS = -0.7;
    public static double WRIST_POS = 0;
    public static double LINK_POS = 1;

    public static double LinkServoExtend = 0;
    public static double LinkServoRetract = 0;
    public static double ClawServoClose = 0;
    public static double ClawServoOpen = 0.3;
    public static double WristServoTransfer = 0.3;
    public static double WristServoPickup = 1;
    public static double WristServoIncrement = 0.555;

    @Log(name = "wristTarget")
    public double wristTargetPos;
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
    public void slidesout() {
        linkServo.setPosition(LinkServoExtend);
    }

    public void slidesin() {
        linkServo.setPosition(LinkServoRetract);
    }

    public void ClawServoChomp() {
        // the intake system's position
        clawServo.setPosition(ClawServoClose);
    }

    public void ClawServoBigOpen() {
        clawServo.setPosition(ClawServoOpen); //opens claw for intake and release
    }

    public void ClawWristServoPickup() {
        wristServo.setPosition(WristServoPickup); //lowers claw to intake
    }

    public void ClawWristServoTransfer() {
        // positions for the arm of the bot for transfer
        wristServo.setPosition(WristServoTransfer);
    }

    public void ClawWristServoIncrement() {
        // the arm's position to score
        setWristPos(wristTargetPos + WristServoIncrement);
    }

    public void ClawWristServoDecrement() {
        // the arm's position to score
        setWristPos(wristTargetPos - WristServoIncrement);
    }
  
    private void setWristPos(double w) {
        if (wristServo != null) {
            wristServo.setPosition(w);
            wristTargetPos = w;
        }
    }
}
