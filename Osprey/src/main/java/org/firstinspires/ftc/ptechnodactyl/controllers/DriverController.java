package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.DrivingCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickDriveCommand;

public class DriverController {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, turboButton, snailButton;
    public CommandButton override;

    public DriverController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = g.leftTrigger.getAsButton(0.5);

        AssignNamedControllerButton();
        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.ps_options;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        turboButton = gamepad.leftBumper;
        snailButton = gamepad.rightBumper;
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
