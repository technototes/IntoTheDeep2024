package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesSequentials {

    public static SequentialCommandGroup transferVertical(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::bucketServoTransfer, r.verticalSlidesSubsystem),
                Command.create(r.verticalSlidesSubsystem::armServoTransfer, r.verticalSlidesSubsystem),
                Command.create(r.verticalSlidesSubsystem::slidesDown, r.verticalSlidesSubsystem)
                // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }
    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::bucketServoTransfer, r.verticalSlidesSubsystem),
                Command.create(r.verticalSlidesSubsystem::armServoEmpty, r.verticalSlidesSubsystem),
                Command.create(r.verticalSlidesSubsystem::slideBasketLow, r.verticalSlidesSubsystem)
        );
    }
    public static SequentialCommandGroup HighBasket(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::bucketServoTransfer, r.verticalSlidesSubsystem),
                Command.create(r.verticalSlidesSubsystem::armServoEmpty, r.verticalSlidesSubsystem),
                Command.create(r.verticalSlidesSubsystem::slideBasketHigh, r.verticalSlidesSubsystem)
        );
    }

    //transfer
    //inc/dec

}
