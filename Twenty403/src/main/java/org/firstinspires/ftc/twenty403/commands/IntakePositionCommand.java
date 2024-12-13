package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class IntakePositionCommand {

    public static SequentialCommandGroup IntakePos(Robot r) {
        return new SequentialCommandGroup(
            KidShampooCmds.cmds.OpenRetainer(r.kidShampooSubsystem),
            KidShampooCmds.cmds.StopIntake(r.kidShampooSubsystem),
            KidShampooCmds.cmds.DumpWrist(r.kidShampooSubsystem),
            ArmSubCmds.cmds.slideZero(r.armSubsystem),
            ArmSubCmds.cmds.intakePos(r.armSubsystem),
            new WaitCommand(1),
            ArmSubCmds.cmds.intakePosSlides(r.armSubsystem),
            new WaitCommand(1),
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem),
            KidShampooCmds.cmds.SlurpIntake(r.kidShampooSubsystem),
            new WaitCommand(1),
            KidShampooCmds.cmds.StopIntake(r.kidShampooSubsystem)
        );
    }
}
