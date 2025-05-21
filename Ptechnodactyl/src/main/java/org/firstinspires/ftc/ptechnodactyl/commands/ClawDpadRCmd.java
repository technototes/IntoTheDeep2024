package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.Robot;

public class ClawDpadRCmd {

    public static ParallelCommandGroup ClawDpadR(Robot r) {
        return new ParallelCommandGroup(
            ClawCmds.cmds.ClawDpadrLeft(r.clawSubsystem),
            ClawCmds.cmds.ClawDpadrRight(r.clawSubsystem)
        );
    }
}
