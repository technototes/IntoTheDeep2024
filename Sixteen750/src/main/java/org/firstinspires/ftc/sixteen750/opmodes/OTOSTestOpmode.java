package org.firstinspires.ftc.sixteen750.opmodes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.controls.SingleController;
import org.firstinspires.ftc.sixteen750.subsystems.MiniOdopodLocalizer;

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
