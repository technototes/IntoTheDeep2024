package org.firstinspires.ftc.ptechnodactyl.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.controllers.DriverController;
import org.firstinspires.ftc.ptechnodactyl.controllers.TestController;

@TeleOp(name = "MotorTest")
@SuppressWarnings("unused")
public class MotorTest extends CommandOpMode implements Loggable {

    public Hardware hardware;
    public Robot robot;

    public TestController test;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        test = new TestController(driverGamepad, robot);
    }
}
