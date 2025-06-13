package org.firstinspires.ftc.hoops.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.technototes.library.general.Periodic;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.Setup;

@Config
public class LauncherSubsystem implements Subsystem, Loggable {

    public static double MAX_MOTOR_VELOCITY = 0.25; // 0.5 1.0

    public static double MIN_MOTOR_VELOCITY = 0.075; // 0.15 0.25

    boolean hasHardware;
    EncodedMotor<DcMotorEx> top;
    EncodedMotor<DcMotorEx> bottom;

    public LauncherSubsystem(Hardware h) {
        // Do stuff in here
        if (Setup.Connected.LAUNCHER) {
            hasHardware = true;
            top = h.topLaunch;
            bottom = h.bottomLaunch;
            bottom.setDirection(DcMotorSimple.Direction.FORWARD);
            top.setDirection(DcMotorSimple.Direction.REVERSE);
            bottom.coast();
            top.coast();
        } else {
            hasHardware = false;
            top = null;
            bottom = null;
        }
    }

    public void Launch(double angleInDegrees) {
        // Spin the motors
        // TODO: make the motors spit the thing at the right angle
        if (hasHardware) {
            if (angleInDegrees > 0) {
                top.setVelocity(MAX_MOTOR_VELOCITY);
                bottom.setVelocity(MIN_MOTOR_VELOCITY);
            } else if (angleInDegrees < 0) {
                top.setVelocity(MIN_MOTOR_VELOCITY);
                bottom.setVelocity(MAX_MOTOR_VELOCITY);
            } else {
                top.setVelocity(MAX_MOTOR_VELOCITY);
                bottom.setVelocity(MAX_MOTOR_VELOCITY);
            }
        }
    }

    public void Stop() {
        if (hasHardware) {
            top.setVelocity(0);
            bottom.setVelocity(0);
        }
    }
}
