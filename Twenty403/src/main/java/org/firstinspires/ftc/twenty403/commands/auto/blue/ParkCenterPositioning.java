package org.firstinspires.ftc.twenty403.commands.auto.blue;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Robot;

public class ParkCenterPositioning extends SequentialCommandGroup {

    public ParkCenterPositioning(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebaseSubsystem,
                AutoConstants.WingBlue.LEFT_CLEAR_TO_MID_PARK_CENTER
            )
        );
    }
}
