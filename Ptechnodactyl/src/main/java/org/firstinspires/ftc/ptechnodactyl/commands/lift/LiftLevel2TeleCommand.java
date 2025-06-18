package org.firstinspires.ftc.ptechnodactyl.commands.lift;

import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class LiftLevel2TeleCommand extends LiftCommand {

    public LiftLevel2TeleCommand(LiftSubsystem l) {
        super(l, LiftSubsystem.LiftConstants.TELEOP_LEVEL_2);
    }
}
