package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;

public class LowSpecimenCommand {

    public class LowSpecimen extends ParallelCommandGroup {

        public ParallelCommandGroup LowSpecimenPreSlides(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::horizontal, r.armSubsystem),
                    Command.create(r.kidShampooSubsystem::releaseJaw, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::scoopWrist, r.kidShampooSubsystem)
            );
        }

        public SequentialCommandGroup IntakeSpecimen(Robot r) {
            return new SequentialCommandGroup(
                    Command.create(r.armSubsystem::setSlideToZero, r.armSubsystem),
                    new WaitCommand(0.5),
                    LowSpecimenPreSlides(r)
            );
        }
    }
}
