package org.firstinspires.ftc.ptechnodactyl.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.ptechnodactyl.Hardware;


@Config
public class ClawSubsystem implements Subsystem, Loggable {
    private Servo clawServo, wristServo;
    private boolean isHardware;

    @Log(name = "clawPosition")
    public double clawPosition = 0;


    public static double CLAW_OPEN_POSITION = 0.3;
    public static double CLAW_CLOSE_POSITION = 0.7;

    private void setClawPosition(double d) {
        if (isHardware) {
            clawServo.setPosition(d);
            clawPosition = d;
        }
    }

    public void openClaw() {
        setClawPosition(CLAW_OPEN_POSITION);
    }

    public void closeClaw(){
    setClawPosition(CLAW_CLOSE_POSITION);
    }



}