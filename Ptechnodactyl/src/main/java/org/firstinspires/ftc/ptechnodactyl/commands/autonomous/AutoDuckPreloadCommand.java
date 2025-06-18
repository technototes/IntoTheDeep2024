package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositPreloadCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem;

public class AutoDuckPreloadCommand extends SequentialCommandGroup {

    public AutoDuckPreloadCommand(
        DrivebaseSubsystem drive,
        ArmSubsystem depot,
        ExtensionSubsystem extension,
        LiftSubsystem lift,
        VisionSubsystem vision
    ) {
        super(
            new TrajectorySequenceCommand(drive, RobotConstants.DUCK_DEPOSIT_PRELOAD).alongWith(
                new DepositPreloadCommand(depot, extension, lift, vision)
            ),
            new BucketDumpCommand(depot)
        );
    }
}
