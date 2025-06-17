package org.firstinspires.ftc.ptechnodactyl.commands.extension;

import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem;

public class ExtensionBarcodeSelectCommand extends ExtensionOutCommand {

    public VisionSubsystem visionSubsystem;

    public ExtensionBarcodeSelectCommand(ExtensionSubsystem s, VisionSubsystem v) {
        super(s);
        visionSubsystem = v;
    }

    @Override
    public void execute() {
        extensionSubsystem.center();
        extensionSubsystem.setSlide(
            visionSubsystem.barcodePipeline.zero()
                ? ExtensionSubsystem.ExtensionConstants.LOW_GOAL_AUTO
                : ExtensionSubsystem.ExtensionConstants.OUT
        );
    }
}
