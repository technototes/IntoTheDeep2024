package org.firstinspires.ftc.sixteen750.commands.slides;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import java.util.function.IntSupplier;
import org.firstinspires.ftc.sixteen750.subsystems.VerticalSlidesSubsystem;

@Config
public class VerticalAnalogCommand implements Command, Loggable { //needs testing

    //need to change to ints and get motor values
    public static int BIG_EXTEND = 0; //test
    public static int SMALL_EXTEND = 0; //test
    public static int BIG_RETRACT = 0; //test
    public static int SMALL_RETRACT = 0; //test

    public VerticalSlidesSubsystem subsystem;

    public IntSupplier x, y;

    public VerticalAnalogCommand(VerticalSlidesSubsystem sub, Stick xyStick) {
        addRequirements(sub);
        subsystem = sub;
        y = (IntSupplier) xyStick.getYSupplier();
    }

    @Override
    public void execute() {
        // (Remember that "3 O'Clock" is zero degrees)
        double yvalue = -y.getAsInt(); //IMPORTANT: this line was servo specific -> make sure it is correct
        //command that changes the vertslides position?
        /*if (yvalue > BIG_EXTEND) {
            subsystem.manualBigExtend();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::BigExtending, subsystem));
        } else if (yvalue > SMALL_EXTEND) {
            subsystem.manualSmallExtend();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::SmallExtending, subsystem));
        } else if (yvalue < BIG_RETRACT) {
            subsystem.manualBigRetract();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::BigRetracting, subsystem));
        } else if (yvalue < SMALL_RETRACT) {
            subsystem.manualSmallRetract();
            //            CommandScheduler.scheduleOnce(Command.create(subsystem::SmallRetracting, subsystem));
        }*/
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
