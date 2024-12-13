package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.twenty403.Robot;

public class HighBasketCommand {

    public static SequentialCommandGroup HighBasketPreArm(Robot r) {
        return new SequentialCommandGroup(
            KidShampooCmds.cmds.DumpWrist(r.kidShampooSubsystem),
            KidShampooCmds.cmds.OpenRetainer(r.kidShampooSubsystem)
        );
    }

    public static SequentialCommandGroup HighBasket(Robot r) {
        return new SequentialCommandGroup(
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem),
            KidShampooCmds.cmds.StopIntake(r.kidShampooSubsystem),
            KidShampooCmds.cmds.ScoopWrist(r.kidShampooSubsystem),
            ArmSubCmds.cmds.slideZero(r.armSubsystem),
            ArmSubCmds.cmds.highbasketArm(r.armSubsystem),
            new WaitCommand(1),
            ArmSubCmds.cmds.highbasketSlide(r.armSubsystem),
            new WaitCommand(1.2),
            HighBasketPreArm(r)
        );
    }
}
