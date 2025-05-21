package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.Robot;

public class ClawDownCmd {

    public static ParallelCommandGroup ClawDown(Robot r) {
        return new ParallelCommandGroup(
            ClawCmds.cmds.ClawDownLeft(r.clawSubsystem),
            ClawCmds.cmds.ClawDownRight(r.clawSubsystem)
        );
    }
}
