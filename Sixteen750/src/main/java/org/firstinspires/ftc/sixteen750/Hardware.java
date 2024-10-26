package org.firstinspires.ftc.sixteen750;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class Hardware implements Loggable {

    public List<LynxModule> hubs;

    public IMU imu;
    public EncodedMotor<DcMotorEx> fl, fr, rl, rr;
    public Servo clawservo;
    public Servo wristservo;
    public Servo linkservo;
    public EncodedMotor<DcMotorEx> slidemotor;
    public Servo armservo;
    public Servo bucketservo;

    /* Put other hardware here! */

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);
        imu = new IMU(
            Setup.HardwareNames.IMU,
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP
        );

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

        if (Setup.Connected.BUCKET) {
            armservo = new Servo(Setup.HardwareNames.ARMSERVO);
            bucketservo = new Servo(Setup.HardwareNames.BUCKETSERVO);
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
