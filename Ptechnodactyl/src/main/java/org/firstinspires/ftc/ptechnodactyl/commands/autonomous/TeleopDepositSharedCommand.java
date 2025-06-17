package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.RegenerativeTrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.RobotState;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositSharedCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class TeleopDepositSharedCommand extends SequentialCommandGroup {

    public DrivebaseSubsystem drivebaseSubsystem;

    public TeleopDepositSharedCommand(
        DrivebaseSubsystem drive,
        IntakeSubsystem intake,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension
    ) {
        super(
            new WaitCommand(0.3),
            drive::relocalizeUnsafe,
            new RegenerativeTrajectorySequenceCommand(
                drive,
                RobotConstants.WAREHOUSE_TO_SHARED_HUB,
                drive
            ).alongWith(
                new IntakeOutCommand(intake).withTimeout(0.5),
                //new WaitCommand(0.3).andThen(new DriveRelocalizeSharedCommand(drive)),
                new WaitCommand(0.1).andThen(new DepositSharedCommand(deposit, extension, lift))
            ),
            new WaitCommand(0.1),
            new BucketDumpCommand(deposit)
        );
        drivebaseSubsystem = drive;
    }

    @Override
    public void initialize() {
        super.initialize();
        RobotState.startDeposit();
    }

    @Override
    public void end(boolean cancel) {
        RobotState.stopDeposit();
        super.end(cancel);
    }
}
