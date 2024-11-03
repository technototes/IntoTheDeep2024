package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.twenty403.Robot;

public class LowSpecimenCommand {

    public class LowSpecimen extends ParallelCommandGroup {

        public SequentialCommandGroup LowSpecimen(Robot r) {
            return new SequentialCommandGroup(
                Command.create(r.armSubsystem::lowSpecimen, r.armSubsystem),
                Command.create(r.kidShampooSubsystem::releaseJaw, r.kidShampooSubsystem)
            );
        }
    }
}
