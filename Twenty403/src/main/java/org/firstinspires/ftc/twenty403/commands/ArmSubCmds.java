package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.commands.driving.MoveArmCommand;
import org.firstinspires.ftc.twenty403.subsystems.ArmSubsystem;

public class ArmSubCmds {

    public static class cmds {

        public static Command slideZero(ArmSubsystem AS) {
            return Command.create(AS::setSlideToZero);
        }

        public static Command resetSlides(ArmSubsystem AS) {
            return Command.create(AS::resetSlideZero);
        }

        public static Command highbasketSlide(ArmSubsystem AS) {
            return Command.create(AS::highBasketSlides);
        }

        public static Command highbasketArm(ArmSubsystem AS) {
            return Command.create(AS::highBasket);
        }

        public static Command highbasketArmAuto(ArmSubsystem AS) {
            return Command.create(AS::highBasketAuto);
        }

        public static Command highbasketArmWaitTillDone(ArmSubsystem AS) {
            return new MoveArmCommand(AS, AS::highBasket);
        }

        public static Command lowbasketArm(ArmSubsystem AS) {
            return Command.create(AS::lowBasket);
        }

        public static Command setArmToIntake(ArmSubsystem AS) {
            return Command.create(AS::setArmToIntake);
        }

        public static Command setSlidesToIntake(ArmSubsystem AS) {
            return Command.create(AS::setSlidesToIntake);
        }
    }
}
