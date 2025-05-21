package org.firstinspires.ftc.ptechnodactyl.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.ptechnodactyl.Hardware;

@Config
public class ClawSubsystem implements Subsystem, Loggable {

    private Servo clamp, leftDiffy, rightDiffy;
    private EncodedMotor<DcMotorEx> arm;
    private boolean isHardware;

    @Log(name = "clampPosition")
    public double clampPosition = 0;

    @Log(name = "leftDiffyPosition")
    public double leftDiffyPosition = 0;

    @Log(name = "rightDiffyPosition")
    public double rightDiffyPosition = 0;

    public static double CLAMP_OPEN_POSITION = 0.5;
    public static double CLAMP_CLOSE_POSITION = 0.96;
    public static double CLAW_DOWN_LEFT_POSITION = 0.84;
    public static double CLAW_DOWN_RIGHT_POSITION = 0.84;
    public static double CLAW_NEUTRAL_LEFT_POSITION = 0.17;
    public static double CLAW_NEUTRAL_RIGHT_POSITION = 0.87;
    public static double CLAW_DPADL_LEFT_POSITION = 0.26;
    public static double CLAW_DPADL_RIGHT_POSITION = 0.74;
    public static double CLAW_DPADR_LEFT_POSITION = 0.74;
    public static double CLAW_DPADR_RIGHT_POSITION = 0.26;
    public static double CLAW_DPADU_LEFT_POSITION = 0.98;
    public static double CLAW_DPADU_RIGHT_POSITION = 0.02;
    public static double MIN_ARM_MOTOR_SPEED = -0.5;
    public static double MAX_ARM_MOTOR_SPEED = 0.5;
    public static int INCREMENT_DECREMENT = 30;
    public static double FEEDFORWARD_COEFFICIENT = 0;
    public static int ARM_VERTICAL = 0;
    public static int ARM_HORIZONTAL = 0;
    public static int INITIAL_POSITION = 0;
    public static int ARM_MAX = 0;
    public static int ARM_MIN = 0;

    @Log(name = "armTarget")
    public int armTargetPos;

    @Log(name = "armPos")
    public int armPos;

    @Log(name = "armPow")
    public double armPow;

    @Log(name = "armFdFwdVal")
    public double armFeedFwdValue;

    private PIDFController armPidController;

    private void setArmPos(int e) {
        armPidController.setTargetPosition(e);
        armTargetPos = e;
    }

    public static PIDCoefficients armPID = new PIDCoefficients(0, 0.0, 0);

    private void setClampPosition(double d) {
        if (isHardware) {
            clamp.setPosition(d);
            clampPosition = d;
        }
    }

    private void setLeftDiffyPos(double d) {
        if (isHardware) {
            leftDiffy.setPosition(d);
            leftDiffyPosition = d;
        }
    }

    private void setRightDiffyPos(double d) {
        if (isHardware) {
            rightDiffy.setPosition(d);
            rightDiffyPosition = d;
        }
    }

    private int getArmUnmodifiedPosition() {
        if (isHardware) {
            return (int) arm.getSensorValue();
        } else {
            return 0;
        }
    }

    public ClawSubsystem(Hardware hw) {
        isHardware = true;
        clamp = hw.clampServo;
        leftDiffy = hw.leftDiffy;
        rightDiffy = hw.rightDiffy;
        arm = hw.arm;
        armPidController = new PIDFController(
            armPID,
            0,
            0,
            0,
            /*

            The function arguments for the Feed Forward function are Position (ticks) and
            Velocity (units?). So, for the arm, we want to check to see if which side of
            center we're on, and if the velocity is pushing us down, FF should probably be
            low (negative?) while if velocity is pushing us *up*, FF should be high (right?)
            Someone who's done physics and/or calculus recently should write some real equations

            Braelyn got the math right

            For angle T through this range where we start at zero:
                       /
                      / T
            180 _____/_____ 0
            The downward torque due to gravity is cos(T) * Gravity (9.8m/s^2)

            If we're moving from 0 to 180 degrees, then:
                While T is less than 90, the "downward torque" is working *against* the motor
                When T is greater than 90, the "downward torque" is working *with* the motor

             */

            (ticks, velocity) -> {
                armFeedFwdValue = FEEDFORWARD_COEFFICIENT * Math.cos(getArmAngle(ticks));

                //                if (velocity > MIN_ANGULAR_VELOCITY) {
                //                    //increase armFeedFwdValue to avoid slamming or increase D in PID
                //                    armFeedFwdValue += ARM_SLAM_PREVENTION;
                //                }

                return armFeedFwdValue;
            }
        );
        //        setArmPos(INITIAL_POSITION);
    }

    public ClawSubsystem() {
        isHardware = false;
        clamp = null;
        leftDiffy = null;
        rightDiffy = null;
        arm = null;
    }

    public void increment(double value) {
        int newArmPos = (int) (armTargetPos + value * INCREMENT_DECREMENT);
        if (newArmPos > ARM_MAX) {
            newArmPos = ARM_MAX;
        } else if (newArmPos < ARM_MIN) {
            newArmPos = ARM_MIN;
        }
        setArmPos(newArmPos);
    }

    public void powIncrement() {
        armPow = armPow + 0.05;
        setArmMotorPower(armPow);
    }

    public void powDecrement() {
        armPow = armPow - 0.05;
        setArmMotorPower(armPow);
    }

    private static double getArmAngle(double ticks) {
        // our horizontal value starts at ARM_HORIZONTAL, so we need to
        // subtract it
        return ((Math.PI / 2.0) * (ticks - ARM_HORIZONTAL)) / (ARM_VERTICAL - ARM_HORIZONTAL);
    }

    public void openClamp() {
        setClampPosition(CLAMP_OPEN_POSITION);
    }

    public void closeClamp() {
        setClampPosition(CLAMP_CLOSE_POSITION);
    }

    public void downLeftClaw() {
        setLeftDiffyPos(CLAW_DOWN_LEFT_POSITION);
    }

    public void downRightClaw() {
        setRightDiffyPos(CLAW_DOWN_RIGHT_POSITION);
    }

    public void neutralLeftClaw() {
        setLeftDiffyPos(CLAW_NEUTRAL_LEFT_POSITION);
    }

    public void neutralRightClaw() {
        setRightDiffyPos(CLAW_NEUTRAL_RIGHT_POSITION);
    }

    public void dpadlLeftClaw() {
        setLeftDiffyPos(CLAW_DPADL_LEFT_POSITION);
    }

    public void dpadlRightClaw() {
        setRightDiffyPos(CLAW_DPADL_RIGHT_POSITION);
    }

    public void dpadrLeftClaw() {
        setLeftDiffyPos(CLAW_DPADR_LEFT_POSITION);
    }

    public void dpadrRightClaw() {
        setRightDiffyPos(CLAW_DPADR_RIGHT_POSITION);
    }

    public void dpaduLeftClaw() {
        setLeftDiffyPos(CLAW_DPADU_LEFT_POSITION);
    }

    public void dpaduRightClaw() {
        setRightDiffyPos(CLAW_DPADU_RIGHT_POSITION);
    }

    private void setArmMotorPower(double speed) {
        if (isHardware) {
            double clippedSpeed = Range.clip(speed, MIN_ARM_MOTOR_SPEED, MAX_ARM_MOTOR_SPEED);
            arm.setPower(clippedSpeed);
        }
    }

    @Override
    public void periodic() {
        armPos = getArmUnmodifiedPosition();
        armPow = armPidController.update(armPos);
        setArmMotorPower(armPow);
    }
}
