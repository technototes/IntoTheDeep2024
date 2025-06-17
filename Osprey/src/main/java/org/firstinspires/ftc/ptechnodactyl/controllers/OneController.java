package org.firstinspires.ftc.ptechnodactyl.controllers;

import android.support.v4.app.INotificationSideChannel;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.DrivingCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickDriveCommand;

public class OneController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton ArmHorizontal;
    public CommandButton ArmVertical;
    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, turboButton, snailButton;
    public CommandButton increment;
    public CommandButton decrement;

    public OneController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.leftBumper;
        closeClaw = gamepad.rightBumper;
        ArmHorizontal = gamepad.ps_square;
        ArmVertical = gamepad.ps_triangle;
        increment = gamepad.ps_circle;
        decrement = gamepad.ps_cross;
        resetGyroButton = gamepad.ps_options;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.leftBumper;
        snailButton = gamepad.rightBumper;
    }

    public void BindControls() {
        if (Setup.Connected.CLAWSUBSYSTEM) {
            bindClawSubsystemControls();
        }
        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
    }

    public void bindClawSubsystemControls() {
        openClaw.whenPressed(robot.clawSubsystem::openClaw);
        closeClaw.whenPressed(robot.clawSubsystem::closeClaw);
        ArmVertical.whenPressed(robot.clawSubsystem::setArmVertical);
        ArmHorizontal.whenPressed(robot.clawSubsystem::setArmHorizontal);
        increment.whenPressed(robot.clawSubsystem::incrementn);
        decrement.whenPressed(robot.clawSubsystem::decrement);
    }

    public void bindDriveControls() {
        CommandScheduler.scheduleJoystick(
            new JoystickDriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick)
        );

        turboButton.whenPressed(DrivingCommands.TurboDriving(robot.drivebaseSubsystem));
        turboButton.whenReleased(DrivingCommands.NormalDriving(robot.drivebaseSubsystem));
        snailButton.whenPressed(DrivingCommands.SnailDriving(robot.drivebaseSubsystem));
        snailButton.whenReleased(DrivingCommands.NormalDriving(robot.drivebaseSubsystem));
        resetGyroButton.whenPressed(DrivingCommands.ResetGyro(robot.drivebaseSubsystem));
    }
}
