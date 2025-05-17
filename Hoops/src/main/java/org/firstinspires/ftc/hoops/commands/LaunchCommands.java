package org.firstinspires.ftc.hoops.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.MethodCommand;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.subsystems.LauncherSubsystem;

public class LaunchCommands {

    public static Command launchCommand(Robot r) {
        return Command.create(r.launcherSubsystem::Launch, 45.0);
    }

    public static Command stopLaunchCommand(Robot r) {
        return Command.create(r.launcherSubsystem::Stop);
    }
}
