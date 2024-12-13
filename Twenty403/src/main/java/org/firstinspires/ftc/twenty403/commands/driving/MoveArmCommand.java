package org.firstinspires.ftc.twenty403.commands.driving;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.subsystems.ArmSubsystem;

public class MoveArmCommand implements Command {

    ArmSubsystem sub;
    Runnable cmd;

    public MoveArmCommand(ArmSubsystem arm, Runnable armCommand) {
        addRequirements(arm);
        sub = arm;
        cmd = armCommand;
    }

    @Override
    public void execute() {
        cmd.run();
    }

    @Override
    public boolean isFinished() {
        return sub.isArmAtTarget();
    }
}
