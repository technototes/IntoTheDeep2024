package org.firstinspires.ftc.ptechnodactyl.commands.arm;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;

public class ArmAllianceCommand extends ArmCommand {

    public ArmAllianceCommand(ArmSubsystem s) {
        super(s);
    }

    @Override
    public void execute() {
        subsystem.out();
        subsystem.carry();
    }
}
