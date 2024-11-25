package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.sixteen750.Robot;

public class HorizontalSlidesCommands {

    //claw/wrist transfer, inc/dec, pickup, claw

    //slides commands
    public static Command horizontalExtend(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::slidesExtend);
    }

    public static Command horizontalRetract(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::slidesRetract);
    }

    public static Command horiSlideToggle(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::slideToggle);
    }

    //claw commands
    public static Command clawChomp(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawChomp);
    }

    public static Command clawOpen(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::ClawOpen);
    }

    public static Command clawToggle(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::clawToggle);
    }

    //wrist commands
    public static Command resetWristZero(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::resetWristZero);
    }

    public static Command wristToggle(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::wristToggle);
    }

    public static Command wristTransfer(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::WristServoTransfer);
    }

    public static Command wristVertTransfer(Robot r) {
        return Command.create(
            r.horizontalSlidesSubsystem::WristVertTransfer,
            r.horizontalSlidesSubsystem
        );
    }

    public static Command wristPickup(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::WristServoPickup);
    }

    public static Command wristIncrement(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::WristServoIncrement);
    }

    public static Command wristDecrement(Robot r) {
        return Command.create(r.horizontalSlidesSubsystem::WristServoDecrement);
    }
}
