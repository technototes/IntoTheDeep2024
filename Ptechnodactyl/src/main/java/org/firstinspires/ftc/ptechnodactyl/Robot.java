package org.firstinspires.ftc.ptechnodactyl;

import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.*;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Color;
import org.firstinspires.ftc.ptechnodactyl.opmodes.tele.TeleOpBase;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.BrakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CapSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CarouselSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem;

public class Robot implements Loggable {

    @Config
    public static class SubsystemConstants {

        public static boolean LIFT_ENABLED = true;
        public static boolean ARM_ENABLED = true;
        public static boolean EXTENSION_ENABLED = true;
        public static boolean DRIVE_ENABLED = true;
        public static boolean CAROUSEL_ENABLED = true;
        public static boolean INTAKE_ENABLED = true;
        public static boolean VISION_ENABLED = true;
        public static boolean CAP_ENABLED = true;
        public static boolean SPEAKER_ENABLED = true;
        public static boolean BRAKE_ENABLED = true;
    }

    @Log.Number(name = "Lift")
    public LiftSubsystem liftSubsystem;

    @Log(name = "Deposit")
    public ArmSubsystem armSubsystem;

    @Log(name = "Extension")
    public ExtensionSubsystem extensionSubsystem;

    @LogConfig.DenyList(TeleOpBase.class)
    @Log(name = "Drivebase")
    public DrivebaseSubsystem drivebaseSubsystem;

    @Log.Number(name = "Carousel")
    public CarouselSubsystem carouselSubsystem;

    @Log(name = "Intake")
    public IntakeSubsystem intakeSubsystem;

    @Log(name = "Cap")
    public CapSubsystem capSubsystem;

    public VisionSubsystem visionSubsystem;

    @Log.Boolean(name = "Brake", trueValue = "ACTIVE", falseValue = "INACTIVE", index = 0)
    public BrakeSubsystem brakeSubsystem;

    public Robot(Hardware hardware) {
        if (BRAKE_ENABLED) brakeSubsystem = new BrakeSubsystem(hardware.brake);

        if (LIFT_ENABLED) liftSubsystem = new LiftSubsystem(hardware.liftMotor);

        if (ARM_ENABLED) armSubsystem = new ArmSubsystem(hardware.dumpServo, hardware.armServo);

        if (EXTENSION_ENABLED) extensionSubsystem = new ExtensionSubsystem(
            hardware.slideServo,
            hardware.turretServo
        );

        if (DRIVE_ENABLED) drivebaseSubsystem = new DrivebaseSubsystem(
            hardware.flDriveMotor,
            hardware.frDriveMotor,
            hardware.rlDriveMotor,
            hardware.rrDriveMotor,
            hardware.imu,
            hardware.leftRangeSensor,
            hardware.rightRangeSensor,
            hardware.frontRangeSensor
        );

        if (CAROUSEL_ENABLED) carouselSubsystem = new CarouselSubsystem(hardware.carouselMotor);

        if (INTAKE_ENABLED) intakeSubsystem = new IntakeSubsystem(
            hardware.intakeMotor,
            hardware.intakeSensor
        );

        if (VISION_ENABLED) visionSubsystem = new VisionSubsystem(hardware.camera);

        if (CAP_ENABLED) capSubsystem = new CapSubsystem(
            hardware.capArmServos,
            hardware.capClawServo,
            hardware.capTurretServo
        );
    }
}
