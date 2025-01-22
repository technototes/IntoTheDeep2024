package org.firstinspires.ftc.hoops.commands;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.util.MathUtils;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.subsystems.DrivebaseSubsystem;

public class JoystickDriveCommand implements Command {

    public DrivebaseSubsystem subsystem;
    public DoubleSupplier x, y, r;
    public BooleanSupplier watchTrigger;
    public double targetHeadingRads;
    public DoubleSupplier driveStraighten;

    public JoystickDriveCommand(
        DrivebaseSubsystem sub,
        Stick xyStick,
        Stick rotStick,
        DoubleSupplier straightDrive
    ) {
        addRequirements(sub);
        subsystem = sub;
        x = xyStick.getXSupplier();
        y = xyStick.getYSupplier();
        r = rotStick.getXSupplier();
        targetHeadingRads = -sub.getExternalHeading();
        driveStraighten = straightDrive;
    }

    // Use this constructor if you don't want auto-straightening
    public JoystickDriveCommand(DrivebaseSubsystem sub, Stick xyStick, Stick rotStick) {
        this(sub, xyStick, rotStick, null);
    }

    // This will make the bot snap to an angle, if the 'straighten' button is pressed
    // Otherwise, it just reads the rotation value from the rotation stick
    private double getRotation(double headingInRads) {
        // Check to see if we're trying to straighten the robot
        if (
            driveStraighten == null ||
            driveStraighten.getAsDouble() < Setup.GlobalSettings.TRIGGER_THRESHOLD
        ) {
            // No straighten override: return the stick value
            // (with some adjustment...)
            return -Math.pow(r.getAsDouble(), 3) * subsystem.speed;
        } else {
            // headingInRads is [0-2pi]
            double heading = -Math.toDegrees(headingInRads);
            double close = MathUtils.closestTo(heading, 0, 90, 180, 270, 360);
            double offBy = close - heading;
            // Normalize the error to -1 to 1
            double normalized = Math.max(Math.min(offBy / 45, 1.), -1.);
            // Dead zone of 5 degreesLiftHighJunctionCommand(liftSubsystem)
            if (Math.abs(normalized) < Setup.GlobalSettings.STRAIGHTEN_RANGE) {
                return 0.0;
            }
            // Fix the range to be from (abs) dead_zone => 1 to scal from 0 to 1
            // Scale it by the cube root, the scale that down by 30%
            // .9 (about 40 degrees off) provides .96 power => .288
            // .1 (about 5 degrees off) provides .46 power => .14
            return normalized * Setup.GlobalSettings.STRAIGHTEN_SCALE_FACTOR; // Math.cbrt(normalized) * Setup.GlobalSettings.STRAIGHTEN_SCALE_FACTOR;
        }
    }

    @Override
    public void execute() {
        // If subsystem is busy it is running a trajectory.
        if (!subsystem.isBusy()) {
            double curHeading = -subsystem.getExternalHeading();

            // The math & signs looks wonky, because this makes things field-relative
            // (Remember that "3 O'Clock" is zero degrees)
            double yvalue = -y.getAsDouble();
            double xvalue = -x.getAsDouble();
            if (driveStraighten != null) {
                if (driveStraighten.getAsDouble() > 0.7) {
                    if (Math.abs(yvalue) > Math.abs(xvalue)) xvalue = 0;
                    else yvalue = 0;
                }
            }
            Vector2d input = new Vector2d(
                yvalue * subsystem.speed,
                xvalue * subsystem.speed
            ).rotated(curHeading);

            subsystem.setWeightedDrivePower(
                new Pose2d(input.getX(), input.getY(), getRotation(curHeading))
            );
        }
        subsystem.update();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean cancel) {
        if (cancel) subsystem.setDriveSignal(new DriveSignal());
    }
}
