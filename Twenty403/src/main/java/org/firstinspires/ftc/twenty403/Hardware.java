package org.firstinspires.ftc.twenty403;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoController;
import com.technototes.library.hardware.motor.EncodedMotor;
import com.technototes.library.hardware.motor.Motor;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.hardware.motor.CRServo;
import com.technototes.library.hardware.sensor.encoder.MotorEncoder;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.logger.Loggable;
import com.technototes.vision.hardware.Webcam;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class Hardware implements Loggable {

    public List<LynxModule> hubs;

    public IMU imu;
    public EncodedMotor<DcMotorEx> fl, fr, rl, rr;
    public MotorEncoder odoF, odoR;
    public Servo retainer, jaw;
    public CRServo intake;

    /* Put other hardware here! */

    public Hardware(HardwareMap hwmap) {
        hubs = hwmap.getAll(LynxModule.class);
        imu = new IMU(
            Setup.HardwareNames.IMU,
            RevHubOrientationOnRobot.LogoFacingDirection.UP,
            RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );
        if (Setup.Connected.DRIVEBASE) {
            fl = new EncodedMotor<>(Setup.HardwareNames.FLMOTOR);
            fr = new EncodedMotor<>(Setup.HardwareNames.FRMOTOR);
            rl = new EncodedMotor<>(Setup.HardwareNames.RLMOTOR);
            rr = new EncodedMotor<>(Setup.HardwareNames.RRMOTOR);
        }
        if (Setup.Connected.ODOSUBSYSTEM) {
            odoR = new MotorEncoder(Setup.HardwareNames.ODOR);
            odoF = new MotorEncoder(Setup.HardwareNames.ODOF);
        }
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM){
            intake = new CRServo(Setup.HardwareNames.INTAKE);
            retainer = new Servo(Setup.HardwareNames.RETAINER);
            jaw = new Servo(Setup.HardwareNames.JAW);
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
