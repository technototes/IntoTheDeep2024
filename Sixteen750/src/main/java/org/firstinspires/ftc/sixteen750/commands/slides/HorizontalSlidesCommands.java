package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.sixteen750.Robot;

public class HorizontalSlidesCommands {

    //command.create(something something)
    //low basket, high basket, low specimen, high specimen, bucket transfer, clawwrist transfer, inc/dec, pickup, claw
    public static Command horizontalExtend(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::slidesout);
    }

    public static Command horizontalRetract(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::slidesin);
    }

    public static Command clawChomp(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawServoChomp);
    }

    public static Command clawOpen(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawServoBigOpen);
    }
    public static Command clawToggle(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::clawToggle);
    }
    public static Command wristToggle(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::wristToggle);
    }
    public static Command slideToggle(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::slideToggle);
    }
    public static Command wristTransfer(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoTransfer);
    }//may need to make a reset pos for wrist because of the skipping

    public static Command VertExtendTransfer(Robot r) {
        return Command.create(
            r.horizontalSlidesSubsystem::VertExtendTransfer,
            r.horizontalSlidesSubsystem
        );
    }

    public static SequentialCommandGroup transferring(Robot r) {
        return new SequentialCommandGroup(
            wristTransfer(r),
            horizontalRetract(r),
            clawChomp(r),
            VerticalSlidesSequentials.SlidesDown(r)
        );
    }

    public static Command wristPickup(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoPickup);
    }

    public static Command wristIncrement(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoIncrement);
    }

    public static Command wristDecrement(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoDecrement);
    }

    public static SequentialCommandGroup intake(Robot r) {
        return new SequentialCommandGroup(
            wristTransfer(r),
            Command.create(r.horizontalSlidesSubsystem::ClawServoBigOpen),
            horizontalExtend(r),
            new WaitCommand(1),
            Command.create(r.horizontalSlidesSubsystem::ClawWristServoPickup)
        );
    }
}
