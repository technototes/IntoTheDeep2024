package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;

public class LowSpecimenCommand {


        public static ParallelCommandGroup LowSpecimenPreArm(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.kidShampooSubsystem::releaseJaw, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::straightWrist, r.kidShampooSubsystem)
            );
        }

        public static SequentialCommandGroup LowSpecimen(Robot r) {
            return new SequentialCommandGroup(
                    Command.create(r.armSubsystem::setSlideToZero, r.armSubsystem),
                    new WaitCommand(0.5),
                    Command.create(r.armSubsystem::lowSpecimen, r.armSubsystem),
                    new WaitCommand(0.5),
                    Command.create(r.armSubsystem::lowSpecimenSlides, r.armSubsystem),
                    new WaitCommand(0.5),
                    LowSpecimenPreArm(r)
            );
        }
}

