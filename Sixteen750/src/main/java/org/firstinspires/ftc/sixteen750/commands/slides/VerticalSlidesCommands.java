package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesCommands {

    //slides commands
    public static Command vertSlideToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideToggle);
    }
    public static Command SlidesZero(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::resetSlideZero);
    }
    public static Command HighBasket(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideBasketHigh);
    }
    public static Command LowBasket(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideBasketLow);
    }
    //unused
    public static Command SlidesDown(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slidesDown);
    }
    public static Command HighChamber(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideChamberHigh);
    }
    public static Command LowChamber(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideChamberLow);
    }

    //bucket commands
    public static Command bucketToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketToggle);
    }
    public static Command BucketTransfer(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoTransfer);
    }
    public static Command BucketEmpty(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoEmpty);
    }
    public static Command BucketIncrement(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoIncrement);
    }
    public static Command BucketDecrement(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoDecrement);
    }

    //arm commands
    public static Command armToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armToggle);
    }
    public static Command ArmTransfer(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoTransfer);
    }
    public static Command ArmEmpty(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoEmpty);
    }
    public static Command ArmIncrement(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoIncrement);
    }
    public static Command ArmDecrement(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoDecrement);
    }

}
