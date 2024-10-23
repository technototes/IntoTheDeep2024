package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.subsystems.ArmSubsystem;

public class BasketCommand {

    public class LowBasket extends SequentialCommandGroup {

        public LowBasket(Robot r) {
            return new SequentialCommandGroup(
                    Command.create(r.armSubsystem::lowBasketRotate1, r.armSubsystem),
                    Command.create(r.armSubsystem::lowBasketSlides, r.armSubsystem)
            )
        }
    }

}