package org.firstinspires.ftc.ptechnodactyl.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.commands.autonomous.AutoDuckCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.vision.VisionBarcodeCommand;

public abstract class AutoDuckBase extends CommandOpMode implements Loggable {

    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        RobotConstants.updateAlliance(Alliance.get(getClass()));
        hardware = new Hardware();
        robot = new Robot(hardware);
        CommandScheduler.scheduleInit(new VisionBarcodeCommand(robot.visionSubsystem));
        CommandScheduler.scheduleOnceForState(
            new AutoDuckCommand(
                robot.drivebaseSubsystem,
                robot.intakeSubsystem,
                robot.liftSubsystem,
                robot.armSubsystem,
                robot.extensionSubsystem,
                robot.visionSubsystem,
                robot.carouselSubsystem
            ),
            OpModeState.RUN
        );
    }

    @Override
    public void uponStart() {
        if (Robot.SubsystemConstants.CAP_ENABLED) robot.capSubsystem.up();
    }

    @Override
    public void end() {}

    @Autonomous(name = "\uD83D\uDD35 \uD83E\uDD86 Blue Duck")
    @Alliance.Blue
    public static class DuckBlueAuto extends AutoDuckBase {}

    @Autonomous(name = "\uD83D\uDFE5 \uD83E\uDD86 Red Duck")
    @Alliance.Red
    public static class DuckRedAuto extends AutoDuckBase {}
}
