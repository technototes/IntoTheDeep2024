package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;

public class LowBasketCommand {

    public static class LowBasket extends SequentialCommandGroup {

        public LowBasket(Robot robot) {
            super();
        }

        public ParallelCommandGroup LowBasketPreSlides(Robot r) {
            return new ParallelCommandGroup(
                    Command.create(r.armSubsystem::lowBasket, r.armSubsystem),
                    Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::spitIntake, r.kidShampooSubsystem),
                    Command.create(r.kidShampooSubsystem::dumpWrist, r.kidShampooSubsystem)

            );
        }

        public SequentialCommandGroup LowBasket(Robot r) {
            return new SequentialCommandGroup(
                Command.create(r.armSubsystem::setSlideToZero, r.armSubsystem),
                new WaitCommand(0.5),
                LowBasketPreSlides(r),
                new WaitCommand(0.5),
                Command.create(r.armSubsystem::slideIntake, r.armSubsystem)
            );
        }

    }
}
