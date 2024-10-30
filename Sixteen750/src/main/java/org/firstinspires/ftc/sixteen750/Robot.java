package org.firstinspires.ftc.sixteen750;

import com.technototes.library.logger.Loggable;
import com.technototes.library.util.Alliance;
import org.firstinspires.ftc.sixteen750.helpers.StartingPosition;
import org.firstinspires.ftc.sixteen750.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.sixteen750.subsystems.HorizontalSlidesSubsystem;
import org.firstinspires.ftc.sixteen750.subsystems.VerticalSlidesSubsystem;

public class Robot implements Loggable {

    public StartingPosition position;
    public Alliance alliance;

    public double initialVoltage;

    public DrivebaseSubsystem drivebase;
    public VerticalSlidesSubsystem slideH;
    public HorizontalSlidesSubsystem horizontalSlidesSubsystem;

    public Robot(Hardware hw, Alliance team, StartingPosition pos) {
        this.position = pos;
        this.alliance = team;
        this.initialVoltage = hw.voltage();

        if (Setup.Connected.DRIVEBASE) {
            drivebase = new DrivebaseSubsystem(hw.fl, hw.fr, hw.rl, hw.rr, hw.imu);
        }
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            slideH = new VerticalSlidesSubsystem(hw);
        }
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            horizontalSlidesSubsystem = new HorizontalSlidesSubsystem(hw);
        }
    }
}
