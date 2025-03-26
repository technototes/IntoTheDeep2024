package org.firstinspires.ftc.hoops.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.controllers.AllinOneController;
import org.firstinspires.ftc.hoops.controllers.DriverController;
import org.firstinspires.ftc.hoops.controllers.OperatorController;

@TeleOp(name = "LaunchTest")
@SuppressWarnings("unused")
public class LaunchTest extends CommandOpMode implements Loggable {

    public Hardware hardware;
    public Robot robot;

    public AllinOneController operator;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        operator = new AllinOneController(driverGamepad, robot);
    }
}
