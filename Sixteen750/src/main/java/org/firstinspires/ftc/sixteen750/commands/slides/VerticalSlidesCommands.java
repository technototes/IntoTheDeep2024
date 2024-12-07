package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesCommands {

    //slides commands
    public static Command vertSlideToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideToggle, r.verticalSlidesSubsystem);
    }

    public static Command SlidesZero(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::resetSlideZero, r.verticalSlidesSubsystem);
    }

    public static Command HighBasketCommand(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideBasketHigh,
            r.verticalSlidesSubsystem
        );
    }

    public static Command LowBasketCommand(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideBasketLow, r.verticalSlidesSubsystem);
    }

    //unused
    public static Command SlidesDownCommand(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slidesDown, r.verticalSlidesSubsystem);
    }

    public static Command HighChamber(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideChamberHigh,
            r.verticalSlidesSubsystem
        );
    }

    public static Command LowChamber(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideChamberLow,
            r.verticalSlidesSubsystem
        );
    }

    //bucket commands
    public static Command bucketToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketToggle, r.verticalSlidesSubsystem);
    }

    public static Command BucketTransfer(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoTransfer,
            r.verticalSlidesSubsystem
        );
    }

    public static Command BucketLift(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoLift,
            r.verticalSlidesSubsystem
        );
    }
    public static Command BucketLift(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoLift, r.verticalSlidesSubsystem);
    }
    public static Command BucketEmpty(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoEmpty,
            r.verticalSlidesSubsystem
        );
    }

    public static Command BucketIncrement(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoIncrement,
            r.verticalSlidesSubsystem
        );
    }

    public static Command BucketDecrement(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoDecrement,
            r.verticalSlidesSubsystem
        );
    }

    //arm commands
    public static Command armToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armToggle, r.verticalSlidesSubsystem);
    }

    public static Command ArmTransfer(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::armServoTransfer,
            r.verticalSlidesSubsystem
        );
    }

    public static Command ArmEmpty(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoEmpty, r.verticalSlidesSubsystem);
    }

    public static Command ArmIncrement(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::armServoIncrement,
            r.verticalSlidesSubsystem
        );
    }

    public static Command ArmDecrement(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::armServoDecrement,
            r.verticalSlidesSubsystem
        );
    }
}
