package org.firstinspires.ftc.ptechnodactyl.commands.lift;

import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class LiftLevel1Command extends LiftCommand {

    public LiftLevel1Command(LiftSubsystem l) {
        super(l, LiftSubsystem.LiftConstants.LEVEL_1);
    }
}
