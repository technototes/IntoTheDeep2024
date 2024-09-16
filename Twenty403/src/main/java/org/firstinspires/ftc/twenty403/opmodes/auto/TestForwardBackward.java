package org.firstinspires.ftc.twenty403.opmodes.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.commands.driving.DriveTestCommand;
import org.firstinspires.ftc.twenty403.controls.DriverController;
import org.firstinspires.ftc.twenty403.helpers.StartingPosition;

@Autonomous(name = "Fwd/Backward")
@SuppressWarnings("unused")
public class TestForwardBackward extends CommandOpMode {

    public Robot robot;
    public DriverController controls;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.Wing);
        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.START.toPose());
        CommandScheduler.scheduleForState(
            new SequentialCommandGroup(
                new DriveTestCommand(robot.drivebaseSubsystem, 1),
                new WaitCommand(3),
                new DriveTestCommand(robot.drivebaseSubsystem, 0),
                new WaitCommand(3),
                new DriveTestCommand(robot.drivebaseSubsystem, -1),
                new WaitCommand(3),
                new DriveTestCommand(robot.drivebaseSubsystem, 0),
                EZCmd.Drive.RecordHeading(robot.drivebaseSubsystem),
                CommandScheduler::terminateOpMode
            ),
            OpModeState.RUN
        );
    }
}
