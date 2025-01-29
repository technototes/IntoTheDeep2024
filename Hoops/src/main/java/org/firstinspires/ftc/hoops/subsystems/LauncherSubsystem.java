package org.firstinspires.ftc.hoops.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.technototes.library.hardware.motor.EncodedMotor;
import org.firstinspires.ftc.hoops.Hardware;

@Config
public class LauncherSubsystem {

    public static double MAX_MOTOR_VELOCITY = 1.0;

    public static double MIN_MOTOR_VELOCITY = 0.25;

    EncodedMotor<DcMotorEx> top;
    EncodedMotor<DcMotorEx> bottom;

    public LauncherSubsystem(Hardware h) {
        // Do stuff in here
        top = h.topLaunch;
        bottom = h.bottomLaunch;
        bottom.setDirection(DcMotorSimple.Direction.REVERSE);
        top.setDirection(DcMotorSimple.Direction.FORWARD);
        bottom.coast();
        top.coast();
    }

    public void Launch(double angleInDegrees) {
        // Spin the motors
        // TODO: make the motors spit the thing at the right angle
        if (angleInDegrees >= 0) {
            top.setVelocity(MAX_MOTOR_VELOCITY);
            bottom.setVelocity(MIN_MOTOR_VELOCITY);
        } else if (angleInDegrees <= 0) {
            top.setVelocity(MIN_MOTOR_VELOCITY);
            bottom.setVelocity(MAX_MOTOR_VELOCITY);
        } else if (angleInDegrees == 0) {
            top.setVelocity(MAX_MOTOR_VELOCITY);
            bottom.setVelocity(MAX_MOTOR_VELOCITY);
        }
    }

    public void Stop() {
        top.setVelocity(0);
        bottom.setVelocity(0);
    }
}
