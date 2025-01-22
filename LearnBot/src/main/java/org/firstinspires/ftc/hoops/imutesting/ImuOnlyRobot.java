package org.firstinspires.ftc.hoops.imutesting;

import com.technototes.library.logger.Loggable;

public class ImuOnlyRobot implements Loggable {

    public double initialVoltage;
    public ImuOnlySubsystem subsys;

    public ImuOnlyRobot(ImuOnlyHardware hw) {
        this.initialVoltage = hw.voltage();
        this.subsys = new ImuOnlySubsystem(hw);
    }
}
