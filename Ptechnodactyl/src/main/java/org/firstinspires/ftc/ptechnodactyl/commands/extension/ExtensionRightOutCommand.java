package org.firstinspires.ftc.ptechnodactyl.commands.extension;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;

public class ExtensionRightOutCommand extends ExtensionRightSideCommand {

    public ExtensionRightOutCommand(ExtensionSubsystem subsystem) {
        super(subsystem, ExtensionSubsystem.ExtensionConstants.OUT);
    }
}
