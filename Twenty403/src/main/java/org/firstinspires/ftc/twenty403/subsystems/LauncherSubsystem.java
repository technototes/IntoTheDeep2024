package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.technototes.library.hardware.motor.EncodedMotor;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class LauncherSubsystem {

    public static double MAX_MOTOR_VELOCITY = 1.0;

    EncodedMotor<DcMotorEx> top;
    EncodedMotor<DcMotorEx> bottom;

    public LauncherSubsystem(Hardware h) {
        // Do stuff in here
        top = h.armL;
        bottom = h.armR;
        bottom.setDirection(DcMotorSimple.Direction.REVERSE);
        top.setDirection(DcMotorSimple.Direction.FORWARD);
        bottom.coast();
        top.coast();
    }

    public void Launch(double angleInDegrees) {
        // Spin the motors
        // TODO: make the motors spit the thing at the right angle
        top.setVelocity(MAX_MOTOR_VELOCITY);
        bottom.setVelocity(MAX_MOTOR_VELOCITY);
    }

    public void Stop() {
        top.setVelocity(0);
        bottom.setVelocity(0);
    }
}
