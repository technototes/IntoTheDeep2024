package org.firstinspires.ftc.ptechnodactyl.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.servo.Servo;

@Config
public class ClawSubsystem {

    public Servo servowervo;
    public static double CLAW_OPEN = 0;
    public static double CLAW_CLOSE = 0.4;

    public void setClawOpen() {
        servowervo.setPosition(CLAW_OPEN);
    }

    public void setClawClose() {
        servowervo.setPosition(CLAW_CLOSE);
    }

}

