package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesCommands {

    public static Command HighBasket(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::slideBasketHigh,
            r.verticalSlidesSubsystem
        );
    }

    public static Command LowBasket(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slideBasketLow, r.verticalSlidesSubsystem);
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

    public static Command BucketTransfer(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoTransfer,
            r.verticalSlidesSubsystem
        );
    }
    public static Command BucketDecrement(Robot r) {
        return Command.create(
                r.verticalSlidesSubsystem::bucketServoDecrement,
                r.verticalSlidesSubsystem
        );
    }
    public static Command BucketIncrement(Robot r) {
        return Command.create(
                r.verticalSlidesSubsystem::bucketServoIncrement,
                r.verticalSlidesSubsystem
        );
    }

    public static Command BucketEmpty(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::bucketServoEmpty,
            r.verticalSlidesSubsystem
        );
    }

    public static Command ArmTransfer(Robot r) {
        return Command.create(
            r.verticalSlidesSubsystem::armServoTransfer,
            r.verticalSlidesSubsystem
        );
    }

    public static Command ArmScore(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::armServoEmpty, r.verticalSlidesSubsystem);
    }
    public static Command SlidesDown(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slidesDown, r.verticalSlidesSubsystem);
    }
    public static Command SlidesZero(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::resetSlideZero, r.verticalSlidesSubsystem);
    }

    //transfer
    //inc/dec

}
