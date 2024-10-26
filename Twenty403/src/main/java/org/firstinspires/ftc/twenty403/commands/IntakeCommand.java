package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.twenty403.Robot;

public class IntakeCommand {

    public class Intake extends ParallelCommandGroup {

        public ParallelCommandGroup Intake(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::intakeRotate, r.armSubsystem),
                    Command.create(r.armSubsystem::intakeSlides, r.armSubsystem),
                    Command.create(r.kidShampooSubsystem::slurpIntake, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::eatRetainer, r.kidShampooSubsystem)
            );
        }
    }

}