package org.firstinspires.ftc.hoops.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.subsystems.IntakeSubsystem;

public class IntakeAndLaunchCommand {

    public static ParallelCommandGroup IntakeAndLaunch(Robot r) {
        return new ParallelCommandGroup(
            IntakeCommands.intakeCommand(r),
            LaunchCommands.launchCommand(r)
        );
    }

    public static ParallelCommandGroup IntakeAndLaunchStop(Robot r) {
        return new ParallelCommandGroup(
            IntakeCommands.stopIntakeCommand(r),
            LaunchCommands.stopLaunchCommand(r)
        );
    }
}
