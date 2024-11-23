package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesSequentials {

    public static SequentialCommandGroup transferVertical(Robot r) {
        return new SequentialCommandGroup(
        Command.create(r.horizontalSlidesSubsystem::VertExtendTransfer, r.horizontalSlidesSubsystem),
        Command.create(r.verticalSlidesSubsystem::armServoTransfer, r.verticalSlidesSubsystem),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::slidesDown, r.verticalSlidesSubsystem),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::bucketServoTransfer, r.verticalSlidesSubsystem)
                // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }
    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
                BasketScore(r),
                Command.create(r.verticalSlidesSubsystem::slideBasketLow, r.verticalSlidesSubsystem)
        );
    }

    public static SequentialCommandGroup HighBasket(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
                BasketScore(r),
                Command.create(r.verticalSlidesSubsystem::slideBasketHigh, r.verticalSlidesSubsystem)
        );
    }
    public static SequentialCommandGroup HighDown(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::slideBasketLow, r.verticalSlidesSubsystem),
                new WaitCommand(.1),
                Command.create(r.verticalSlidesSubsystem::slidesDown, r.verticalSlidesSubsystem)
        );
    }
    public static SequentialCommandGroup BasketScore(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::bucketServoLift, r.verticalSlidesSubsystem),
                new WaitCommand(0.3),
                Command.create(r.verticalSlidesSubsystem::armServoEmpty, r.verticalSlidesSubsystem),
                new WaitCommand(0.5),
                Command.create(r.verticalSlidesSubsystem::bucketServoEmpty, r.verticalSlidesSubsystem)
        );
    }

    //transfer
    //inc/dec

}
