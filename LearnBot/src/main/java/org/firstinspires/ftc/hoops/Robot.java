package org.firstinspires.ftc.hoops;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.hoops.helpers.StartingPosition;
import org.firstinspires.ftc.hoops.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.hoops.subsystems.PlacementSubsystem;
import org.firstinspires.ftc.hoops.subsystems.TestSubsystem;

public class Robot implements Loggable {

    public StartingPosition position;
    public Alliance alliance;
    public double initialVoltage;
    public DrivebaseSubsystem drivebaseSubsystem;
    public PlacementSubsystem placementSubsystem;
    public TestSubsystem testsubsystem;

    public Robot(Hardware hw) {
        this.initialVoltage = hw.voltage();
        this.testsubsystem = new TestSubsystem(hw);
        if (Setup.Connected.DRIVEBASE) {
            this.drivebaseSubsystem = new DrivebaseSubsystem(
                hw.flMotor,
                hw.frMotor,
                hw.rlMotor,
                hw.rrMotor,
                hw.imu
            );
        }
        if (Setup.Connected.TESTSUBSYSTEM) {
            this.testsubsystem = new TestSubsystem(hw);
        }
    }
}
