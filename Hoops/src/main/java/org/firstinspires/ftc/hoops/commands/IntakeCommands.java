package org.firstinspires.ftc.hoops.commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.hoops.Robot;

public class IntakeCommands {

    public static Command intakeCommand(Robot r) {
        return Command.create(r.intakeSubsystem::Intake);
    }

    public static Command stopIntakeCommand(Robot r) {
        return Command.create(r.intakeSubsystem::Stop);
    }
}
