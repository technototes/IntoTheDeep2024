package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesCommands {

    public static Command HighBasket(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::SlideBasketHigh, r.verticalSlidesSubsystem);
    }

    public static Command LowBasket(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::SlideBucketLow, r.verticalSlidesSubsystem);
    }

    public static Command HighChamber(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::SlideChamberHigh, r.verticalSlidesSubsystem);
    }

    public static Command LowChamber(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::SlideChamberLow, r.verticalSlidesSubsystem);
    }

    public static Command LiftIntake(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::LiftHeightIntake, r.verticalSlidesSubsystem);
    }
    public static Command BucketTransfer(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::BucketServoTransfer, r.verticalSlidesSubsystem);
    }
    public static Command horizontalExtend(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::slidesout, r.horizontalSlidesSubsystem);
    }
    public static Command ArmTransfer(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::ArmServoTransfer, r.verticalSlidesSubsystem);
    }
    public static Command ArmScore(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::ArmServoEmpty, r.verticalSlidesSubsystem);
    }
    public static Command SlidesZero(Robot r) {
        return Command.create(r.verticalSlidesSubsystem::slidesdown, r.verticalSlidesSubsystem);
    }
    //transfer
    //inc/dec

}
