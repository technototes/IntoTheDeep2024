package org.firstinspires.ftc.ptechnodactyl.commands.extension;

import static org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem.ExtensionConstants;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;

public class ExtensionLeftSideCommand extends ExtensionOutCommand {

    public ExtensionLeftSideCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.SHARED, ExtensionConstants.LEFT);
    }

    public ExtensionLeftSideCommand(ExtensionSubsystem subsystem, double extension) {
        super(subsystem, extension, ExtensionConstants.LEFT);
    }

    @Override
    public void execute() {
        if (getRuntime().seconds() < 0.7) extensionSubsystem.fullyOut();
        else {
            extensionSubsystem.setSlide(slideTarget);
            extensionSubsystem.setTurret(turretTarget);
        }
    }
}
