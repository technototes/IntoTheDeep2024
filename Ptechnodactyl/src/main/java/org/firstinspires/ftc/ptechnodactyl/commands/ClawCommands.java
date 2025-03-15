package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class ClawCommands {
    //create claw commands for the buttons

    public static Command clawOpen(ClawSubsystem CS) {
        return Command.create(CS::setClawOpen);
    }

    public static Command clawClose(ClawSubsystem CS) {
        return Command.create(CS::setClawClose);
    }
}
