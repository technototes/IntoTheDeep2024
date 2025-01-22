package org.firstinspires.ftc.hoops;

import com.technototes.library.logger.Loggable;
import org.firstinspires.ftc.hoops.subsystems.MotorTestSubsystem;

public class OnlyMotorRobot implements Loggable {

    public double initialVoltage;
    public MotorTestSubsystem motorTestSubsystem;

    public OnlyMotorRobot(Hardware hw) {
        this.initialVoltage = hw.voltage();
        if (Setup.Connected.MOTOR) {
            this.motorTestSubsystem = new MotorTestSubsystem(hw);
        }
    }
}
