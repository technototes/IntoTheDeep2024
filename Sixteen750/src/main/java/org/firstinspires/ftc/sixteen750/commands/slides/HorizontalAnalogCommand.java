package org.firstinspires.ftc.sixteen750.commands.slides;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.MathUtils;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.subsystems.DrivebaseSubsystem;
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
        //        addRequirements(sub);
        subsystem = sub;
        y = xyStick.getYSupplier();
    }

    @Override
    public void execute() {
        // If subsystem is busy it is running a trajectory.
        // The math & signs looks wonky, because this makes things field-relative
        // (Remember that "3 O'Clock" is zero degrees)
        double yvalue = -y.getAsDouble();
        //command that changes the horislides position?
        if (yvalue > BIGEXTEND) {
            subsystem.BigExtending();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::BigExtending, subsystem));
        } else if (yvalue > SMALLEXTEND) {
            subsystem.SmallExtending();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::SmallExtending, subsystem));
        } else if (yvalue < BIGRETRACT) {
            subsystem.BigRetracting();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::BigRetracting, subsystem));
        } else if (yvalue < SMALLRETRACT) {
            subsystem.SmallRetracting();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::SmallRetracting, subsystem));
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
