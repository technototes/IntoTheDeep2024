package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.sensor.ColorSensor;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class KidShampooSubsystem implements Subsystem, Loggable {

    private Servo retainer, jaw, wrist;

    @Log(name = "jawPosition")
    public double jawPosition = 0;

    @Log(name = "jawTarget")
    public double jawTarget = 0;

    @Log(name = "wristPosition")
    public double wristPosition = 0;

    @Log(name = "wristTarget")
    public double wristTarget = 0;

    @Log
    private CRServo intake;

    public static double RETAINER_OPEN_POSITION = .78;

    public static double RETAINER_CLOSE_POSITION = .52;

    public static double JAW_BITE_POSITION = .45;

    public static double JAW_RELEASE_POSITION = .2;
    public static double INTAKE_SLURP = .6;

    public static double INTAKE_SPIT = -.6;

    public static double WRIST_SCOOP = 0;
    public static double WRIST_INC_DEC = .5;
    public static double WRIST_DUMP = -.7;
    public static double WRIST_STRAIGHT = .35;

    @Log(name = "intakePos")
    public double intakePos;

    public KidShampooSubsystem(Hardware hw) {
        intake = hw.intake;
        retainer = hw.retainer;
        wrist = hw.wrist;
        jaw = hw.jaw;
    }

    public void openRetainer() {
        retainer.setPosition(RETAINER_OPEN_POSITION);
    }

    public void closeRetainer() {
        retainer.setPosition(RETAINER_CLOSE_POSITION);
    }

    public void biteJaw() {
        jawTarget = JAW_BITE_POSITION;
        jaw.setPosition(JAW_BITE_POSITION);
    }

    public void wristincrement(double v) {
        int newSlidePos = (int) (wristTarget + v * WRIST_INC_DEC);
        setWristPos(newSlidePos);
    }

    public void wdecrement() {
        wristincrement(-1.0);
    }

    public void wincrement() {
        wristincrement(1.0);
    }

    public void dumpWrist() {
        //wristTarget = WRIST_DUMP;
        setWristPos(WRIST_DUMP);
    }

    public void scoopWrist() {
        //MwristTarget = WRIST_SCOOP;
        setWristPos(WRIST_SCOOP);
    }

    public void straightWrist() {
        setWristPos(WRIST_STRAIGHT);
    }

    public void releaseJaw() {
        jawTarget = JAW_RELEASE_POSITION;
        jaw.setPosition(JAW_RELEASE_POSITION);
    }

    public void slurpIntake() {
        intakePos = INTAKE_SLURP;
        intake.setPower(INTAKE_SLURP);
    }

    public void collectHorizontalSample() {
        // closeRetainer(),
        // scoopWrist(),

    }

    @Override
    public void periodic() {
        jawPosition = jaw.getPosition();
        wristPosition = wrist.getPosition();
    }

    public void spitIntake() {
        intakePos = INTAKE_SPIT;
        intake.setPower(INTAKE_SPIT);
    }

    public void stopIntake() {
        intakePos = 0;
        intake.setPower(0);
    }

    private void setWristPos(double w) {
        if (wrist != null) {
            //w = Range.clip(w, 0.0, 1.0);
            wrist.setPosition(w);
            wristTarget = w;
        }
    }
}
