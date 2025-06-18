package org.firstinspires.ftc.ptechnodactyl.commands.arm;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.*;

public abstract class ArmCommand implements Command {

    public ArmSubsystem subsystem;

    public ArmCommand(ArmSubsystem arm) {
        subsystem = arm;
        addRequirements(arm);
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.5;
    }
}
