package org.firstinspires.ftc.ptechnodactyl.commands.lift;

import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class LiftCollectCommand extends LiftCommand {

    public LiftCollectCommand(LiftSubsystem l) {
        super(l, LiftSubsystem.LiftConstants.COLLECT);
    }
}
