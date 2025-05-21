package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class ClawCmds {

    public static class cmds {

        public static Command OpenClamp(ClawSubsystem CS) {
            return Command.create(CS::openClamp);
        }

        public static Command CloseClamp(ClawSubsystem CS) {
            return Command.create(CS::closeClamp);
        }

        public static Command ClawDownLeft(ClawSubsystem CS) {
            return Command.create(CS::downLeftClaw);
        }

        public static Command ClawDownRight(ClawSubsystem CS) {
            return Command.create(CS::downRightClaw);
        }

        public static Command ClawNeutralLeft(ClawSubsystem CS) {
            return Command.create(CS::neutralLeftClaw);
        }

        public static Command ClawNeutralRight(ClawSubsystem CS) {
            return Command.create(CS::neutralRightClaw);
        }

        public static Command ClawDpadlLeft(ClawSubsystem CS) {
            return Command.create(CS::dpadlLeftClaw);
        }

        public static Command ClawDpadlRight(ClawSubsystem CS) {
            return Command.create(CS::dpadlRightClaw);
        }

        public static Command ClawDpadrLeft(ClawSubsystem CS) {
            return Command.create(CS::dpadrLeftClaw);
        }

        public static Command ClawDpadrRight(ClawSubsystem CS) {
            return Command.create(CS::dpadrRightClaw);
        }

        public static Command ClawDpaduLeft(ClawSubsystem CS) {
            return Command.create(CS::dpaduLeftClaw);
        }

        public static Command ClawDpaduRight(ClawSubsystem CS) {
            return Command.create(CS::dpaduRightClaw);
        }

        public static Command PowTest(ClawSubsystem CS) {
            return Command.create(CS::powIncrement);
        }

        public static Command PowTest2(ClawSubsystem CS) {
            return Command.create(CS::powDecrement);
        }
    }
}
