package org.firstinspires.ftc.hoops.commands;

import com.technototes.library.command.Command;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.subsystems.MotorTestSubsystem;

public class AnalogMotorControlCmd implements Command, Loggable {

    @Log(name = "")
    public String instruction = "RStick ^/v";

    @Log.Number(name = "Move Power")
    public double value;

    MotorTestSubsystem sub;
    DoubleSupplier ds;

    public AnalogMotorControlCmd(MotorTestSubsystem motorTest, DoubleSupplier axis) {
        sub = motorTest;
        ds = axis;
        addRequirements(motorTest);
    }

    @Override
    public void execute() {
        double stickPos = ds.getAsDouble();
        if (Math.abs(stickPos) < Setup.GlobalSettings.STICK_DEAD_ZONE) {
            stickPos = 0;
        } else {
            // Scale stickPos from deadzone to 1 and square it to make it a little easier to control
            double scaled =
                (Math.abs(stickPos) - Setup.GlobalSettings.STICK_DEAD_ZONE) /
                (1 - Setup.GlobalSettings.STICK_DEAD_ZONE);
            // This gets the sign from stickPos, and puts it on scaled ^ 3
            stickPos = Math.copySign(scaled * scaled * scaled, stickPos);
        }
        value = -stickPos;
        sub.setMotorPower(value);
    }
}
