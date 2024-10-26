package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.twenty403.Robot;

public class LowBasketCommand {

    public static class LowBasket extends SequentialCommandGroup {

        public LowBasket(Robot robot) {
            super();
        }

        public SequentialCommandGroup LowBasket(Robot r) {
            return new SequentialCommandGroup(
                    Command.create(r.armSubsystem::lowBasketRotate, r.armSubsystem),
                    Command.create(r.armSubsystem::lowBasketSlides, r.armSubsystem),
                    Command.create(r.kidShampooSubsystem::openRetainer, r.kidShampooSubsystem)

            );
        }
    }

}