package org.firstinspires.ftc.ptechnodactyl.commands.cap;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CapSubsystem;

public class CapOpenCommand implements Command {

    public CapSubsystem subsystem;

    public CapOpenCommand(CapSubsystem cap) {
        subsystem = cap;
        addRequirements(cap);
    }

    @Override
    public void execute() {
        subsystem.open();
    }
}
