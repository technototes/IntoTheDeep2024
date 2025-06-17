package org.firstinspires.ftc.ptechnodactyl.subsystems;

import com.technototes.library.hardware.servo.ServoGroup;
import com.technototes.library.subsystem.Subsystem;
import java.util.function.Supplier;

public class BrakeSubsystem implements Subsystem, Supplier<Boolean> {

    @com.acmerobotics.dashboard.config.Config
    public static class BrakeConstants {

        public static double UP = 0.02, DOWN = 0.34;
    }

    private boolean isBraking;
    public ServoGroup servo;

    public BrakeSubsystem(ServoGroup s) {
        servo = s;
        isBraking = false;
    }

    public void raise() {
        servo.setPosition(BrakeConstants.UP);
        isBraking = false;
    }

    public void lower() {
        servo.setPosition(BrakeConstants.DOWN);
        isBraking = true;
    }

    @Override
    public Boolean get() {
        return isBraking;
    }
}
