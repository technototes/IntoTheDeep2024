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
            ArmSubCmds.cmds.setArmToIntake(r.armSubsystem),
            KidShampooCmds.cmds.ScoopWrist(r.kidShampooSubsystem),
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem)
            /*new WaitCommand(0.5),
            ArmSubCmds.cmds.setSlidesToIntake(r.armSubsystem),
            new WaitCommand(0.5),
            KidShampooCmds.cmds.ScoopWrist(r.kidShampooSubsystem),
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem),
            KidShampooCmds.cmds.SlurpIntake(r.kidShampooSubsystem),
            new WaitCommand(2),
            KidShampooCmds.cmds.StopIntake(r.kidShampooSubsystem)*/
        );
    }
}
