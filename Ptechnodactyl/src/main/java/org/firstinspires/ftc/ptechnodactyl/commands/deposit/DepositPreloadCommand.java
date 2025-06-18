package org.firstinspires.ftc.ptechnodactyl.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmBarcodeSelectCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionBarcodeSelectCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftBarcodeSelectCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem;

public class DepositPreloadCommand extends ParallelCommandGroup {

    public DepositPreloadCommand(
        ArmSubsystem arm,
        ExtensionSubsystem extension,
        LiftSubsystem lift,
        VisionSubsystem vision
    ) {
        super(
            new LiftBarcodeSelectCommand(lift, vision).withTimeout(1),
            new ArmBarcodeSelectCommand(arm, vision),
            new ExtensionBarcodeSelectCommand(extension, vision)
        );
    }
}
