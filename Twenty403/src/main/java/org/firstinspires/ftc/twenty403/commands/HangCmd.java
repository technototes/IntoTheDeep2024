package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystems.HangSubsystem;

public class HangCmd {

    public static class hang {

        public static Command Suspend(HangSubsystem hang){
            return Command.create(hang::suspend);
        }
    }
}
