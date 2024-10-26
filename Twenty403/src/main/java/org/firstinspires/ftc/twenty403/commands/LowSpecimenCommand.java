package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.twenty403.Robot;

public class LowSpecimenCommand {

    public class LowSpecimen extends ParallelCommandGroup {

        public ParallelCommandGroup LowSpecimen1(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::lowSpecimenRotate1, r.armSubsystem),
                    Command.create(r.armSubsystem::lowSpecimenSlides1, r.armSubsystem)
            );
        }

        public ParallelCommandGroup LowSpecimen2(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::lowSpecimenRotate2, r.armSubsystem),
                    Command.create(r.armSubsystem::lowSpecimenSlides2, r.armSubsystem)
            );
        }

        public SequentialCommandGroup LowSpecimen(Robot r) {
            return new SequentialCommandGroup(
                    LowSpecimen1(r),
                    LowSpecimen2(r),
                    Command.create(r.kidShampooSubsystem::releaseJaw, r.armSubsystem)

            );
        }

    }

}