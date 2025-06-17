package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.path.command.TrajectorySequenceCommand;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.carousel.CarouselSpinCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.deposit.DepositCollectCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class AutoCarouselCommand extends SequentialCommandGroup {

    public AutoCarouselCommand(
        DrivebaseSubsystem drive,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension,
        CarouselSubsystem carousel
    ) {
        super(
            new TrajectorySequenceCommand(drive, RobotConstants.HUB_TO_CAROUSEL).alongWith(
                new DepositCollectCommand(deposit, extension, lift)
            ),
            new CarouselSpinCommand(carousel).withTimeout(4)
        );
    }
}
