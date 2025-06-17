package org.firstinspires.ftc.ptechnodactyl.commands.intake;

import org.firstinspires.ftc.ptechnodactyl.RobotState;
import org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem;

public class IntakeSafeCommand extends IntakeInCommand {

    public IntakeSafeCommand(IntakeSubsystem s) {
        super(s);
    }

    @Override
    public boolean isFinished() {
        return RobotState.hasFreight() && getRuntime().seconds() > 0.2;
    }

    @Override
    public void end(boolean cancel) {
        if (!cancel) subsystem.idle();
    }
}
