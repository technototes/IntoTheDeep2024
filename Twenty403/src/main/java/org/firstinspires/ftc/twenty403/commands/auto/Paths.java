package org.firstinspires.ftc.twenty403.commands.auto;

import com.technototes.library.command.Command;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Robot;

public class Paths {
    public static Command splineTestCommand(Robot r) {
        return new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.SPLINETEST1_TO_SPLINETEST2
        );
    }

    public static Command pushPathSpline(Robot r) {
        return new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.PUSH_BOT
        );


    }
}