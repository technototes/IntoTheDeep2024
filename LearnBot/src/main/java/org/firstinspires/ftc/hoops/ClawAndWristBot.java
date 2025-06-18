package org.firstinspires.ftc.hoops;

import com.technototes.library.hardware.servo.ServoGroup;
import org.firstinspires.ftc.hoops.subsystems.ClawAndWristSubsystem;

//@Config
public class ClawAndWristBot {

    public String CLAW1 = "claw1";
    public String CLAW2 = "claw2";
    public String WRIST = "wrist";

    public ClawAndWristSubsystem caw;

    public ClawAndWristBot() {
        caw = new ClawAndWristSubsystem(
            new ServoGroup(CLAW1),
            new ServoGroup(CLAW2),
            new ServoGroup(WRIST)
        );
    }
}
