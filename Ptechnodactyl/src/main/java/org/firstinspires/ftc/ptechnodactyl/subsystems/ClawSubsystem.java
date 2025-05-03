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

    private Servo clawServo;
    private EncodedMotor<DcMotorEx> arm;
    private boolean isHardware;

    @Log(name = "clawPosition")
    public double clawPosition = 0;

    public static double CLAW_OPEN_POSITION = 0.3;
    public static double CLAW_CLOSE_POSITION = 0.7;
    public static int INCREMENT_DECREMENT = 200;
    public static double FEEDFORWARD_COEFFICIENT = 0.00014;
    public static int ARM_VERTICAL = 3100;
    public static int ARM_HORIZONTAL = 1000;
    public static int INITIAL_POSITION = 150;
    public static int ARM_MAX = 0;
    public static int ARM_MIN = 0;

    @Log(name = "armTarget")
    public int armTargetPos;

    @Log(name = "armPos")
    public int armPos;

    @Log(name = "armFdFwdVal")
    public double armFeedFwdValue;

    private PIDFController armPidController;

    private void setArmPos(int e) {
        armPidController.setTargetPosition(e);
        armTargetPos = e;
    }

    public static PIDCoefficients armPID = new PIDCoefficients(0.0005, 0.0, 0.0001);

    private void setClawPosition(double d) {
        if (isHardware) {
            clawServo.setPosition(d);
            clawPosition = d;
        }
    }

    public ClawSubsystem(Hardware hw) {
        isHardware = true;
        clawServo = hw.clawServo;
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
                if (Math.abs(armFeedFwdValue) < 0.1) {
                    armFeedFwdValue = 0.0;
                }

                return armFeedFwdValue;
            }
        );
        setArmPos(INITIAL_POSITION);
    }

    public void increment(double value) {
        int newArmPos = (int) (armTargetPos + value * INCREMENT_DECREMENT);
        //        if (newArmPos > ARM_MAX) {
        //            newArmPos = 3150;
        //        } else if (newArmPos < ARM_MIN) {
        //            newArmPos = 0;
        //        }
        setArmPos(newArmPos);
    }

    public void incrementn() {
        increment(1.0);
    }

    public void decrement() {
        increment(-1.0);
    }

    private static double getArmAngle(double ticks) {
        // our horizontal value starts at ARM_HORIZONTAL, so we need to
        // subtract it
        return ((Math.PI / 2.0) * (ticks - ARM_HORIZONTAL)) / (ARM_VERTICAL - ARM_HORIZONTAL);
    }

    public void openClaw() {
        setClawPosition(CLAW_OPEN_POSITION);
    }

    public void closeClaw() {
        setClawPosition(CLAW_CLOSE_POSITION);
    }
}
