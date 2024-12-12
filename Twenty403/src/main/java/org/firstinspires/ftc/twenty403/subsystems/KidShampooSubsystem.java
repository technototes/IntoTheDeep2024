package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class KidShampooSubsystem implements Subsystem, Loggable {

    private Servo retainer, jaw, wrist;
    private CRServo intake;

    @Log(name = "jawPosition")
    public double jawPosition = 0;

    @Log(name = "wristPosition")
    public double wristPosition = 0;

    @Log(name = "intakePow")
    public double intakePow;

    @Log(name = "retainerPos")
    public double retainerPos;

    public static double RETAINER_OPEN_POSITION = .78;

    public static double RETAINER_CLOSE_POSITION = .52;

    public static double JAW_BITE_POSITION = .45;

    public static double JAW_RELEASE_POSITION = .2;
    public static double INTAKE_SLURP = .6;

    public static double INTAKE_SPIT = -.6;

    public static double WRIST_SCOOP = 0.35;
    public static double WRIST_INC_DEC = .5;
    public static double WRIST_DUMP = 0.05;
    public static double WRIST_STRAIGHT = .35;

    public KidShampooSubsystem(Hardware hw) {
        intake = hw.intake;
        retainer = hw.retainer;
        wrist = hw.wrist;
        jaw = hw.jaw;
    }

    public void openRetainer() {
        setRetainerPosition(RETAINER_OPEN_POSITION);
    }

    public void closeRetainer() {
        setRetainerPosition(RETAINER_CLOSE_POSITION);
    }

    public void biteJaw() {
        setJawPosition(JAW_BITE_POSITION);
    }

    private void wristMoveRelative(double v) {
        int newSlidePos = (int) (wristPosition + v * WRIST_INC_DEC);
        setWristPos(newSlidePos);
    }

    public void wristDecrement() {
        wristMoveRelative(-1.0);
    }

    public void wristIncrement() {
        wristMoveRelative(1.0);
    }

    public void dumpWrist() {
        setWristPos(WRIST_DUMP);
    }

    public void scoopWrist() {
        setWristPos(WRIST_SCOOP);
    }

    public void straightWrist() {
        setWristPos(WRIST_STRAIGHT);
    }

    public void releaseJaw() {
        setJawPosition(JAW_RELEASE_POSITION);
    }

    public void slurpIntake() {
        setIntakePower(INTAKE_SLURP);
    }

    public void collectHorizontalSample() {
        // closeRetainer(),
        // scoopWrist(),

    }

    public void spitIntake() {
        setIntakePower(INTAKE_SPIT);
    }

    public void stopIntake() {
        setIntakePower(0);
    }

    private void setRetainerPosition(double d) {
        if (retainer != null) {
            retainer.setPosition(d);
            retainerPos = d;
        }
    }

    private void setJawPosition(double d) {
        if (jaw != null) {
            jaw.setPosition(d);
            jawPosition = d;
        }
    }

    private void setIntakePower(double d) {
        if (intake != null) {
            intake.setPower(d);
            intakePow = d;
        }
    }

    private void setWristPos(double w) {
        if (wrist != null) {
            //w = Range.clip(w, 0.0, 1.0);
            wrist.setPosition(w);
            wristPosition = w;
        }
    }
}
