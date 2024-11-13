package org.firstinspires.ftc.twenty403.commands.auto;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Robot;

public class ForwardBackwardSideCommand extends SequentialCommandGroup {

    public ForwardBackwardSideCommand(Robot r) {
        super(
            new TrajectorySequenceCommand(r.drivebaseSubsystem, AutoConstants.FORWARD_BACKWARD1)
                .andThen(
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.FORWARD_BACKWARD2
                    )
                )
                .andThen(
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.FORWARD_BACKWARD3
                    )
                )
                .andThen(
                    new TrajectorySequenceCommand(
                        r.drivebaseSubsystem,
                        AutoConstants.FORWARD_BACKWARD4
                    )
                )
        );
    }
}
