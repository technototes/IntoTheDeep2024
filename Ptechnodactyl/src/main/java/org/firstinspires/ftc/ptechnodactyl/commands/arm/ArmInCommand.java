package org.firstinspires.ftc.ptechnodactyl.commands.arm;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.*;

public class ArmInCommand extends ArmCommand {

    public ArmInCommand(ArmSubsystem s) {
        super(s);
    }

    @Override
    public void execute() {
        subsystem.in();
        subsystem.collect();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.4;
    }
}
