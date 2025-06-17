package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class AutoParkSquareCommand extends SequentialCommandGroup {

    public AutoParkSquareCommand(
        DrivebaseSubsystem drive,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension
    ) {
        super(
            new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_SQUARE).alongWith(
                new DepositCollectCommand(deposit, extension, lift)
            )
        );
    }
}
