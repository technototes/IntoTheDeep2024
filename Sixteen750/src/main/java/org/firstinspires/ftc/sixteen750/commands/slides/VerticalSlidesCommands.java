package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesCommands {

    public static Command HighBasket(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideBasketHigh);
    }

    public static Command LowBasket(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideBasketLow);
    }
    public static Command slideToggle(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideToggle);
    }

    public static Command HighChamber(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideChamberHigh);
    }

    public static Command LowChamber(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideChamberLow);
    }

    public static Command BucketTransfer(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoTransfer);
    }
    public static Command BucketDecrement(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoDecrement);
    }
    public static Command BucketIncrement(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoIncrement);
    }

    public static Command BucketEmpty(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::bucketServoEmpty);
    }

    public static Command ArmTransfer(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoTransfer);
    }

    public static Command ArmScore(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoEmpty);
    }
    public static Command SlidesDown(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slidesDown);
    }
    public static Command SlidesZero(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::resetSlideZero);
    }


    //transfer
    //inc/dec

}
