package org.firstinspires.ftc.twenty403.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.controls.SingleController;
import org.firstinspires.ftc.twenty403.helpers.StartingPosition;
import org.firstinspires.ftc.twenty403.subsystems.MiniOdopodLocalizer;

@TeleOp(name = "OTOSTest")
public class OTOSTestOpmode extends LinearOpMode {

    public Robot robot;
    public Setup setup;
    public SingleController controls;
    public Hardware hardware;


    @Override
    public void runOpMode() throws InterruptedException {
        SparkFunOTOS sparky = hardwareMap.get(SparkFunOTOS.class, "otos");
        MiniOdopodLocalizer local = new MiniOdopodLocalizer(sparky);

        waitForStart();

        while (opModeIsActive()) {

            Pose2d pos = local.getPoseEstimate();
            telemetry.addData("X", pos.getX());
            telemetry.addData("Y", pos.getY());
            telemetry.addData("H", pos.getHeading());

            telemetry.update();
        }
    }
}
