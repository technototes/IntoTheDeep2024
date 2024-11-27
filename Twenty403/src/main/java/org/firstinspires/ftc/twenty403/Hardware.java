package org.firstinspires.ftc.twenty403;

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.AdafruitIMU;
import com.technototes.library.hardware.sensor.ColorSensor;
import com.technototes.library.hardware.sensor.IGyro;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.hardware.sensor.encoder.MotorEncoder;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Webcam;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;
import org.firstinspires.ftc.twenty403.helpers.IEncoder;
import org.firstinspires.ftc.twenty403.helpers.OctoquadEncoder;

public class Hardware implements Loggable {

    public List<LynxModule> hubs;

    public IGyro imu;
    public EncodedMotor<DcMotorEx> fl, fr, rl, rr, armL, armR;
    public IEncoder odoF, odoR;
    public Servo retainer, jaw, wrist;
    public CRServo intake;
    public Motor suspend;
    public EncodedMotor<DcMotorEx> rotate1, rotate2, slides;
    public IEncoder armEncoder;
    private OctoQuad octoquad;

    /* Put other hardware here! */

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);
        if (Setup.Connected.EXTERNALIMU) {
            imu = new AdafruitIMU(Setup.HardwareNames.EXTERNALIMU, AdafruitIMU.Orientation.Pitch);
        } else {
            imu = new IMU(
                Setup.HardwareNames.IMU,
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
            );
        }
        if (Setup.Connected.DRIVEBASE) {
            fl = new EncodedMotor<>(Setup.HardwareNames.FLMOTOR);
            fr = new EncodedMotor<>(Setup.HardwareNames.FRMOTOR);
            rl = new EncodedMotor<>(Setup.HardwareNames.RLMOTOR);
            rr = new EncodedMotor<>(Setup.HardwareNames.RRMOTOR);
        }
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
            intake = new CRServo(Setup.HardwareNames.INTAKE);
            retainer = new Servo(Setup.HardwareNames.RETAINER);
            wrist = new Servo(Setup.HardwareNames.WRIST);
            jaw = new Servo(Setup.HardwareNames.JAW);
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            suspend = new Motor(Setup.HardwareNames.SUSPEND);
        }
        if (Setup.Connected.ARMSUBSYSTEM) {
            rotate1 = new EncodedMotor<>(Setup.HardwareNames.ARML);
            rotate2 = new EncodedMotor<>(Setup.HardwareNames.ARMR);
            slides = new EncodedMotor<>(Setup.HardwareNames.SLIDEMOTOR);
        }
        if (Setup.Connected.OCTOQUAD) {
            octoquad = hwmap.get(OctoQuad.class, Setup.HardwareNames.OCTOQUAD);
            octoquad.resetAllPositions();
            if (Setup.Connected.ODOSUBSYSTEM) {
                odoR = new OctoquadEncoder(octoquad, Setup.OctoQuadPorts.ODOR);
                odoF = new OctoquadEncoder(octoquad, Setup.OctoQuadPorts.ODOF);
                odoR.setDirection(Setup.OctoQuadPorts.ODOR_REVERSE);
                odoF.setDirection(Setup.OctoQuadPorts.ODOF_REVERSE);
            }
            armEncoder = new OctoquadEncoder(octoquad, Setup.OctoQuadPorts.ARMENCODER);
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
