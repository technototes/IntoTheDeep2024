package org.firstinspires.ftc.hoops.subsystems;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.ServoGroup;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.hoops.Hardware;

//@Config
public class TestSubsystem implements Subsystem, Loggable {

    public static int DISTANCE = 10; // centimeters!

    public static double STARTING_POWER = 0.2;

    public static double HIGH_TICKS = 3600;
    public static double LOW_TICKS = 0;
    public static double DEAD_POWER = 0.01;
    public static double POWER_STEP = 0.05;
    public static double LOW_POWER = -0.7;
    public static double HIGH_POWER = 0.7;
    public static double BIGADJUSTMENT = 0.001;
    public static double SMALLADJUSTMENT = 0.0001;
    public static double LOW_BASKET = -950;
    public static double HIGH_BASKET = -1350;
    //    public static double HIGH_POS = 1000;
    public static int SLIDE_ZERO = 0;
    public static double MIN_MOTOR_SPEED = -0.7; //TODO: test
    public static double MAX_MOTOR_SPEED = 1;
    // Let's say thing this spins a motor between 0 and 3600 'ticks'
    // but only while/if the the distance is greater than 10cm
    private EncodedMotor<DcMotorEx> theMotor;

    private ServoGroup servo;

    private Rev2MDistanceSensor theSensor;
    private boolean running;
    private double curPower;
    private double zeroTicks;
    private PIDFController slidePidController;
    public static PIDCoefficients PID = new PIDCoefficients(0.0, 0.0, 0.0);
    public int slideResetPos;
    private boolean isHardware;

    @Log(name = "Power")
    public volatile double power = 0.0;

    @Log(name = "Ticks")
    public volatile double ticks = 0.0;

    @Log(name = "Stop mode")
    public String stopMode = "coast";

    @Log(name = "Current Servo Position")
    public double currentServoPos;

    @Log(name = "slideTarget")
    public int slideTargetPos;

    @Log(name = "Current Motor Position")
    public double currentMotorPos;

    public TestSubsystem(Hardware hw) {
        theMotor = hw.theMotor;
        curPower = 0.0;
        zeroTicks = 0.0;
        servo = hw.servo;
        resetTicks();
        slidePidController = new PIDFController(PID, 0, 0, 0, (x, y) -> 0.1);
        isHardware = true;
    }

    public void BigExtending() {
        setServoPosition(currentServoPos - BIGADJUSTMENT);
    }

    public void SmallExtending() {
        setServoPosition(currentServoPos - SMALLADJUSTMENT);
    }

    public void BigRetracting() {
        setServoPosition(currentServoPos + BIGADJUSTMENT);
    }

    public void SmallRetracting() {
        setServoPosition(currentServoPos + SMALLADJUSTMENT);
    }

    private void setServoPosition(double pos) {
        pos = Range.clip(pos, 0.0, 1.0);
        servo.setPosition(pos);
        currentServoPos = pos;
    }

    public void servoIncrement() {
        running = true;
        setServoPosition(servo.getPosition() + 0.1);
    }

    public void servoDecrement() {
        running = true;
        setServoPosition(servo.getPosition() - 0.1);
    }

    public void servoMaxPos() {
        if (servo == null) {
            throw new RuntimeException("Null servo in TestSubsystem");
        }
        running = true;
        setServoPosition(1);
    }

    public void servoMinPos() {
        running = true;
        setServoPosition(0);
    }

    private int getMotorTargetPosition() {
        return (int) slidePidController.getTargetPosition();
    }

    private int getMotorCurrentPos() {
        //return getSlideUnmodifiedPosition() - slideResetPos;
        return (int) theMotor.getSensorValue();
    }

    private void setMotorTargetPosition(int p) {
        slidePidController.setTargetPosition(p);
    }

    public void slidesDown() {
        // lowers the bucket system
        //probably going to do the slide thing with the joysticks (negative of slidesup)
        //setMotorPosition(SLIDE_ZERO);
        slidePidController.setTargetPosition(SLIDE_ZERO);
    }

    public void slideBasketLow() {
        //takes the arm to the first level
        //setMotorPosition(LOW_BASKET);
        slidePidController.setTargetPosition(LOW_BASKET);
    }

    public void slideBasketHigh() {
        //setMotorPosition(HIGH_BASKET);
        slidePidController.setTargetPosition(HIGH_BASKET);
    }

    public void slideChamberLow() {
        //takes the arm to the first level
        slidePidController.setTargetPosition(LOW_BASKET);
    }

    public void periodic() {
        double targetSpeed = slidePidController.update(getMotorCurrentPos());
        double clippedSpeed = Range.clip(targetSpeed, MIN_MOTOR_SPEED, MAX_MOTOR_SPEED);
        setMotorSpeed(clippedSpeed);
        //        setLiftPosition_OVERRIDE(
        //                leftPidController.getTargetPosition(),
        //                rightPidController.getTargetPosition()
        //        );

    }

    public void setMotorSpeed(double speed) {
        theMotor.setSpeed(speed);
        currentMotorPos = theMotor.getSensorValue();
    }

    public void slideChamberHigh() {
        slidePidController.setTargetPosition(HIGH_BASKET);
    }

    public void servoLeft() {
        running = true;
        setPositionold(0.5);
    }

    private void setSlidePos(int e) {
        slidePidController.setTargetPosition(e);
        slideTargetPos = e;
    }

    private void setSlideMotorPower(double speed) {
        if (isHardware) {
            theMotor.setSpeed(speed);
        }
    }

    private int getSlideUnmodifiedPosition() {
        if (isHardware) {
            return (int) theMotor.getSensorValue();
        } else {
            return 0;
        }
    }

    public void setMotorPosition(double t) {
        theMotor.setPosition(t);
    }

    public void servoRight() {
        running = true;
        setPositionold(0.7);
    }

    public void forwardSpinning() {
        running = true;
        setPower(1.0);
    }

    public void backwardSpinning() {
        running = true;
        setPower(-1.0);
    }

    public void stopSpinning() {
        running = true;
        setPower(0);
    }

    public void toggleMotorStopMode() {
        if (theMotor != null) {
            if (stopMode.equals("coast")) {
                theMotor.brake();
                stopMode = "brake";
            } else {
                theMotor.coast();
                stopMode = "coast";
            }
        }
    }

    public void resetPosition() {
        resetTicks();
    }

    //    @Override
    //    public void periodic() {
    //        getTicks();
    //    }

    public void setMotorPower(double d) {
        setPower(d);
    }

    public double getMotorPosition() {
        return getTicks();
    }

    /**
     * This stuff is all for hiding the actual hardware behind fake values, so we can run
     * code even if the hardware isn't connected
     */

    private void setPower(double d) {
        if (theMotor != null) {
            theMotor.setSpeed(d);
            power = d;
        }
    }

    private double getTicks() {
        if (theMotor == null) {
            // This is a fake value: Do whatever you want to 'test' your code
            return 1500;
        }
        ticks = theMotor.get();
        // This reads the 'encoder' from the motor, but it's offset from our reset value
        return ticks - zeroTicks;
    }

    private void setPositionold(double d) {
        if (servo != null) {
            servo.setPosition(d);
        }
    }

    private void resetTicks() {
        if (theMotor == null) {
            zeroTicks = 0.0;
        } else {
            zeroTicks = theMotor.get();
        }
    }
}
