package org.firstinspires.ftc.sixteen750.opmodes.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.commands.auto.Paths;
import org.firstinspires.ftc.sixteen750.helpers.StartingPosition;

@Autonomous(name = "AscentOnly")
@SuppressWarnings("unused")
public class AscentOnly extends CommandOpMode {

    public Robot robot;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.Net);
        robot.drivebase.setPoseEstimate(AutoConstants.NET_START.toPose());
        CommandScheduler.register(robot.verticalSlidesSubsystem);
        CommandScheduler.scheduleForState(
            new SequentialCommandGroup(Paths.AscentOnly(robot), CommandScheduler::terminateOpMode),
            OpModeState.RUN
        );
    }

    public void uponStart() {
        robot.prepForStart();
    }
}
