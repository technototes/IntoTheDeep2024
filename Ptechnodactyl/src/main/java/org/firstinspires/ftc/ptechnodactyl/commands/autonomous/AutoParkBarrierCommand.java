package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.ConditionalCommand;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class AutoParkBarrierCommand extends SequentialCommandGroup {

    public AutoParkBarrierCommand(
        DrivebaseSubsystem drive,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension
    ) {
        super(
            new DepositCollectCommand(deposit, extension, lift),
            new ConditionalCommand(() -> CommandScheduler.getOpModeRuntime() > 26),
            new TrajectorySequenceCommand(drive, RobotConstants.HUB_BARRIER_PARK)
        );
    }
}
