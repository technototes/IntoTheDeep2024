package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.Robot;

public class ClawDpadUCmd {

    public static ParallelCommandGroup ClawDpadU(Robot r) {
        return new ParallelCommandGroup(
            ClawCmds.cmds.ClawDpaduLeft(r.clawSubsystem),
            ClawCmds.cmds.ClawDpaduRight(r.clawSubsystem)
        );
    }
}
