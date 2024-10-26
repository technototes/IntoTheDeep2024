package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;

import org.firstinspires.ftc.twenty403.Robot;

public class NeutralSpecimenCommand {

    public class Neutral extends ParallelCommandGroup {

        public ParallelCommandGroup Neutral(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::neutralRotate, r.armSubsystem),
                    Command.create(r.armSubsystem::neutralSlides, r.armSubsystem),
                    Command.create(r.kidShampooSubsystem::biteJaw, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem)
            );
        }
    }

}