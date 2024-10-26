package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;

import org.firstinspires.ftc.twenty403.Robot;

public class IntakeSampleCommand {

    public class Intake extends ParallelCommandGroup {

        public ParallelCommandGroup IntakeSample(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::intakeRotateSample, r.armSubsystem),
                    Command.create(r.armSubsystem::intakeSlidesSample, r.armSubsystem),
                    Command.create(r.kidShampooSubsystem::slurpIntake, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::eatRetainer, r.kidShampooSubsystem)
            );
        }
    }

}