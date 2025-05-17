package org.firstinspires.ftc.hoops;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.hoops.helpers.StartingPosition;
import org.firstinspires.ftc.hoops.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.hoops.subsystems.IntakeSubsystem;
import org.firstinspires.ftc.hoops.subsystems.LauncherSubsystem;

public class Robot implements Loggable {

    public StartingPosition position;
    public Alliance alliance;
    public double initialVoltage;
    public DrivebaseSubsystem drivebaseSubsystem;
    public LauncherSubsystem launcherSubsystem;
    public IntakeSubsystem intakeSubsystem;

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
        if (Setup.Connected.LAUNCHER) {
            this.launcherSubsystem = new LauncherSubsystem(hw);
        }
        if (Setup.Connected.INTAKE) {
            this.intakeSubsystem = new IntakeSubsystem(hw);
        }
    }
}
