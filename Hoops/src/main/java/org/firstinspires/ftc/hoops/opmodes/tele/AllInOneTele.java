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

@TeleOp(name = "AllInOneTele")
@SuppressWarnings("unused")
public class AllInOneTele extends CommandOpMode {

    public Robot robot;
    public org.firstinspires.ftc.hoops.controllers.AllinOneController Allcontrols;

    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        if (Setup.Connected.DRIVEBASE) {
            Allcontrols = new org.firstinspires.ftc.hoops.controllers.AllinOneController(
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
