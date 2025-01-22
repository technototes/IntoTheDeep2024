package org.firstinspires.ftc.hoops.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.Command;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.controllers.TestController;

@TeleOp(name = "Test")
@SuppressWarnings("unused")
public class TestOpmode extends CommandOpMode implements Loggable {

    public Robot robot;
    public TestController testCtrl;
    public Hardware hardware;

    public Command trigTest;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        testCtrl = new TestController(driverGamepad, robot);
        //        trigTest = new TriggerTestCommand(
        //            driverGamepad.leftTrigger,
        //            driverGamepad.leftTrigger.getAsButton(.5)
        //        );
        //        CommandScheduler.scheduleJoystick(trigTest);
    }
}
