package org.firstinspires.ftc.ptechnodactyl.commands.arm;

import org.firstinspires.ftc.ptechnodactyl.subsystems.*;

public class ArmSharedCommand extends ArmCommand {

    public ArmSharedCommand(ArmSubsystem s) {
        super(s);
    }

    @Override
    public void execute() {
        subsystem.down();
        subsystem.fakeCarry();
    }

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.5;
    }

    @Override
    public void end(boolean cancel) {
        if (!cancel) subsystem.carry();
    }
}
