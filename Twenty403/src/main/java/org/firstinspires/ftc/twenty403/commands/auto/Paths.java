package org.firstinspires.ftc.twenty403.commands.auto;

import com.technototes.library.command.Command;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Robot;

public class Paths {

    public static Command ObservationScoring(Robot r) {
        return new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO1)
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

    public static Command SampleScoring(Robot r) {
        return new TrajectorySequenceCommand(
            r.drivebaseSubsystem,
            AutoConstants.START_TO_NETSCORING
        )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.NETSCORING_TO_INTAKE1
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.INTAKE1_TO_NETSCORING
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.NETSCORING_TO_INTAKE2
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.INTAKE2_TO_NETSCORING
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.NETSCORING_TO_INTAKE3
                )
            )
            .andThen(
                new TrajectorySequenceCommand(
                    r.drivebaseSubsystem,
                    AutoConstants.INTAKE3_TO_NETSCORING
                )
            );
    }
}
