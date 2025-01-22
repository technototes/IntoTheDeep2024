package org.firstinspires.ftc.hoops;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.AdafruitIMU;
import com.technototes.library.hardware.sensor.ColorDistanceSensor;
import com.technototes.library.hardware.sensor.IGyro;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Webcam;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class Hardware implements Loggable {

    public List<LynxModule> hubs;

    public EncodedMotor<DcMotorEx> flMotor, frMotor, rlMotor, rrMotor;
    public IGyro imu;

    public EncodedMotor<DcMotorEx> topLaunch, bottomLaunch;
    public EncodedMotor<DcMotorEx> slurp;

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);
        if (Setup.Connected.EXTERNAL_IMU) {
            imu = new AdafruitIMU(Setup.HardwareNames.EXTERNAL_IMU, AdafruitIMU.Orientation.Pitch);
        } else {
            imu = new IMU(
                Setup.HardwareNames.IMU,
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD
            );
        }
        if (Setup.Connected.DRIVEBASE) {
            this.frMotor = new EncodedMotor<>(Setup.HardwareNames.FRMOTOR);
            this.flMotor = new EncodedMotor<>(Setup.HardwareNames.FLMOTOR);
            this.rrMotor = new EncodedMotor<>(Setup.HardwareNames.RRMOTOR);
            this.rlMotor = new EncodedMotor<>(Setup.HardwareNames.RLMOTOR);
        }
        if (Setup.Connected.LAUNCHER) {
            this.topLaunch = new EncodedMotor<>(Setup.HardwareNames.TOP_LAUNCH);
            this.bottomLaunch = new EncodedMotor<>(Setup.HardwareNames.BOTTOM_LAUNCH);
        }
        if (Setup.Connected.INTAKE) {
            this.slurp = new EncodedMotor<>(Setup.HardwareNames.INTAKE);
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
