package org.firstinspires.ftc.ptechnodactyl.subsystems;

import    com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;

@Config
public class ClawSubsystem {

    public Servo servowervo;
    public static double CLAW_OPEN = 0;
    public static double CLAW_CLOSE = 0.4;

    @Log(name = "clawpos")
    public double clawpos;

    public void setServoPosition(double r) {
        servowervo.setPosition(r);
        clawpos = r;
    }

    public void setClawOpen() {
        setServoPosition(CLAW_OPEN);
    }

    public void setClawClose() {
        setServoPosition(CLAW_CLOSE);
    }

    public void periodic() {

    }
}

