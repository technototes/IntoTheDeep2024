package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class ClawCmds {

    public static class cmds {

        public static Command OpenClaw(ClawSubsystem CS) {
            return Command.create(CS::openClaw);
        }

        public static Command CloseClaw(ClawSubsystem CS) {
            return Command.create(CS::closeClaw);
        }

        public static Command PowTest(ClawSubsystem CS) {
            return Command.create(CS::powIncrement);
        }

        public static Command PowTest2(ClawSubsystem CS) {
            return Command.create(CS::powDecrement);
        }
    }
}
