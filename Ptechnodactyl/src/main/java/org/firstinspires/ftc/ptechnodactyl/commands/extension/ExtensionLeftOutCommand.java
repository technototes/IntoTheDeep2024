package org.firstinspires.ftc.ptechnodactyl.commands.extension;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;

public class ExtensionLeftOutCommand extends ExtensionLeftSideCommand {

    public ExtensionLeftOutCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionSubsystem.ExtensionConstants.OUT);
    }
}
