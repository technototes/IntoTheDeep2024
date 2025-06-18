package org.firstinspires.ftc.ptechnodactyl.commands.deposit;

import static org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem.ExtensionConstants.OUT;

import com.technototes.library.command.ParallelCommandGroup;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionLeftSideCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionRightSideCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class DepositOppositeSharedCommand extends ParallelCommandGroup {

    public DepositOppositeSharedCommand(
        ArmSubsystem arm,
        ExtensionSubsystem extension,
        LiftSubsystem lift
    ) {
        super(
            new LiftSharedCommand(lift),
            Alliance.Selector.selectOf(
                RobotConstants.getAlliance(),
                new ExtensionLeftSideCommand(extension, OUT),
                new ExtensionRightSideCommand(extension, OUT)
            ),
            new ArmAllianceCommand(arm)
        );
    }
}
