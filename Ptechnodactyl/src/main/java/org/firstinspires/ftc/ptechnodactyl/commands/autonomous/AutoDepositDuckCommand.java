package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositAllianceCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class AutoDepositDuckCommand extends SequentialCommandGroup {

    public AutoDepositDuckCommand(
        DrivebaseSubsystem drive,
        ArmSubsystem depot,
        ExtensionSubsystem extension,
        LiftSubsystem lift,
        IntakeSubsystem intake
    ) {
        super(
            new TrajectorySequenceCommand(drive, RobotConstants.DUCK_INTAKE_TO_HUB).alongWith(
                new WaitCommand(0.3).andThen(new IntakeOutCommand(intake)).withTimeout(0.6),
                depot::slightCarry,
                new WaitCommand(0.3).andThen(new DepositAllianceCommand(depot, extension, lift))
            ),
            new BucketDumpCommand(depot).sleep(0.3)
        );
    }
}
