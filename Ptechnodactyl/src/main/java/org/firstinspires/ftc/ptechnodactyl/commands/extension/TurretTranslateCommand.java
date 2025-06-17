package org.firstinspires.ftc.ptechnodactyl.commands.extension;

import java.util.function.BooleanSupplier;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;

public class TurretTranslateCommand extends ExtensionCommand {

    public BooleanSupplier flipTranslate;

    public TurretTranslateCommand(
        ExtensionSubsystem subsystem,
        double turret,
        BooleanSupplier flip
    ) {
        super(subsystem, 0, turret);
        flipTranslate = flip;
    }

    @Override
    public void initialize() {
        extensionSubsystem.translateTurret(
            flipTranslate.getAsBoolean() ? -turretTarget : turretTarget
        );
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return getRuntime().seconds() > 0.01;
    }
}
