package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class JoystickIncDecCmd implements Command, Loggable {

    public ClawSubsystem subsystem;
    public DoubleSupplier x;

    public JoystickIncDecCmd(ClawSubsystem sub, Stick xStick) {
        addRequirements(sub);
        subsystem = sub;
        x = xStick.getXSupplier();
    }

    // This will make the bot snap to an angle, if the 'straighten' button is pressed
    // Otherwise, it just reads the rotation value from the rotation stick

    @Override
    public void execute() {
        double xvalue = -x.getAsDouble();
        subsystem.increment(xvalue);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
