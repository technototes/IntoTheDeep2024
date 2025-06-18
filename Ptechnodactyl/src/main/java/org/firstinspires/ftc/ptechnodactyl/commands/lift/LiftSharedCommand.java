package org.firstinspires.ftc.ptechnodactyl.commands.lift;

import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class LiftSharedCommand extends LiftCommand {

    public LiftSharedCommand(LiftSubsystem l) {
        super(l, LiftSubsystem.LiftConstants.NEUTRAL);
    }
}
