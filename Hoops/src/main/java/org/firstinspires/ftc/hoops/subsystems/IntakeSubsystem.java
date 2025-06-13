package org.firstinspires.ftc.hoops.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class IntakeSubsystem implements Subsystem, Loggable {

    boolean hasHardware;
    EncodedMotor<DcMotorEx> slurp;
    DistanceSensor ballDetector;

    public static double THE_VELOCITY = 0.5;
    public static double DISTANCE = 5.0;

    public IntakeSubsystem(Hardware h) {
        if (Setup.Connected.INTAKE) {
            hasHardware = true;
            slurp = h.slurp;
            slurp.coast();
            // TODO: replace null with something
            ballDetector = null;
        } else {
            hasHardware = false;
            ballDetector = null;
            slurp = null;
        }
    }

    public void Intake() {
        setSlurp(THE_VELOCITY);
    }

    public void Stop() {
        setSlurp(0);
    }

    public boolean Detect() {
        return getDistance() < DISTANCE;
    }

    protected double getDistance() {
        if (hasHardware) {
            return ballDetector.getDistance(DistanceUnit.CM);
        } else {
            return 0;
        }
    }

    protected void setSlurp(double val) {
        if (hasHardware) {
            slurp.setVelocity(val);
        }
    }
}
