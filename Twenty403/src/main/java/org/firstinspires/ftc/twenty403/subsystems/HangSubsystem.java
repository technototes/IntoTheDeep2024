package org.firstinspires.ftc.twenty403.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.twenty403.Hardware;

@Config
public class HangSubsystem implements Subsystem, Loggable {

    private Motor suspend;

    public static double SUSPEND_POSITION = .4;

    public HangSubsystem(Hardware hw) {
        suspend = hw.suspend;
    }

    public void suspend() {
        suspend.setPower(SUSPEND_POSITION);
    }
}
