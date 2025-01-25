package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;

import org.firstinspires.ftc.twenty403.Robot;

public class AutoHighBasketCommand {



    public static SequentialCommandGroup HighBasket(Robot r) {
        return new SequentialCommandGroup(
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem),
            KidShampooCmds.cmds.StopIntake(r.kidShampooSubsystem),
            KidShampooCmds.cmds.ScoopWristAutoOnly(r.kidShampooSubsystem),
            ArmSubCmds.cmds.slideZero(r.armSubsystem),
            ArmSubCmds.cmds.highbasketArmAuto(r.armSubsystem),
            new WaitCommand(1),
            ArmSubCmds.cmds.highbasketSlide(r.armSubsystem),
            new WaitCommand(0.8),
            KidShampooCmds.cmds.UpRetainer(r.kidShampooSubsystem),
            new WaitCommand(0.4),
            KidShampooCmds.cmds.DumpWrist(r.kidShampooSubsystem),
            new WaitCommand(2.3),
            KidShampooCmds.cmds.ScoopWristAutoOnly(r.kidShampooSubsystem),
            new WaitCommand(0.2),
            KidShampooCmds.cmds.CloseRetainer(r.kidShampooSubsystem)

        );
    }
}
