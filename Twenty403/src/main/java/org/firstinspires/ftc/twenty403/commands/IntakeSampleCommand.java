package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;

public class IntakeSampleCommand {



        public static ParallelCommandGroup IntakeSamplePreArm(Robot r) {
            return new ParallelCommandGroup(
                Command.create(r.kidShampooSubsystem::slurpIntake, r.kidShampooSubsystem),
                Command.create(r.kidShampooSubsystem::closeRetainer, r.kidShampooSubsystem),
                Command.create(r.kidShampooSubsystem::scoopWrist, r.kidShampooSubsystem)
            );
        }

        public static SequentialCommandGroup IntakeSample(Robot r) {
            return new SequentialCommandGroup(
                    Command.create(r.armSubsystem::setSlideToZero, r.armSubsystem),
                    new WaitCommand(0.5),
                    Command.create(r.armSubsystem::setArmToIntake, r.armSubsystem),
                    new WaitCommand(0.5),
                    Command.create(r.armSubsystem::slideIntake, r.armSubsystem),
                    new WaitCommand(0.5),
                    IntakeSamplePreArm(r)
            );
        }

    }

