package org.firstinspires.ftc.ptechnodactyl;

import static org.firstinspires.ftc.ptechnodactyl.Hardware.HardwareConstants.*;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.IMU.AxesSigns;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo;
import com.technototes.library.hardware.servo.ServoGroup;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Webcam;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ArmSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.BrakeSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CapSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Hardware implements Loggable {

    @com.acmerobotics.dashboard.config.Config
    public static class HardwareConstants {

        public static String LIFT = "lift";

        public static String DUMP = "dump";
        public static String ARM = "arm";

        public static String SLIDE = "slide";
        public static String TURRET = "turret";

        public static String FL_MOTOR = "flmotor";
        public static String FR_MOTOR = "frmotor";
        public static String RL_MOTOR = "rlmotor";
        public static String RR_MOTOR = "rrmotor";
        public static String IMU = "imu";
        public static String L_RANGE = "ldistance";
        public static String R_RANGE = "rdistance";
        public static String F_RANGE = "fdistance";

        public static String CAROUSEL = "carousel";

        public static String CAMERA = "webcam";

        public static String INTAKE = "intake";
        public static String INTAKE_COLOR = "irange";

        public static String CAP = "cap";

        public static String BRAKE = "brake";
    }

    public EncodedMotor<DcMotorEx> liftMotor;

    public ServoGroup dumpServo;
    public ServoGroup armServo;

    public ServoGroup turretServo;
    public ServoGroup slideServo;

    public EncodedMotor<DcMotorEx> flDriveMotor;
    public EncodedMotor<DcMotorEx> frDriveMotor;
    public EncodedMotor<DcMotorEx> rlDriveMotor;
    public EncodedMotor<DcMotorEx> rrDriveMotor;
    public static IMU imu;
    public Rev2MDistanceSensor leftRangeSensor;
    public Rev2MDistanceSensor rightRangeSensor;
    public Rev2MDistanceSensor frontRangeSensor;

    public Motor<DcMotorEx> intakeMotor;
    public ColorDistanceSensor intakeSensor;

    public Motor<DcMotorEx> carouselMotor;

    public ServoGroup capServo;

    public Webcam camera;

    public ServoGroup brake;

    public ServoGroup capLeftArmServo;
    public ServoGroup capRightArmServo;
    public ServoGroup capArmServos;

    public ServoGroup capClawServo;
    public ServoGroup capTurretServo;

    public Hardware() {
        if (BRAKE_ENABLED) {
            brake = new ServoGroup(BRAKE).startAt(BrakeSubsystem.BrakeConstants.UP);
        }
        if (LIFT_ENABLED) {
            liftMotor = new EncodedMotor<DcMotorEx>(LIFT).brake().tare();
        }
        if (ARM_ENABLED) {
            dumpServo = new ServoGroup(DUMP).invert().startAt(ArmSubsystem.ArmConstants.CARRY);
            armServo = new ServoGroup(ARM).startAt(ArmSubsystem.ArmConstants.UP);
        }
        if (EXTENSION_ENABLED) {
            slideServo = new ServoGroup(SLIDE).startAt(ExtensionSubsystem.ExtensionConstants.IN);
            turretServo = new ServoGroup(TURRET)
                .startAt(ExtensionSubsystem.ExtensionConstants.CENTER)
                .expandedRange();
        }
        if (DRIVE_ENABLED) {
            flDriveMotor = new EncodedMotor<>(FL_MOTOR);
            frDriveMotor = new EncodedMotor<>(FR_MOTOR);
            rlDriveMotor = new EncodedMotor<>(RL_MOTOR);
            rrDriveMotor = new EncodedMotor<>(RR_MOTOR);
            imu = new IMU(HardwareConstants.IMU).remapAxes(AxesOrder.YXZ, AxesSigns.NPP);
            leftRangeSensor = new Rev2MDistanceSensor(L_RANGE).onUnit(DistanceUnit.INCH);
            rightRangeSensor = new Rev2MDistanceSensor(R_RANGE).onUnit(DistanceUnit.INCH);
            frontRangeSensor = new Rev2MDistanceSensor(F_RANGE).onUnit(DistanceUnit.INCH);
        }
        if (CAROUSEL_ENABLED) {
            carouselMotor = new Motor<DcMotorEx>(CAROUSEL).brake();
        }
        if (VISION_ENABLED) {
            camera = new Webcam(CAMERA);
        }
        if (INTAKE_ENABLED) {
            intakeMotor = new Motor<>(INTAKE);
            intakeSensor = new ColorDistanceSensor(INTAKE_COLOR).onUnit(DistanceUnit.INCH);
        }
        if (CAP_ENABLED) {
            capLeftArmServo = new ServoGroup("caparml").onRange(0.25, 0.65).invert();
            capRightArmServo = new ServoGroup("caparmr").onRange(0.35, 0.75);
            capArmServos = new ServoGroup(capLeftArmServo, capRightArmServo).startAt(
                CapSubsystem.CapConstants.ARM_INIT
            );

            capClawServo = new ServoGroup("capclaw").startAt(CapSubsystem.CapConstants.CLAW_CLOSE);
            capTurretServo = new ServoGroup("capturr").startAt(
                CapSubsystem.CapConstants.TURRET_INIT
            );
        }
    }
}
