package org.firstinspires.ftc.twenty403;

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.twenty403.helpers.OctoquadEncoder;
import org.firstinspires.ftc.twenty403.helpers.StartingPosition;
import org.firstinspires.ftc.twenty403.subsystems.ArmSubsystem;
import org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.twenty403.subsystems.HangSubsystem;
import org.firstinspires.ftc.twenty403.subsystems.KidShampooSubsystem;
import org.firstinspires.ftc.twenty403.subsystems.SafetySubsystem;
import org.firstinspires.ftc.twenty403.subsystems.TwoDeadWheelLocalizer;

public class Robot implements Loggable {

    public StartingPosition position;
    public Alliance alliance;
    public double initialVoltage;

    public DrivebaseSubsystem drivebaseSubsystem;
    public KidShampooSubsystem kidShampooSubsystem;
    public TwoDeadWheelLocalizer localizer;
    public SafetySubsystem safetySubsystem;
    public HangSubsystem hangSubsystem;
    public ArmSubsystem armSubsystem;

    public Robot(Hardware hw, Alliance team, StartingPosition pos) {
        this.position = pos;
        this.alliance = team;
        this.initialVoltage = hw.voltage();
        if (Setup.Connected.ODOSUBSYSTEM && Setup.Connected.OCTOQUAD) {
            this.localizer = new TwoDeadWheelLocalizer(hw.odoF, hw.odoR, hw.imu);
        } else {
            this.localizer = null;
        }
        if (Setup.Connected.DRIVEBASE) {
            this.drivebaseSubsystem = new DrivebaseSubsystem(
                hw.fl,
                hw.fr,
                hw.rl,
                hw.rr,
                hw.imu,
                localizer
            );
        }
        if (Setup.Connected.SAFETYSUBSYSTEM) {
            this.safetySubsystem = new SafetySubsystem(hw);
        }
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
            this.kidShampooSubsystem = new KidShampooSubsystem(hw);
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            this.hangSubsystem = new HangSubsystem(hw);
        }
        if (Setup.Connected.ARMSUBSYSTEM) {
            this.armSubsystem = new ArmSubsystem(hw);
        }
    }

    public void atStart() {
        if (Setup.Connected.ARMSUBSYSTEM) {
            armSubsystem.setSlideToZero();
        }
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
            kidShampooSubsystem.dumpWrist();
            kidShampooSubsystem.closeRetainer();
            kidShampooSubsystem.releaseJaw();
        }
    }
}
