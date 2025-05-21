package org.firstinspires.ftc.ptechnodactyl.commands;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.Robot;

public class ClawNeutralCmd {

    public static ParallelCommandGroup ClawNeutral(Robot r) {
        return new ParallelCommandGroup(
            ClawCmds.cmds.ClawNeutralLeft(r.clawSubsystem),
            ClawCmds.cmds.ClawNeutralRight(r.clawSubsystem)
        );
    }
}
