package org.firstinspires.ftc.sixteen750.commands.slides;

import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.clawChomp;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.horizontalExtend;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.horizontalRetract;
import static org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands.wristTransfer;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.sixteen750.Robot;

public class HorizontalSlidesSequentials {

    public static SequentialCommandGroup intake(Robot r) {
        return new SequentialCommandGroup(
                wristTransfer(r),
                Command.create(r.horizontalSlidesSubsystem::ClawOpen),
                horizontalExtend(r),
                new WaitCommand(1),
                Command.create(r.horizontalSlidesSubsystem::WristServoPickup)
        );
    }
    public static SequentialCommandGroup transferring(Robot r) {
        return new SequentialCommandGroup(
                wristTransfer(r),
                horizontalRetract(r),
                clawChomp(r),
                VerticalSlidesSequentials.SlidesDown(r)
        );
    }
}
