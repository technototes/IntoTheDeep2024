package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class LowBasketCommand {

    public static SequentialCommandGroup LowBasketPreArm(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.kidShampooSubsystem::closeRetainer, r.kidShampooSubsystem),
            Command.create(r.kidShampooSubsystem::spitIntake, r.kidShampooSubsystem),
            Command.create(r.kidShampooSubsystem::dumpWrist, r.kidShampooSubsystem)
        );
    }

    public static SequentialCommandGroup LowBasket(Robot r) {
        return new SequentialCommandGroup(
            Command.create(r.kidShampooSubsystem::stopIntake),
            new MoveSlidesCommand(r.armSubsystem, r.armSubsystem::setSlideToZero),
            new WaitCommand(0.5),
            Command.create(r.armSubsystem::lowBasket, r.armSubsystem),
            new WaitCommand(0.5),
            new MoveSlidesCommand(r.armSubsystem, r.armSubsystem::lowBasketSlides),
            new WaitCommand(0.5),
            LowBasketPreArm(r)
        );
    }
}
