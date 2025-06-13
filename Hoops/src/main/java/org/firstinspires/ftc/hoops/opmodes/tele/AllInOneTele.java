package org.firstinspires.ftc.hoops.opmodes.tele;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.general.Periodic;
import com.technototes.library.structure.CommandOpMode;
import org.firstinspires.ftc.hoops.Hardware;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.commands.EZCmd;
import org.firstinspires.ftc.hoops.controllers.allinOneController;

@TeleOp(name = "AllInOneTele")
@SuppressWarnings("unused")
public class AllInOneTele extends CommandOpMode {

    public Robot robot;
    public allinOneController Allcontrols;

    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware);
        CommandScheduler.register(robot.launcherSubsystem);
        CommandScheduler.register(robot.intakeSubsystem);
        if (Setup.Connected.DRIVEBASE) {
            Allcontrols = new allinOneController(driverGamepad, robot);

            CommandScheduler.scheduleForState(
                EZCmd.Drive.ResetGyro(robot.drivebaseSubsystem),
                OpModeState.INIT
            );
        }
    }
}
