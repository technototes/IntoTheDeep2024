package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.sixteen750.Robot;

public class SlidesCommands {
    //command.create(something something)
    //low basket, high basket, low specimen, high specimen, bucket transfer, clawwrist transfer, inc/dec, pickup, claw
    public static Command horizontalExtend(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::slidesout, r.horizontalSlidesSubsystem);
    }
    public static Command horizontalRetract(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::slidesin, r.horizontalSlidesSubsystem);
    }
    public static Command clawChomp(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::ClawServoChomp, r.horizontalSlidesSubsystem);
    }
    public static Command clawOpen(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::ClawServoBigOpen, r.horizontalSlidesSubsystem);
    }
    public static Command wristTransfer(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoTransfer, r.horizontalSlidesSubsystem);
    }
    public static SequentialCommandGroup wristTransferring(Robot r){
        return new SequentialCommandGroup(
                Command.create(r.horizontalSlidesSubsystem::ClawWristServoTransfer, r.horizontalSlidesSubsystem),
                Command.create(r.horizontalSlidesSubsystem::ClawServoBigOpen,r.horizontalSlidesSubsystem)
                // put the commands for vertical slide bucket transfer position, then placing position, then bucket wrist scoring position
                );
    }
    public static Command wristPickup(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoPickup, r.horizontalSlidesSubsystem);
    }
    public static Command wristIncrement(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoIncrement, r.horizontalSlidesSubsystem);
    }
    public static Command wristDecrement(Robot r){
        return Command.create(r.horizontalSlidesSubsystem::ClawWristServoDecrement, r.horizontalSlidesSubsystem);
    }
}
