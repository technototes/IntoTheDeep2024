package org.firstinspires.ftc.twenty403.commands.auto.red;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.AutoConstants.WingRed;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.commands.auto.SafetyStartCommand;
import org.firstinspires.ftc.twenty403.commands.auto.SafetyStopCommand;

public class WingPixelLeft extends SequentialCommandGroup {

    public WingPixelLeft(Robot r) {
        super(
            new SafetyStartCommand(r.safetySubsystem),
            new TrajectorySequenceCommand(r.drivebaseSubsystem, WingRed.START_TO_LEFT_SPIKE),
            new TrajectorySequenceCommand(r.drivebaseSubsystem, WingRed.LEFT_SPIKE_TO_CLEAR),
            new SafetyStopCommand(r.safetySubsystem),
            new WaitCommand(5),
            new SafetyStartCommand(r.safetySubsystem),
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.WingRed.CLEAR_TO_PARK_CORNER
            )
        );
    }
}
