package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesSequentials {

    //complete sequentials
    public static SequentialCommandGroup HighBasket(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::slideBasketHigh),
                BasketScore(r)
        );
    }
    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
                BasketScore(r),
                Command.create(r.verticalSlidesSubsystem::slideBasketLow)
        );
    }
    public static SequentialCommandGroup SlidesDown(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::slideBasketLow),
                new WaitCommand(.1),
                Command.create(r.verticalSlidesSubsystem::slidesDown)
        );
    }

    //partial sequentials
    public static SequentialCommandGroup transferVertical(Robot r) {
        return new SequentialCommandGroup(
        Command.create(r.horizontalSlidesSubsystem::WristVertTransfer),
        Command.create(r.verticalSlidesSubsystem::armServoTransfer),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::slidesDown),
                new WaitCommand(.3),
                Command.create(r.verticalSlidesSubsystem::bucketServoTransfer)
                // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }
    public static SequentialCommandGroup BasketScore(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.verticalSlidesSubsystem::bucketServoLift),
                new WaitCommand(0.3),
                Command.create(r.verticalSlidesSubsystem::armServoEmpty),
                new WaitCommand(0.5),
                Command.create(r.verticalSlidesSubsystem::bucketServoEmpty)
        );
    }


}
