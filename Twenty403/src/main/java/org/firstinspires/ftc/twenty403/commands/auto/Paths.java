package org.firstinspires.ftc.twenty403.commands.auto;

import com.technototes.library.command.Command;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Robot;

public class Paths {

    public static Command SampleScoring(Robot r) {
        return new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.OBSERVATION_TEST1)
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO1
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO2
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO5
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO6
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO7
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO8
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO9
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO10
                )
            );
    }
}
