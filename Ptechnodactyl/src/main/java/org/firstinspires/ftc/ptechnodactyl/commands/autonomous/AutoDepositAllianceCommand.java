package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.path.command.RegenerativeTrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.BucketDumpCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositCycleAllianceCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

@com.acmerobotics.dashboard.config.Config
public class AutoDepositAllianceCommand extends SequentialCommandGroup {

    public static double TIME = 1;

    public AutoDepositAllianceCommand(
        DrivebaseSubsystem drive,
        IntakeSubsystem intake,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension
    ) {
        super(
            drive::relocalize,
            new RegenerativeTrajectorySequenceCommand(
                drive,
                RobotConstants.WAREHOUSE_TO_HUB,
                drive
            ).alongWith(
                deposit::slightCarry,
                new WaitCommand(0.3)
                    .andThen(new IntakeOutCommand(intake))
                    .withTimeout(TIME)
                    .andThen(new DepositCycleAllianceCommand(deposit, extension, lift))
            ),
            new BucketDumpCommand(deposit)
        );
    }
}
