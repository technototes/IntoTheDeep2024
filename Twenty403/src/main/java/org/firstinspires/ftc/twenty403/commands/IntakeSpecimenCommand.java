package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.twenty403.Robot;

public class IntakeSpecimenCommand {

    public class Intake extends ParallelCommandGroup {

        public ParallelCommandGroup IntakeSpecimen(Robot r) {
            return new ParallelCommandGroup(
                Command.create(r.armSubsystem::setArmToIntake, r.armSubsystem),
                Command.create(r.kidShampooSubsystem::releaseJaw, r.kidShampooSubsystem),
                Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem)
            );
        }
    }
}
