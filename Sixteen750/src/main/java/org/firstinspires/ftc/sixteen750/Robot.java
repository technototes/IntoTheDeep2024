package org.firstinspires.ftc.sixteen750;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesSequentials;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;
import org.firstinspires.ftc.sixteen750.helpers.StartingPosition;
import org.firstinspires.ftc.sixteen750.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.sixteen750.subsystems.HangSubsystem;
import org.firstinspires.ftc.sixteen750.subsystems.HorizontalSlidesSubsystem;
import org.firstinspires.ftc.sixteen750.subsystems.SafetySubsystem;
import org.firstinspires.ftc.sixteen750.subsystems.TwoDeadWheelLocalizer;
import org.firstinspires.ftc.sixteen750.subsystems.VerticalSlidesSubsystem;

public class Robot implements Loggable {

    public StartingPosition position;
    public Alliance alliance;

    public double initialVoltage;

    public DrivebaseSubsystem drivebase;
    public VerticalSlidesSubsystem verticalSlidesSubsystem;
    public HorizontalSlidesSubsystem horizontalSlidesSubsystem;
    public TwoDeadWheelLocalizer localizer;
    public SafetySubsystem safetySubsystem;
    public HangSubsystem hangSubsystem;

    public Robot(Hardware hw, Alliance team, StartingPosition pos) {
        this.position = pos;
        this.alliance = team;
        this.initialVoltage = hw.voltage();

        if (Setup.Connected.ODOSUBSYSTEM) {
            this.localizer = new TwoDeadWheelLocalizer(hw.odoR, hw.odoF);
        } else {
            this.localizer = null;
        }
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            verticalSlidesSubsystem = new VerticalSlidesSubsystem(hw);
        }
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            horizontalSlidesSubsystem = new HorizontalSlidesSubsystem(hw);
        }

        if (Setup.Connected.DRIVEBASE) {
            drivebase = new DrivebaseSubsystem(hw.fl, hw.fr, hw.rl, hw.rr, hw.imu, localizer);

            if (localizer != null) {
                // YOU MUST CALL THIS IMMEDIATELY AFTER CREATING THE DRIVEBASE!
                localizer.setDrivebase(this.drivebase);
            }
        }

        if (Setup.Connected.SAFETYSUBSYSTEM) {
            this.safetySubsystem = new SafetySubsystem(hw);
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            this.hangSubsystem = new HangSubsystem(hw);
        }
    }

    public void prepForStart() {
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            horizontalSlidesSubsystem.WristServoTransfer();
            horizontalSlidesSubsystem.slidesRetract();
            horizontalSlidesSubsystem.ClawChomp();
        }
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            verticalSlidesSubsystem.slidesDown();
        }
    }
}
