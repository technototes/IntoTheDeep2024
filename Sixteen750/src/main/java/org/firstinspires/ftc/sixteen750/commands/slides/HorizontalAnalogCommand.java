package org.firstinspires.ftc.sixteen750.commands.slides;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.sixteen750.subsystems.HorizontalSlidesSubsystem;

@Config
public class HorizontalAnalogCommand implements Command, Loggable {

    public static double BIGEXTEND = 0.9;
    public static double SMALLEXTEND = 0.4;
    public static double BIGRETRACT = -0.9;
    public static double SMALLRETRACT = -0.4;

    public HorizontalSlidesSubsystem subsystem;
    public DoubleSupplier x, y;

    public HorizontalAnalogCommand(HorizontalSlidesSubsystem sub, Stick xyStick) {
        addRequirements(sub);
        subsystem = sub;
        y = xyStick.getYSupplier();
    }

    @Override
    public void execute() {
        // If subsystem is busy it is running a trajectory.
        // The math & signs looks wonky, because this makes things field-relative
        // (Remember that "3 O'Clock" is zero degrees)
        double yvalue = -y.getAsDouble();
        //command that changes the horislides position
        if (yvalue > BIGEXTEND) {
            subsystem.manualBigExtend();
        } else if (yvalue > SMALLEXTEND) {
            subsystem.manualSmallExtend();
        } else if (yvalue < BIGRETRACT) {
            subsystem.manualBigRetract();
        } else if (yvalue < SMALLRETRACT) {
            subsystem.manualSmallRetract();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
