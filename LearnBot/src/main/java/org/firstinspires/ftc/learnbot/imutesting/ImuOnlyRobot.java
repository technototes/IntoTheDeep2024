package org.firstinspires.ftc.learnbot.imutesting;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.learnbot.Hardware;
import org.firstinspires.ftc.learnbot.Setup;
import org.firstinspires.ftc.learnbot.helpers.StartingPosition;
import org.firstinspires.ftc.learnbot.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.learnbot.subsystems.PlacementSubsystem;
import org.firstinspires.ftc.learnbot.subsystems.TestSubsystem;

public class ImuOnlyRobot implements Loggable {

    public double initialVoltage;
    public ImuOnlySubsystem subsys;

    public ImuOnlyRobot(ImuOnlyHardware hw) {
        this.initialVoltage = hw.voltage();
        this.subsys = new ImuOnlySubsystem(hw);
    }
}
