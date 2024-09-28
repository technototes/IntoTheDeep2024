package org.firstinspires.ftc.sixteen750.commands.auto;

import static org.firstinspires.ftc.sixteen750.AutoConstants.TEST2_TO_TEST3;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Robot;

public class SideAndBackCommand extends SequentialCommandGroup {

    public SideAndBackCommand(Robot r) {
        super(
            new TrajectorySequenceCommand(
                r.drivebase,
                AutoConstants.SIDE_LEFT_TO_SIDE_RIGHT
            )
        );
    }
}
