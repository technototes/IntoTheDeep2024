package org.firstinspires.ftc.sixteen750.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.sixteen750.AutoConstants;
import org.firstinspires.ftc.sixteen750.Hardware;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.driving.DrivingCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesSequentials;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;
import org.firstinspires.ftc.sixteen750.controls.DriverController;
import org.firstinspires.ftc.sixteen750.controls.OperatorController;
import org.firstinspires.ftc.sixteen750.controls.OperatorControllerChoiceTest;
import org.firstinspires.ftc.sixteen750.helpers.StartingPosition;

@TeleOp(name = "ChoiceTest")
@SuppressWarnings("unused")
public class ChoiceTeleOp extends CommandOpMode {

    public Robot robot;
    public OperatorControllerChoiceTest controlsOperator;
    public DriverController controlsDriver;
    public Hardware hardware;

    @Override
    public void uponInit() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        hardware = new Hardware(hardwareMap);
        robot = new Robot(hardware, Alliance.NONE, StartingPosition.Unspecified);
        controlsOperator = new OperatorControllerChoiceTest(codriverGamepad, robot);
        if (Setup.Connected.DRIVEBASE) {
            controlsDriver = new DriverController(driverGamepad, robot);
            // Just pick a starting point
            robot.drivebase.setPoseEstimate(AutoConstants.NET_START.toPose());
            CommandScheduler.scheduleForState(
                DrivingCommands.ResetGyro(robot.drivebase),
                OpModeState.INIT
            );
            //            CommandScheduler.scheduleOnceForState(
            //                    new HorizontalSlideNeutralCommand(robot.horizontalSlidesSubsystem),
            //                    OpModeState.RUN
            //            );
        }
    }

    @Override
    public void uponStart() {
        robot.prepForStart();
    }
}
