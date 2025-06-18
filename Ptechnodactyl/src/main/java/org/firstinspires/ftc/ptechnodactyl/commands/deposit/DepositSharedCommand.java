package org.firstinspires.ftc.ptechnodactyl.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmSharedCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionSideCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class DepositSharedCommand extends ParallelCommandGroup {

    public DepositSharedCommand(
        ArmSubsystem arm,
        ExtensionSubsystem extension,
        LiftSubsystem lift
    ) {
        super(
            new LiftSharedCommand(lift),
            new ExtensionSideCommand(extension),
            new ArmSharedCommand(arm)
        );
    }
}
