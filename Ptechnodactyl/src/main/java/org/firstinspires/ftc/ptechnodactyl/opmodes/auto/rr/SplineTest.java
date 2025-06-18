package org.firstinspires.ftc.ptechnodactyl.opmodes.auto.rr;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.technototes.library.hardware.HardwareDevice;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Disabled
@Autonomous(group = "drive")
public class SplineTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HardwareDevice.hardwareMap = hardwareMap;

        DrivebaseSubsystem drive = new DrivebaseSubsystem(new Hardware());
        //        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        waitForStart();

        if (isStopRequested()) return;

        Trajectory traj = drive
            .trajectoryBuilder(new Pose2d())
            .splineTo(new Vector2d(70, 10), 0)
            .build();

        drive.followTrajectory(traj);
        sleep(2000);

        drive.followTrajectory(
            drive
                .trajectoryBuilder(traj.end(), true)
                .splineTo(new Vector2d(0, 0), Math.toRadians(180))
                .build()
        );
    }
}
