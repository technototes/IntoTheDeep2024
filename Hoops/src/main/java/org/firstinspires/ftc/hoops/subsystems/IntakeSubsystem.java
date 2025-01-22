package org.firstinspires.ftc.hoops.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.technototes.library.hardware.motor.EncodedMotor;
import org.firstinspires.ftc.hoops.Hardware;

public class IntakeSubsystem {

    EncodedMotor<DcMotorEx> slurp;
    DistanceSensor ballDetector;

    public static double THE_VELOCITY = 0.5;

    public IntakeSubsystem(Hardware h) {
        slurp = h.slurp;
        slurp.coast();

        //TODO: replace null with something

        ballDetector = null;
    }

    public void Intake(double angleInDegrees) {
        slurp.setVelocity(THE_VELOCITY);
    }

    public void Stop() {
        slurp.setVelocity(0);
    }

    public void Detect() {}
}
