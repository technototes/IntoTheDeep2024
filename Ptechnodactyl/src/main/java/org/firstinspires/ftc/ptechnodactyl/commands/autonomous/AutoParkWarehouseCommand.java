package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class AutoParkWarehouseCommand extends SequentialCommandGroup {

    public AutoParkWarehouseCommand(
        DrivebaseSubsystem drive,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension
    ) {
        super(
            new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_PARK).alongWith(
                new DepositCollectCommand(deposit, extension, lift)
            )
        );
    }
}
