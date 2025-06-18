package org.firstinspires.ftc.ptechnodactyl.commands.extension;

import static org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem.ExtensionConstants;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;

public class ExtensionRightSideCommand extends ExtensionOutCommand {

    public ExtensionRightSideCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionConstants.SHARED, ExtensionConstants.RIGHT);
    }

    public ExtensionRightSideCommand(ExtensionSubsystem subsystem, double extension) {
        super(subsystem, extension, ExtensionConstants.RIGHT);
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
