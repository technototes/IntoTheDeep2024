//package org.firstinspires.ftc.twenty403.opmodes.auto;
//
//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.technototes.library.command.CommandScheduler;
//import com.technototes.library.command.SequentialCommandGroup;
//import com.technototes.library.structure.CommandOpMode;
//import com.technototes.library.util.Alliance;
//
//import org.firstinspires.ftc.sixteen750.AutoConstants;
//import org.firstinspires.ftc.sixteen750.Hardware;
//import org.firstinspires.ftc.sixteen750.Robot;
//import org.firstinspires.ftc.sixteen750.commands.auto.Paths;
//import org.firstinspires.ftc.sixteen750.controls.DriverController;
//import org.firstinspires.ftc.sixteen750.helpers.StartingPosition;
//import org.firstinspires.ftc.twenty403.AutoConstants;
//import org.firstinspires.ftc.twenty403.Hardware;
//import org.firstinspires.ftc.twenty403.Robot;
//import org.firstinspires.ftc.twenty403.commands.auto.Paths;
//import org.firstinspires.ftc.twenty403.controls.DriverController;
//import org.firstinspires.ftc.twenty403.helpers.StartingPosition;
//
//@Autonomous(name = "Obs_Parking")
//@SuppressWarnings("unused")
//public class Obs_Park extends CommandOpMode {
//
//    public Robot robot;
//    public DriverController controls;
//    public Hardware hardware;
//
//    @Override
//    public void uponInit() {
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        hardware = new Hardware(hardwareMap);
//        robot = new Robot(hardware, Alliance.RED, StartingPosition.Net.Observation);
//        robot.drivebaseSubsystem.setPoseEstimate(AutoConstants.OBS_START.toPose());
//        CommandScheduler.scheduleForState(
//            new SequentialCommandGroup(Paths.Obs_Parking(robot), CommandScheduler::terminateOpMode),
//            OpModeState.RUN
//        );
//    }
//
//    public void uponStart() {
//        robot.prepForStart();
//    }
//}
//
//
