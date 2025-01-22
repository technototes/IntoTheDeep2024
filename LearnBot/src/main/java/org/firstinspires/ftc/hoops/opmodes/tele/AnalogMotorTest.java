package org.firstinspires.ftc.hoops.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.OnlyMotorRobot;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.controllers.MotorController;

@TeleOp(name = "AnalogMotorTest")
@SuppressWarnings("unused")
public class AnalogMotorTest extends CommandOpMode implements Loggable {

    public Hardware hardware;
    public OnlyMotorRobot robot;
    public MotorController driver;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new OnlyMotorRobot(hardware);
        if (Setup.Connected.MOTOR) {
            driver = new MotorController(driverGamepad, robot);
        }
    }
}
