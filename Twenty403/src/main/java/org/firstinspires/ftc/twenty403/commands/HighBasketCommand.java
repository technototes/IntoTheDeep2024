package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;

public class HighBasketCommand {

    public class HighBasket extends SequentialCommandGroup {

        public ParallelCommandGroup HighBasketPreArm(Robot r) {
            return new ParallelCommandGroup(
                Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem),
                Command.create(r.kidShampooSubsystem::spitIntake, r.kidShampooSubsystem),
                Command.create(r.kidShampooSubsystem::dumpWrist, r.kidShampooSubsystem)
            );
        }

        public SequentialCommandGroup HighBasket(Robot r) {
            return new SequentialCommandGroup(
                    Command.create(r.armSubsystem::setSlideToZero, r.armSubsystem),
                    new WaitCommand(0.5),
                    Command.create(r.armSubsystem::highBasket, r.armSubsystem),
                    new WaitCommand(0.5),
                    Command.create(r.armSubsystem::highBasketSlides, r.armSubsystem),
                    new WaitCommand(0.5),
                    HighBasketPreArm(r)
            );
        }

    }

}
