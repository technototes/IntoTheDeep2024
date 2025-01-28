package org.firstinspires.ftc.sixteen750.commands.auto;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesSequentials;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;

public class Paths {

    public static Command splineTestCommand(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPLINETEST1_TO_SPLINETEST2);
    }

    public static Command SampleScoringTest(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.START_TO_NETSCORING)
            .alongWith(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(0.3))
            .andThen(VerticalSlidesSequentials.SlidesDown(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE1),
                    HorizontalSlidesSequentials.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(new WaitCommand(0.3))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE1_TO_NETSCORING),
                    HorizontalSlidesCommands.wristTransfer(r), //HorizontalSlidesSequentials.transferring(r),
                    HorizontalSlidesCommands.horizontalRetract(r),
                    VerticalSlidesCommands.ArmTransfer(r),
                    VerticalSlidesCommands.BucketTransfer(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawOpen(r))
            .andThen(new WaitCommand(0.3))
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(0.3))
            .andThen(VerticalSlidesSequentials.SlidesDown(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE2),
                    HorizontalSlidesSequentials.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(new WaitCommand(0.3))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE2_TO_NETSCORING),
                    HorizontalSlidesCommands.wristTransfer(r), //HorizontalSlidesSequentials.transferring(r),
                    HorizontalSlidesCommands.horizontalRetract(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawOpen(r))
            .andThen(new WaitCommand(0.3))
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(0.3))
            .andThen(VerticalSlidesSequentials.SlidesDown(r))
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_ASCENT_CLEAR)
                    .andThen(
                        new TrajectorySequenceCommand(
                            r.drivebase,
                            AutoConstants.ASCENT_CLEAR_TO_ASCENT
                        )
                    )
                    .alongWith(VerticalSlidesSequentials.BasketScore(r))
            );
    }

    public static Command SampleScoringTest2(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.START_TO_NETSCORING)
            .alongWith(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(2))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE1),
                    HorizontalSlidesSequentials.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r));
    }

    public static Command SampleScoring(Robot r) {
        // Preload Scoring
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.START_TO_NETSCORING)
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(2))
            .andThen(VerticalSlidesSequentials.transferVertical(r))
            //First Intake and Scoring
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE1),
                    HorizontalSlidesSequentials.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE1_TO_NETSCORING),
                    HorizontalSlidesSequentials.transferring(r),
                    VerticalSlidesSequentials.transferVertical(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawOpen(r))
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(2))
            .andThen(VerticalSlidesSequentials.transferVertical(r))
            //Second Intake and Scoring
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE2),
                    HorizontalSlidesSequentials.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE2_TO_NETSCORING),
                    HorizontalSlidesSequentials.transferring(r),
                    VerticalSlidesSequentials.transferVertical(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawOpen(r))
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(2))
            .andThen(VerticalSlidesSequentials.transferVertical(r))
            //Third Intake and Scoring
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE3),
                    HorizontalSlidesSequentials.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE3_TO_NETSCORING),
                    HorizontalSlidesSequentials.transferring(r),
                    VerticalSlidesSequentials.transferVertical(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawOpen(r))
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(2))
            .andThen(VerticalSlidesSequentials.transferVertical(r))
            //Parking and Ascent 1
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_ASCENT_CLEAR)
            )
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(
                        r.drivebase,
                        AutoConstants.ASCENT_CLEAR_TO_ASCENT
                    ),
                    VerticalSlidesSequentials.BasketScore(r)
                )
            );
    }

    public static Command Obs_Parking(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.OBS_START_TO_OBS_PARK);
    }

    public static Command Net_Parking(Robot r) {
        return new TrajectorySequenceCommand(
            r.drivebase,
            AutoConstants.NET_START_TO_ASCENT_CLEAR
        ).andThen(
            new TrajectorySequenceCommand(r.drivebase, AutoConstants.ASCENT_CLEAR_TO_ASCENT)
                .andThen(VerticalSlidesCommands.ArmEmpty(r))
                .alongWith(VerticalSlidesCommands.BucketEmpty(r))
        );
    }

    public static Command ObservationPushing(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO1)
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO2))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO3))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO4))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO5))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO6))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO7))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO8))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO9))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO9))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO10))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO11))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO12))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO13))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO14))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO15))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO16))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO17))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO18))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO19))
            .andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPEC_SCORING_AUTO20));
    }
}
