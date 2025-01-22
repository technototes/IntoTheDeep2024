package org.firstinspires.ftc.hoops.imutesting;

import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.hardware.adafruit.AdafruitBNO055IMUNew;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.logger.Loggable;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VoltageUnit;

public class ImuOnlyHardware implements Loggable {

    public List<LynxModule> hubs;

    public IMU imu;
    public com.qualcomm.robotcore.hardware.IMU pubImu;
    public AdafruitBNO055IMU extImu;
    public AdafruitBNO055IMUNew newImu;

    public ImuOnlyHardware(HardwareMap hwMap) {
        hubs = hwMap.getAll(LynxModule.class);
        imu = new IMU(
            ImuSetup.INTERNAL_IMU,
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP
        );
        pubImu = hwMap.get(com.qualcomm.robotcore.hardware.IMU.class, ImuSetup.INTERNAL_IMU);
        extImu = hwMap.get(AdafruitBNO055IMU.class, ImuSetup.EXTERNAL_IMU);
        newImu = hwMap.get(AdafruitBNO055IMUNew.class, ImuSetup.EXTERNAL_IMU);
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
