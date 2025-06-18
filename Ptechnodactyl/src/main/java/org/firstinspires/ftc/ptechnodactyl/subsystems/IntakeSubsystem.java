package org.firstinspires.ftc.ptechnodactyl.subsystems;

import static org.firstinspires.ftc.ptechnodactyl.subsystems.IntakeSubsystem.IntakeConstants.*;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Range;
import java.util.function.Supplier;
import org.firstinspires.ftc.ptechnodactyl.RobotState;

/**
 * Subsystem holding methods used for Intake commands. Intake will be responsible for bringing
 * freight in and out of our robot
 */
public class IntakeSubsystem implements Subsystem, Supplier<String> {

    @Config
    public static class IntakeConstants {

        public static double INTAKE_IN_SPEED = 1;
        public static double INTAKE_OUT_SPEED = -1;
        public static double INTAKE_STOP_SPEED = 0;
        public static double INTAKE_IDLE_SPEED = 0.5;

        public static Range CUBE_RANGE = new Range(400, 2100);
        public static Range DUCK_RANGE = new Range(400, 2100);
        public static Range BALL_RANGE = new Range(400, 2100);

        public static double SENSOR_REFRESH_RATE = 50;
    }

    public Motor<DcMotorEx> motor;

    public ColorDistanceSensor sensor;

    public IntakeSubsystem(Motor<DcMotorEx> m, ColorDistanceSensor s) {
        motor = m;
        sensor = s;
    }

    /**
     * Set the intake motor to run in at a constant speed
     */
    public void in() {
        motor.setSpeed(INTAKE_IN_SPEED);
    }

    /**
     * Set the intake motor to run out at a constant speed
     */
    public void out() {
        motor.setSpeed(INTAKE_OUT_SPEED);
    }

    /**
     * Set the intake motor to stop running
     */
    public void stop() {
        motor.setSpeed(INTAKE_STOP_SPEED);
    }

    public void idle() {
        motor.setSpeed(INTAKE_IDLE_SPEED);
    }

    private double light;

    @Override
    public String get() {
        return RobotState.getFreight().toString();
    }

    ElapsedTime t = new ElapsedTime();

    @Override
    public void periodic() {
        if (t.seconds() > 1 / SENSOR_REFRESH_RATE && motor.getSpeed() > 0) {
            t.reset();
            light = sensor.getLight();
            RobotState.setFreight(parseFreight());
        }
    }

    public RobotState.Freight parseFreight() {
        if (CUBE_RANGE.inRange(light)) return RobotState.Freight.CUBE;
        if (DUCK_RANGE.inRange(light)) return RobotState.Freight.DUCK;
        if (BALL_RANGE.inRange(light)) return RobotState.Freight.BALL;
        return RobotState.Freight.NONE;
    }
}
