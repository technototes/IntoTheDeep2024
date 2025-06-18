package org.firstinspires.ftc.ptechnodactyl.opmodes.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.geometry.ConfigurablePoseD;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.Robot;

/*
 * Some Emojis for opmode names:
 * Copy them and paste them in the 'name' section of the @Autonomous tag
 * Red alliance:  🟥
 * Blue alliance: 🟦
 * Wing position: 🪶
 * Backstage pos: 🎦
 */
//@Config
@Autonomous(name = "Basic Auto", preselectTeleOp = "PlainDrive")
@SuppressWarnings("unused")
public class Basic extends CommandOpMode implements Loggable {

    public static int AUTO_TIME = 1;
    public Hardware hardware;
    public Robot robot;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        CommandScheduler.scheduleForState(
            new SequentialCommandGroup(
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, b ->
                    b
                        .apply(new ConfigurablePoseD(0, 0, 0).toPose())
                        .lineTo(new Vector2d(5, 5))
                        .build()
                ),
                // new TurboCommand(robot.drivebaseSubsystem),
                // new StartSpinningCmd(robot.spinner),
                new WaitCommand(AUTO_TIME),
                new TrajectorySequenceCommand(robot.drivebaseSubsystem, b ->
                    b
                        .apply(new ConfigurablePoseD(5, 5, 0).toPose())
                        .lineTo(new Vector2d(0, 0))
                        .build()
                ),
                // new StopSpinningCmd(robot.spinner),
                CommandScheduler::terminateOpMode
            ),
            CommandOpMode.OpModeState.RUN
        );
    }
}
