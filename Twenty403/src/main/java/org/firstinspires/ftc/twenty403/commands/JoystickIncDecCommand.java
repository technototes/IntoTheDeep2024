package org.firstinspires.ftc.twenty403.commands;

import static org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem.DriveConstants.NORMAL_ROTATION_SCALE;
import static org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem.DriveConstants.SLOW_ROTATION_SCALE;
import static org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem.DriveConstants.TRIGGER_THRESHOLD;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.technototes.library.command.Command;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import com.technototes.library.util.MathUtils;

import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.subsystems.ArmSubsystem;
import org.firstinspires.ftc.twenty403.subsystems.DrivebaseSubsystem;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class JoystickIncDecCommand implements Command, Loggable {

    public ArmSubsystem subsystem;
    public DoubleSupplier x;


    public JoystickIncDecCommand(
        ArmSubsystem sub,
        Stick xStick
    ) {
        addRequirements(sub);
        subsystem = sub;
        x = xStick.getXSupplier();

    }


    // This will make the bot snap to an angle, if the 'straighten' button is pressed
    // Otherwise, it just reads the rotation value from the rotation stick


    @Override
    public void execute() {
            double xvalue = -x.getAsDouble();
        subsystem.increment(xvalue);
    }

    @Override
    public boolean isFinished() {
        return false;
    }


}
