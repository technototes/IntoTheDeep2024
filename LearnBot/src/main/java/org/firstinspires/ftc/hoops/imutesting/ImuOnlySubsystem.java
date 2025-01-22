package org.firstinspires.ftc.hoops.imutesting;

import com.qualcomm.hardware.adafruit.AdafruitBNO055IMU;
import com.qualcomm.hardware.adafruit.AdafruitBNO055IMUNew;
import com.technototes.library.hardware.sensor.IMU;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ImuOnlySubsystem implements Subsystem, Loggable {

    IMU internalImu;
    com.qualcomm.robotcore.hardware.IMU publicImu;
    AdafruitBNO055IMU externalImu;
    AdafruitBNO055IMUNew extNewImu;

    @Log(name = "TlImu")
    public String internal = "";

    @Log(name = "ExtImu")
    public String external = "";

    @Log(name = "PubImu")
    public String rawDevice = "";

    @Log(name = "NewImu")
    public String newDevice = "";

    public ImuOnlySubsystem(ImuOnlyHardware hw) {
        internalImu = hw.imu;
        externalImu = hw.extImu;
        publicImu = hw.pubImu;
        extNewImu = hw.newImu;
    }

    @Override
    public void periodic() {
        internal = String.valueOf(internalImu.getHeading());
        Position p = externalImu.getPosition();
        external = String.valueOf(p);
        YawPitchRollAngles ypr = publicImu.getRobotYawPitchRollAngles();
        rawDevice = ypr.toString();
        ypr = extNewImu.getRobotYawPitchRollAngles();
        newDevice = ypr.toString();
    }
}
