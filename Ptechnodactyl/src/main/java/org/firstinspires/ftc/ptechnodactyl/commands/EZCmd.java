package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.MethodCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;

public class EZCmd {

    public static class Drive {

        public static Command TurboMode(DrivebaseSubsystem db) {
            return new MethodCommand(db::setTurboMode);
        }

        public static Command NormalMode(DrivebaseSubsystem db) {
            return new MethodCommand(db::setNormalMode);
        }

        public static Command SnailMode(DrivebaseSubsystem db) {
            return new MethodCommand(db::setSnailMode);
        }

        public static Command ResetGyro(DrivebaseSubsystem db) {
            return new MethodCommand(db::setExternalHeading, 0.0);
        }
    }
}
