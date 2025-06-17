package org.firstinspires.ftc.ptechnodactyl;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.ptechnodactyl.Hardware;
import org.firstinspires.ftc.ptechnodactyl.helpers.StartingPosition;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;
import org.firstinspires.ftc.ptechnodactyl.subsystems.DrivebaseSubsystem;

public class Robot implements Loggable {

    public StartingPosition position;
    public Alliance alliance;
    public double initialVoltage;
    public DrivebaseSubsystem drivebaseSubsystem;
    public ClawSubsystem clawSubsystem;

    public Robot(Hardware hw) {
        this.initialVoltage = hw.voltage();
        if (Setup.Connected.DRIVEBASE) {
            this.drivebaseSubsystem = new DrivebaseSubsystem(
                hw.flMotor,
                hw.frMotor,
                hw.rlMotor,
                hw.rrMotor,
                hw.imu
            );
        }
        if (Setup.Connected.CLAWSUBSYSTEM) {
            this.clawSubsystem = new ClawSubsystem(hw);
        }
    }
}
