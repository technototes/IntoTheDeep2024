package org.firstinspires.ftc.ptechnodactyl.subsystems;

import static org.firstinspires.ftc.ptechnodactyl.Hardware.imu;
import static org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem.DriveConstants.TIP_AUTHORITY;
import static org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem.DriveConstants.TIP_TOLERANCE;

import android.util.Pair;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.MapUtils;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.subsystem.DistanceSensorLocalizer;
import com.technototes.path.subsystem.MecanumConstants;
import com.technototes.path.subsystem.PathingMecanumDrivebaseSubsystem;
import java.util.function.Supplier;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.RobotConstants;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.helpers.HeadingHelper;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class DrivebaseSubsystem
    extends PathingMecanumDrivebaseSubsystem
    implements Supplier<Pose2d>, Loggable {

    @Override
    public Pose2d get() {
        return getPoseEstimate();
    }

    @Config
    public abstract static class DriveConstants implements MecanumConstants {

        public static double SLOW_MOTOR_SPEED = 0.6;
        public static double FAST_MOTOR_SPEED = 1.0;
        public static double AUTO_MOTOR_SPEED = 0.9;

        @TicksPerRev
        public static final double TICKS_PER_REV = 28; // From GoBilda's website

        @MaxRPM
        public static final double MAX_RPM = 6000;

        public static double MAX_TICKS_PER_SEC = (TICKS_PER_REV * MAX_RPM) / 60.0;

        @UseDriveEncoder
        public static final boolean RUN_USING_ENCODER = true;

        @MotorVeloPID
        public static PIDFCoefficients MOTOR_VELO_PID = new PIDFCoefficients(
            25,
            0,
            4,
            MecanumConstants.getMotorVelocityF((MAX_RPM / 60) * TICKS_PER_REV)
        );

        @WheelRadius
        public static double WHEEL_RADIUS = 1.88976; // inches "roughly" lol

        @GearRatio
        public static double GEAR_RATIO = 1 / 13.7; // output (wheel) speed / input (motor) speed

        @TrackWidth
        public static double TRACK_WIDTH = 9; // inches

        @WheelBase
        public static double WHEEL_BASE = 8.5; // inches

        @KV
        public static double kV =
            1.0 / MecanumConstants.rpmToVelocity(MAX_RPM, WHEEL_RADIUS, GEAR_RATIO);

        @KA
        public static double kA = 0;

        @KStatic
        public static double kStatic = 0;

        // This was 60, which was too fast. Things slid around a lot.
        @MaxVelo
        public static double MAX_VEL = 60;

        // This was 35, which also felt a bit too fast. The bot controls more smoothly now
        @MaxAccel
        public static double MAX_ACCEL = 50;

        // This was 180 degrees
        @MaxAngleVelo
        public static double MAX_ANG_VEL = Math.toRadians(180);

        // This was 90 degrees
        @MaxAngleAccel
        public static double MAX_ANG_ACCEL = Math.toRadians(90);

        @TransPID
        public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(8, 0, 0);

        @HeadPID
        public static PIDCoefficients HEADING_PID = new PIDCoefficients(4, 0, 0);

        @LateralMult
        public static double LATERAL_MULTIPLIER = 1.0; // For Mecanum, this was by 1.14 (14% off)

        @VXWeight
        public static double VX_WEIGHT = 1;

        @VYWeight
        public static double VY_WEIGHT = 1;

        @OmegaWeight
        public static double OMEGA_WEIGHT = 1;

        @PoseLimit
        public static int POSE_HISTORY_LIMIT = 100;

        public static double TIP_TOLERANCE = Math.toRadians(10);

        public static double TIP_AUTHORITY = 0.9;

        public static ConfigurablePose LEFT_SENSOR_POSE = new ConfigurablePose(
            0.5,
            -5.5,
            Math.toRadians(-90)
        );
        public static ConfigurablePose RIGHT_SENSOR_POSE = new ConfigurablePose(
            0.5,
            5.5,
            Math.toRadians(90)
        );
        public static ConfigurablePose FRONT_SENSOR_POSE = new ConfigurablePose(
            -5.7,
            -2.5,
            Math.toRadians(180)
        );

        // Helps deal with tired motors
        public static double AFR_SCALE = 0.9;
        public static double AFL_SCALE = 0.9;
        public static double ARR_SCALE = 0.9;
        public static double ARL_SCALE = 0.9;
    }

    private static final boolean ENABLE_POSE_DIAGNOSTICS = true;
    public Rev2MDistanceSensor left, right, front;
    //    protected FtcDashboard dashboard;

    public float xOffset, yOffset;

    public DistanceSensorLocalizer distanceSensorLocalizer;

    @Log(name = "Pose2d: ")
    public String poseDisplay = ENABLE_POSE_DIAGNOSTICS ? "" : null;

    //    @Log.Number(name = "FL")
    public EncodedMotor<DcMotorEx> fl2;

    //    @Log.Number(name = "FR")
    public EncodedMotor<DcMotorEx> fr2;

    //    @Log.Number(name = "RL")
    public EncodedMotor<DcMotorEx> rl2;

    //    @Log.Number(name = "RR")
    public EncodedMotor<DcMotorEx> rr2;

    //    @Log(name = "Turbo")
    public boolean Turbo = false;

    //    @Log(name = "Snail")
    public boolean Snail = false;

    @Log.Number(name = "cur heading")
    public double curHeading;

    public DrivebaseSubsystem(
        EncodedMotor<DcMotorEx> flMotor,
        EncodedMotor<DcMotorEx> frMotor,
        EncodedMotor<DcMotorEx> rlMotor,
        EncodedMotor<DcMotorEx> rrMotor,
        IMU imu,
        Rev2MDistanceSensor l,
        Rev2MDistanceSensor r,
        Rev2MDistanceSensor f
    ) {
        super(flMotor, frMotor, rlMotor, rrMotor, imu, () -> DriveConstants.class);
        fl2 = flMotor;
        fr2 = frMotor;
        rl2 = rlMotor;
        rr2 = rrMotor;
        curHeading = imu.getHeading();
        Setup.HardwareNames.COLOR = imu.getClass().toString();
        distanceSensorLocalizer = new DistanceSensorLocalizer(
            imu,
            MapUtils.of(
                new Pair<>(l, DriveConstants.LEFT_SENSOR_POSE.toPose()),
                new Pair<>(r, DriveConstants.RIGHT_SENSOR_POSE.toPose()),
                new Pair<>(f, DriveConstants.FRONT_SENSOR_POSE.toPose())
            )
        );
        left = l;
        right = r;
        front = f;

        resetGyro();
    }

    @Override
    public void periodic() {
        if (ENABLE_POSE_DIAGNOSTICS) {
            updatePoseEstimate();
            Pose2d pose = getPoseEstimate();
            Pose2d poseVelocity = getPoseVelocity();
            poseDisplay =
                pose.toString() +
                " : " +
                (poseVelocity != null ? poseVelocity.toString() : "nullv");
            curHeading = this.gyro.getHeading();
            Setup.HardwareNames.FLYWHEELMOTOR = this.gyro.getClass().toString();
        }
    }

    public void fast() {
        speed = DriveConstants.FAST_MOTOR_SPEED;
    }

    public void slow() {
        speed = DriveConstants.SLOW_MOTOR_SPEED;
    }

    public DrivebaseSubsystem(Hardware hardware) {
        this(
            hardware.flDriveMotor,
            hardware.frDriveMotor,
            hardware.rlDriveMotor,
            hardware.rrDriveMotor,
            imu,
            hardware.leftRangeSensor,
            hardware.rightRangeSensor,
            hardware.frontRangeSensor
        );
    }

    public void relocalize() {
        distanceSensorLocalizer.update(getPoseEstimate());
        setPoseEstimate(distanceSensorLocalizer.getPoseEstimate());
    }

    public void relocalizeUnsafe() {
        distanceSensorLocalizer.update();
        setPoseEstimate(distanceSensorLocalizer.getPoseEstimate());
    }

    public void resetGyro() {
        Orientation i = imu.getAngularOrientation();
        xOffset = i.secondAngle;
        yOffset = i.thirdAngle;
        distanceSensorLocalizer.setGyroOffset(
            i.firstAngle - Math.toRadians(RobotConstants.getAlliance().selectOf(-90, 90))
        );
    }

    public void setSafeDrivePower(Pose2d raw) {
        Orientation i = imu.getAngularOrientation();
        float x = 0, y = 0, adjX = xOffset - i.secondAngle, adjY = i.thirdAngle - yOffset;
        if (Math.abs(adjY) > TIP_TOLERANCE) y = adjY;
        if (Math.abs(adjX) > TIP_TOLERANCE) x = adjX;
        //        setWeightedDrivePower(raw.plus(new Pose2d(x>0 ? Math.max(x-TIP_TOLERANCE, 0) : Math.min(x+TIP_TOLERANCE, 0), y>0 ? Math.max(y-TIP_TOLERANCE, 0) : Math.min(y+TIP_TOLERANCE, 0), 0)));
        setWeightedDrivePower(
            raw.plus(
                new Pose2d(
                    Range.clip(x * 2, -TIP_AUTHORITY, TIP_AUTHORITY),
                    Range.clip(y * 2, -TIP_AUTHORITY, TIP_AUTHORITY),
                    0
                )
            )
        );
    }

    public void auto() {
        speed = DriveConstants.AUTO_MOTOR_SPEED;
    }

    public void setSnailMode() {
        Snail = true;
        Turbo = false;
    }

    public void saveHeading() {
        HeadingHelper.saveHeading(get().getX(), get().getY(), gyro.getHeading());
    }

    public void setTurboMode() {
        Turbo = true;
        Snail = false;
    }

    public void setNormalMode() {
        Snail = false;
        Turbo = false;
    }

    @Override
    public void setMotorPowers(double lfv, double lrv, double rrv, double rfv) {
        // TODO: Use the stick position to determine how to scale these values
        // in Turbo mode (If the robot is driving in a straight line, the values are
        // going to max out at sqrt(2)/2, rather than: We can go faster, but we don't
        // *always* want to scale faster, only when we're it turbo mode, and when one (or more)
        // of the control sticks are at their limit
        double maxlv = Math.max(Math.abs(lfv), Math.abs(lrv));
        double maxrv = Math.max(Math.abs(rfv), Math.abs(rrv));
        double maxall = Math.max(maxlv, maxrv);
        if (Snail == true) {
            maxall = 1.0 / DriveConstants.SLOW_MOTOR_SPEED;
        } else if (Turbo == false) {
            maxall = 1.0 / DriveConstants.AUTO_MOTOR_SPEED;
        }
        leftFront.setVelocity(
            (lfv * DriveConstants.MAX_TICKS_PER_SEC * DriveConstants.AFL_SCALE) / maxall
        );
        leftRear.setVelocity(
            (lrv * DriveConstants.MAX_TICKS_PER_SEC * DriveConstants.ARL_SCALE) / maxall
        );
        rightRear.setVelocity(
            (rrv * DriveConstants.MAX_TICKS_PER_SEC * DriveConstants.ARR_SCALE) / maxall
        );
        rightFront.setVelocity(
            (rfv * DriveConstants.MAX_TICKS_PER_SEC * DriveConstants.AFR_SCALE) / maxall
        );
    }
}
