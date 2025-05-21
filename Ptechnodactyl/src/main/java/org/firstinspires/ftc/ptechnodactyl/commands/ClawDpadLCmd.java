package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.Robot;

public class ClawDpadLCmd {

    public static ParallelCommandGroup ClawDpadL(Robot r) {
        return new ParallelCommandGroup(
            ClawCmds.cmds.ClawDpadlLeft(r.clawSubsystem),
            ClawCmds.cmds.ClawDpadlRight(r.clawSubsystem)
        );
    }
}
