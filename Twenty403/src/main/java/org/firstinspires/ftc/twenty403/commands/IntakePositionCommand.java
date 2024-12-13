package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.twenty403.Robot;

public class IntakePositionCommand {

    public static SequentialCommandGroup Intakepos(Robot r) {
        return new SequentialCommandGroup(
            KidShampooCmds.cmds.OpenRetainer(r.kidShampooSubsystem),
            KidShampooCmds.cmds.StopIntake(r.kidShampooSubsystem),
            KidShampooCmds.cmds.DumpWrist(r.kidShampooSubsystem),
            ArmSubCmds.cmds.slideZero(r.armSubsystem),
            ArmSubCmds.cmds.intakePos(r.armSubsystem),
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem)
        );
    }
}
