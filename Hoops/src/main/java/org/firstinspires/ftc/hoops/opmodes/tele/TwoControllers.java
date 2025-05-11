package org.firstinspires.ftc.hoops.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;

import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.commands.EZCmd;

@TeleOp(name = "TwoControllers")
@SuppressWarnings("unused")
public class TwoControllers extends CommandOpMode {

    public Robot robot;
    public org.firstinspires.ftc.hoops.controllers.DriverController controlsDriver;
    public org.firstinspires.ftc.hoops.controllers.OperatorController controlsOperator;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        controlsOperator = new org.firstinspires.ftc.hoops.controllers.OperatorController(
            codriverGamepad,
            robot
        );
        if (Setup.Connected.DRIVEBASE) {
            controlsDriver = new org.firstinspires.ftc.hoops.controllers.DriverController(
                driverGamepad,
                robot
            );

            CommandScheduler.scheduleForState(
                EZCmd.Drive.ResetGyro(robot.drivebaseSubsystem),
                OpModeState.INIT
            );
        }
    }
}
