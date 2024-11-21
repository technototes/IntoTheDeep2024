package org.firstinspires.ftc.twenty403.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;

import org.firstinspires.ftc.twenty403.AutoConstants;
import org.firstinspires.ftc.twenty403.Hardware;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.controls.DriverController;
import org.firstinspires.ftc.twenty403.controls.KidShamTestController;
import org.firstinspires.ftc.twenty403.controls.OperatorController;
import org.firstinspires.ftc.twenty403.helpers.StartingPosition;

@TeleOp(name = "KidShamTest")
@SuppressWarnings("unused")
public class KidShamTest extends CommandOpMode {

    public Robot robot;
    public KidShamTestController kidShamTestController;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.BLUE, StartingPosition.Unspecified);
        kidShamTestController = new KidShamTestController(codriverGamepad, robot);
        if (Setup.Connected.DRIVEBASE) {

            CommandScheduler.scheduleForState(
                EZCmd.Drive.ResetGyro(robot.drivebaseSubsystem),
                OpModeState.INIT
            );
        }
    }
}
