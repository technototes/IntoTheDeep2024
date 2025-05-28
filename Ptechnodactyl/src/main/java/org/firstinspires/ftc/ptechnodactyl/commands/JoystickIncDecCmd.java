package org.firstinspires.ftc.ptechnodactyl.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class JoystickIncDecCmd implements Command, Loggable {

    public ClawSubsystem subsystem;
    public DoubleSupplier x;
    public static ElapsedTime timer;

    public static double time;

    public double interval = 1.5;

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
        //        time = timer.time();
        //        if (interval < time)
        subsystem.increment(xvalue);
        //            timer.reset();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
