package org.firstinspires.ftc.ptechnodactyl.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.commands.EZCmd;
import org.firstinspires.ftc.ptechnodactyl.controllers.OneController;

@TeleOp(name = "OneController")
@SuppressWarnings("unused")
public class oneController extends CommandOpMode implements Loggable {

    public Hardware hardware;
    public Robot robot;

    public OneController driver;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        driver = new OneController(driverGamepad, robot);
        CommandScheduler.register(robot.clawSubsystem);
    }
}
