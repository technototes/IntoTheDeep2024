package org.firstinspires.ftc.hoops.imutesting;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.logger.Loggable;
import com.technototes.library.structure.CommandOpMode;

@TeleOp(name = "ImuOpmode")
public class ImuOpMode extends CommandOpMode implements Loggable {

    public ImuOnlyRobot robot;
    public ImuOnlyHardware hardware;

    public Command trigTest;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new ImuOnlyHardware(hardwareMap);
        robot = new ImuOnlyRobot(hardware);
        CommandScheduler.scheduleJoystick(new ImuCommand(robot.subsys));
        //        trigTest = new TriggerTestCommand(
        //            driverGamepad.leftTrigger,
        //            driverGamepad.leftTrigger.getAsButton(.5)
        //        );
        //        CommandScheduler.scheduleJoystick(trigTest);
    }
}
