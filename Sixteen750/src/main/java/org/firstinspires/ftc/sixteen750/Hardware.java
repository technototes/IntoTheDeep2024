package org.firstinspires.ftc.sixteen750;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.AdafruitIMU;
import com.technototes.library.hardware.sensor.IGyro;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.encoder.MotorEncoder;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class Hardware implements Loggable {

    public List<LynxModule> hubs;

    public IGyro imu;
    public EncodedMotor<DcMotorEx> fl, fr, rl, rr;
    public EncodedMotor suspend;
    public EncodedMotor suspend2;
    public Servo clawservo;
    public Servo wristservo;
    public Servo linkservo;
    public EncodedMotor<DcMotorEx> slidemotor;
    public Servo armservo;
    public Servo bucketservo;
    public MotorEncoder odoF, odoR;

    /* Put other hardware here! */

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);
        if (Setup.Connected.EXTERNAL_IMU) {
            imu = new AdafruitIMU(Setup.HardwareNames.EXTERNAL_IMU, AdafruitIMU.Orientation.Pitch);
        } else {
            imu = new IMU(
                Setup.HardwareNames.IMU,
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
            );
        }
        if (Setup.Connected.DRIVEBASE) {
            fl = new EncodedMotor<DcMotorEx>(Setup.HardwareNames.FL_DRIVE_MOTOR);
            fr = new EncodedMotor<DcMotorEx>(Setup.HardwareNames.FR_DRIVE_MOTOR);
            rl = new EncodedMotor<DcMotorEx>(Setup.HardwareNames.RL_DRIVE_MOTOR);
            rr = new EncodedMotor<DcMotorEx>(Setup.HardwareNames.RR_DRIVE_MOTOR);
        }
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            clawservo = new Servo(Setup.HardwareNames.CLAWSERVO);
            wristservo = new Servo(Setup.HardwareNames.WRISTSERVO);
            linkservo = new Servo(Setup.HardwareNames.LINKSERVO);
        }
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            armservo = new Servo(Setup.HardwareNames.ARMSERVO);
            bucketservo = new Servo(Setup.HardwareNames.BUCKETSERVO);
            slidemotor = new EncodedMotor<>(Setup.HardwareNames.SLIDEMOTOR);
        }
        if (Setup.Connected.ODOSUBSYSTEM) {
            odoR = new MotorEncoder(Setup.HardwareNames.ODOR);
            odoF = new MotorEncoder(Setup.HardwareNames.ODOF);
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            suspend = new EncodedMotor(Setup.HardwareNames.SUSPEND);
            suspend2 = new EncodedMotor(Setup.HardwareNames.SUSPEND2);
        }
    }

    // We can read the voltage from the different hubs for fun...
    public double voltage() {
        double volt = 0;
        double count = 0;
        for (LynxModule lm : hubs) {
            count += 1;
            volt += lm.getInputVoltage(VoltageUnit.VOLTS);
        }
        return volt / count;
    }
}
