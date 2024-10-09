package org.firstinspires.ftc.twenty403.opmodes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.helpers.StartingPosition;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Autonomous(group = "drive")
public class SplineTest extends LinearOpMode {

    Robot robot;
    Hardware hardware;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.RED, StartingPosition.Wing);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = robot.drivebaseSubsystem
            .trajectoryBuilder(new Pose2d())
            .splineTo(new Vector2d(30, 30), 0)
            .build();

        robot.drivebaseSubsystem.followTrajectory(traj);

        sleep(2000);

        robot.drivebaseSubsystem.followTrajectory(
            robot.drivebaseSubsystem
                .trajectoryBuilder(traj.end(), true)
                .splineTo(new Vector2d(0, 0), Math.toRadians(180))
                .build()
        );
    }
}
