package org.firstinspires.ftc.sixteen750.commands.auto;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;

public class Paths {

    public static Command splineTestCommand(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPLINETEST1_TO_SPLINETEST2);
    }

    public static Command SampleScoringTest(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.START_TO_NETSCORING);
    }

    public static Command SampleScoring(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.START_TO_NETSCORING)
            //First Intake and Scoring
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE1),
                    HorizontalSlidesCommands.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE1_TO_NETSCORING),
                    HorizontalSlidesCommands.transferring(r),
                    VerticalSlidesCommands.BucketTransfer(r),
                    VerticalSlidesCommands.ArmTransfer(r)
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
                    HorizontalSlidesCommands.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE2_TO_NETSCORING),
                    HorizontalSlidesCommands.transferring(r),
                    VerticalSlidesCommands.BucketTransfer(r),
                    VerticalSlidesCommands.ArmTransfer(r)
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
                    HorizontalSlidesCommands.intake(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawChomp(r))
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE3_TO_NETSCORING),
                    HorizontalSlidesCommands.transferring(r),
                    VerticalSlidesCommands.BucketTransfer(r),
                    VerticalSlidesCommands.ArmTransfer(r)
                )
            )
            .andThen(HorizontalSlidesCommands.clawOpen(r))
            .andThen(VerticalSlidesSequentials.HighBasket(r))
            .andThen(new WaitCommand(2))
            .andThen(VerticalSlidesSequentials.transferVertical(r))
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_ASCENT_CLEAR)
            )
            .andThen(
                new ParallelCommandGroup(
                    new TrajectorySequenceCommand(
                        r.drivebase,
                        AutoConstants.ASCENT_CLEAR_TO_ASCENT
                    ),
                    VerticalSlidesCommands.ArmScore(r),
                    VerticalSlidesCommands.BucketEmpty(r)
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
        ).andThen(new TrajectorySequenceCommand(r.drivebase, AutoConstants.ASCENT_CLEAR_TO_ASCENT));
    }

    public static Command ObservationScoring(Robot r) {
        return new TrajectorySequenceCommand(
            r.drivebase,
            AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO1
        )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO2
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO5
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO6
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO7
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO8
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO9
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebase,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO10
                )
            );
    }
}
