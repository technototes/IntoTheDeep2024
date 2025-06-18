package org.firstinspires.ftc.ptechnodactyl.commands.deposit;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionOutCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftLevel3Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class DepositAllianceCommand extends ParallelCommandGroup {

    public DepositAllianceCommand(
        ArmSubsystem arm,
        ExtensionSubsystem extension,
        LiftSubsystem lift
    ) {
        super(
            new LiftLevel3Command(lift).withTimeout(1),
            new WaitCommand(0).andThen(new ExtensionOutCommand(extension)),
            new ArmAllianceCommand(arm)
        );
    }
}
