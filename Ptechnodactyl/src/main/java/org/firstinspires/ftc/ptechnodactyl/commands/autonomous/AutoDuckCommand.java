package org.firstinspires.ftc.ptechnodactyl.commands.autonomous;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem;

public class AutoDuckCommand extends SequentialCommandGroup {

    public AutoDuckCommand(
        DrivebaseSubsystem drive,
        IntakeSubsystem intake,
        LiftSubsystem lift,
        ArmSubsystem deposit,
        ExtensionSubsystem extension,
        VisionSubsystem vision,
        CarouselSubsystem carousel
    ) {
        super(
            () -> drive.setPoseEstimate(RobotConstants.DUCK_START_SELECT.get()),
            () ->
                drive.distanceSensorLocalizer.setGyroOffset(
                    -RobotConstants.DUCK_START_SELECT.get().getHeading()
                ),
            //drive::relocalize,
            new AutoDuckPreloadCommand(drive, deposit, extension, lift, vision),
            new AutoCarouselCommand(drive, lift, deposit, extension, carousel),
            new AutoIntakeDuckCommand(drive, intake),
            new AutoDepositDuckCommand(drive, deposit, extension, lift, intake),
            new AutoParkBarrierCommand(drive, lift, deposit, extension),
            CommandScheduler::terminateOpMode
        );
    }
}
