package org.firstinspires.ftc.ptechnodactyl.commands.arm;

import org.firstinspires.ftc.ptechnodactyl.subsystems.*;

public class ArmRaiseCommand extends ArmCommand {

    public ArmRaiseCommand(ArmSubsystem s) {
        super(s);
    }

    @Override
    public void execute() {
        subsystem.carry();
        subsystem.up();
    }
}
