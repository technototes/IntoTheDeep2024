package org.firstinspires.ftc.sixteen750.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.sixteen750.Hardware;

@Config
public class HangSubsystem implements Subsystem, Loggable {

    private Motor suspend;
    private Motor suspend2; // idk what it does and idk what to name it

    public static double SUSPEND_POSITION = .4;

    public HangSubsystem(Hardware hw) {
        suspend = hw.suspend;
        suspend2 = hw.suspend2;
    }

    public void suspend() {
        suspend.setPower(SUSPEND_POSITION);
        suspend2.setPower(SUSPEND_POSITION);
    }
}
