package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class HighBasketCommand {

    public static SequentialCommandGroup HighBasketPreArm(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.kidShampooSubsystem::closeRetainer, r.kidShampooSubsystem),
            Command.create(r.kidShampooSubsystem::spitIntake, r.kidShampooSubsystem),
            Command.create(r.kidShampooSubsystem::dumpWrist, r.kidShampooSubsystem)
        );
    }

    public static SequentialCommandGroup HighBasket(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.kidShampooSubsystem::stopIntake, r.kidShampooSubsystem),
            new MoveSlidesCommand(r.armSubsystem, r.armSubsystem::setSlideToZero),
            Command.create(r.armSubsystem::highBasket, r.armSubsystem),
            new WaitCommand(0.5),
            new MoveSlidesCommand(r.armSubsystem, r.armSubsystem::highBasketSlides),
            new WaitCommand(0.5),
            HighBasketPreArm(r)
        );
    }
}
