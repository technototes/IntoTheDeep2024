package org.firstinspires.ftc.sixteen750.commands.auto;

import com.technototes.library.command.Command;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Robot;

public class Paths {

    public static Command splineTestCommand(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.SPLINETEST1_TO_SPLINETEST2);
    }

    public static Command SampleScoring(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.START_TO_NETSCORING)
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE1)
            )
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE1_TO_NETSCORING)
            )
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE2)
            )
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE2_TO_NETSCORING)
            )
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.NETSCORING_TO_INTAKE3)
            )
            .andThen(
                new TrajectorySequenceCommand(r.drivebase, AutoConstants.INTAKE3_TO_NETSCORING)
            );
    }

    public static TrajectorySequenceCommand Obs_Parking(Robot r) {
        return new TrajectorySequenceCommand(r.drivebase, AutoConstants.OBS_START_TO_OBS_PARK);
    }
}
