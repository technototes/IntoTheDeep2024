package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.MethodCommand;
import org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem;

public class EZCmd {

    public static class Drive {

        public static Command NormalMode(DrivebaseSubsystem drive) {
            return new MethodCommand(drive::setNormalMode);
        }

        public static Command SnailMode(DrivebaseSubsystem drive) {
            return new MethodCommand(drive::setSnailMode);
        }

        public static Command TurboMode(DrivebaseSubsystem drive) {
            return new MethodCommand(drive::setTurboMode);
        }

        public static Command ResetGyro(DrivebaseSubsystem drive) {
            return new MethodCommand(drive::setExternalHeading, 0.0);
        }
    }
}
