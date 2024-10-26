package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.twenty403.Robot;

public class HighSpecimenCommand {

    public class HighSpecimen extends ParallelCommandGroup {

        public ParallelCommandGroup HighSpecimen1(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::highSpecimenRotate1, r.armSubsystem),
                    Command.create(r.armSubsystem::highSpecimenSlides1, r.armSubsystem)
            );
        }

        public ParallelCommandGroup HighSpecimen2(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::highSpecimenRotate2, r.armSubsystem),
                    Command.create(r.armSubsystem::highSpecimenSlides2, r.armSubsystem)
            );
        }

        public SequentialCommandGroup HighSpecimen(Robot r) {
            return new SequentialCommandGroup(
                    HighSpecimen1(r),
                    HighSpecimen2(r)
            );
        }

    }

}