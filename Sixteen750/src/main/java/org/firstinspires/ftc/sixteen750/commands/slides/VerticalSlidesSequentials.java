package org.firstinspires.ftc.sixteen750.commands.slides;

import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.ArmEmpty;
import static org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands.ArmEmptyAuto;
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
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Robot;

public class VerticalSlidesSequentials {

    //complete sequentials
    public static SequentialCommandGroup HighBasket(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
            transferScore(r),
            new WaitCommand(.3),
            HighBasketCommand(r),
            BasketScore(r),
            new WaitCommand(.5),
            SlidesDown(r)
        );
    }

    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
            transferScore(r),
            new WaitCommand(.3),
            LowBasketCommand(r),
            BasketScore(r),
            new WaitCommand(.5),
            SlidesDown(r)
        );
    }

    public static SequentialCommandGroup SlidesDown(Robot r) {
        return new SequentialCommandGroup(
            //            LowBasketCommand(r),
            //            new WaitCommand(.3),
            transferVertical(r)
        );
    }

    public static SequentialCommandGroup SlidesDownAuto(Robot r) {
        return new SequentialCommandGroup(
            LowBasketCommand(r),
            new WaitCommand(.1),
            SlidesDownCommand(r),
            transferScoreAuto(r)
        );
    }

    public static SequentialCommandGroup HighBasketAuto(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
            transferScoreAuto(r),
            new WaitCommand(.1),
            HighBasketCommand(r),
            BasketScoreAuto(r),
            new WaitCommand(.15),
            SlidesDownAuto(r)
                .alongWith(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE1)
                )
                .alongWith(HorizontalSlidesSequentials.intake(r))
        );
    }

    public static SequentialCommandGroup HighBasketAuto2(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
            transferScoreAuto(r),
            new WaitCommand(.1),
            HighBasketCommand(r),
            BasketScoreAuto(r),
            new WaitCommand(.15),
            SlidesDownAuto(r)
                .alongWith(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE2)
                )
                .alongWith(HorizontalSlidesSequentials.intake(r))
        );
    }

    public static SequentialCommandGroup HighBasketAuto3(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
            transferScoreAuto(r),
            new WaitCommand(.1),
            HighBasketCommand(r),
            BasketScoreAuto(r),
            new WaitCommand(.15),
            SlidesDownAuto(r)
                .alongWith(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE3)
                )
                .alongWith(HorizontalSlidesSequentials.intake(r))
        );
    }

    public static SequentialCommandGroup HighBasketAuto4(Robot r) { //need to change armScore
        return new SequentialCommandGroup(
            transferScoreAuto(r),
            new WaitCommand(.1),
            HighBasketCommand(r),
            BasketScoreAuto(r),
            new WaitCommand(.15),
            SlidesDownAuto(r)
        );
    }

    //partial sequentials
    public static SequentialCommandGroup transferVertical(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.horizontalSlidesSubsystem::slidesTransfer),
            new WaitCommand(.3),
            BucketLift(r),
            (ArmTransfer(r)),
            new WaitCommand(.3),
            SlidesDownCommand(r),
            new WaitCommand(.3),
            BucketTransfer(r)
            // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }

    public static SequentialCommandGroup transferScore(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.horizontalSlidesSubsystem::slidesTransfer),
            new WaitCommand(.3),
            BucketLift(r),
            (ArmTransfer(r)),
            new WaitCommand(.3),
            SlidesDownCommand(r)
            // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }

    public static SequentialCommandGroup transferScoreAuto(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.horizontalSlidesSubsystem::WristVertTransfer),
            new WaitCommand(.3),
            BucketLift(r),
            (ArmTransfer(r)),
            new WaitCommand(.3),
            SlidesDownCommand(r)
            // commands for vertical slide bucket transfer position first, then wrist transferring
        );
    }

    public static SequentialCommandGroup transferBucket(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.horizontalSlidesSubsystem::slidesTransfer),
            new WaitCommand(.3),
            BucketTransfer(r),
            (ArmTransfer(r)),
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
            new WaitCommand(.5),
            BucketLift(r),
            new WaitCommand(0.2)
        );
    }

    public static SequentialCommandGroup BasketScoreAuto(Robot r) {
        return new SequentialCommandGroup(
            BucketLift(r),
            new WaitCommand(0.3),
            ArmEmptyAuto(r),
            new WaitCommand(0.7),
            BucketEmpty(r),
            new WaitCommand(.5),
            BucketLift(r),
            new WaitCommand(0.2),
            BucketTransfer(r)
        );
    }

    public static SequentialCommandGroup BasketAscent(Robot r) {
        return new SequentialCommandGroup(
            BucketLift(r),
            new WaitCommand(0.3),
            ArmEmpty(r),
            new WaitCommand(0.7),
            BucketEmpty(r)
        );
    }
}
