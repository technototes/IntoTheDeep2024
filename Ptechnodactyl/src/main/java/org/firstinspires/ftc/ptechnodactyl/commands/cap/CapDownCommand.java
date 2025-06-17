package org.firstinspires.ftc.ptechnodactyl.commands.cap;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CapSubsystem;

public class CapDownCommand implements Command {

    public CapSubsystem subsystem;

    public CapDownCommand(CapSubsystem cap) {
        subsystem = cap;
        addRequirements(cap);
    }

    @Override
    public void execute() {
        subsystem.down();
        subsystem.close();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.6;
    }
}
