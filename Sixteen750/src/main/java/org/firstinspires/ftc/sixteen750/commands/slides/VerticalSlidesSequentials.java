package org.firstinspires.ftc.sixteen750.commands.slides;

import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.ArmEmpty;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.ArmTransfer;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.BucketEmpty;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.BucketLift;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.BucketTransfer;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.HighBasketCommand;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.LowBasketCommand;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.SlidesDownCommand;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesSequentials {

    //complete sequentials
    public static SequentialCommandGroup HighBasket(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
                transferScore(r),
                new WaitCommand(.3),
                HighBasketCommand(r),
                BasketScore(r)
        );
    }
    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
                transferScore(r),
                new WaitCommand(.3),
                LowBasketCommand(r),
                BasketScore(r)
        );
    }
    public static SequentialCommandGroup SlidesDown(Robot r) {
        return new SequentialCommandGroup(
                LowBasketCommand(r),
                new WaitCommand(.1),
                SlidesDownCommand(r),
                transferVertical(r)
        );
    }

    //partial sequentials
    public static SequentialCommandGroup transferVertical(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.horizontalSlidesSubsystem::WristVertTransfer),
                new WaitCommand(.3),
                BucketLift(r),
                ArmTransfer(r),
                new WaitCommand(.3),
                SlidesDownCommand(r),
                new WaitCommand(.3),
                BucketTransfer(r)
                // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }
    public static SequentialCommandGroup transferScore(Robot r) {
        return new SequentialCommandGroup(
                Command.create(r.horizontalSlidesSubsystem::WristVertTransfer),
                new WaitCommand(.3),
                BucketLift(r),
                ArmTransfer(r),
                new WaitCommand(.3),
                SlidesDownCommand(r)
                // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }
    public static SequentialCommandGroup BasketScore(Robot r) {
        return new SequentialCommandGroup(
                BucketLift(r),
                new WaitCommand(0.3),
                ArmEmpty(r),
                new WaitCommand(0.7),
                BucketEmpty(r),
                new WaitCommand(.3),
                BucketLift(r)
        );
    }


}

