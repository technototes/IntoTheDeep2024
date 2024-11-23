package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;

import org.firstinspires.ftc.twenty403.subsystems.ArmSubsystem;

import java.util.function.DoubleSupplier;

public class JoystickSlideIncDecCommand implements Command, Loggable {

    public ArmSubsystem subsystem;
    public DoubleSupplier x;


    public JoystickSlideIncDecCommand(
        ArmSubsystem sub,
        Stick xStick
    ) {
        addRequirements(sub);
        subsystem = sub;
        x = xStick.getXSupplier();

    }


    // This will make the bot snap to an angle, if the 'straighten' button is pressed
    // Otherwise, it just reads the rotation value from the rotation stick


    @Override
    public void execute() {
            double xv = -x.getAsDouble();
        subsystem.slideincrement(xv);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
