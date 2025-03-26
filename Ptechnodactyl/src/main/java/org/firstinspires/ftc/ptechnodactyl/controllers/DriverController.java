package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.EZCmd;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickDriveCommand;

public class DriverController {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, turboButton, snailButton, clawOpen, clawClose;
    public CommandButton override;
    public CommandAxis driveStraighten;

    public DriverController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();
        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
        clawOpen.whenPressed(ClawCommands.clawOpen(robot.clawSubsystem));
        clawClose.whenPressed(ClawCommands.clawClose(robot.clawSubsystem));
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.ps_options;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraighten = gamepad.rightTrigger;
        turboButton = gamepad.leftBumper;
        snailButton = gamepad.rightBumper;
        clawOpen = gamepad.ps_triangle;
        clawClose = gamepad.ps_cross;
    }

    public void bindDriveControls() {
        CommandScheduler.scheduleJoystick(
            new JoystickDriveCommand(
                robot.drivebaseSubsystem,
                driveLeftStick,
                driveRightStick,
                driveStraighten
            )
        );
        turboButton.whenPressed(EZCmd.Drive.TurboMode(robot.drivebaseSubsystem));
        turboButton.whenReleased(EZCmd.Drive.NormalMode(robot.drivebaseSubsystem));
        snailButton.whenPressed(EZCmd.Drive.SnailMode(robot.drivebaseSubsystem));
        snailButton.whenReleased(EZCmd.Drive.NormalMode(robot.drivebaseSubsystem));
//        clawOpen.whenPressed(ClawCommands.clawOpen(robot.clawSubsystem));
//        clawClose.whenPressed(ClawCommands.clawClose(robot.clawSubsystem));

        resetGyroButton.whenPressed(EZCmd.Drive.ResetGyro(robot.drivebaseSubsystem));
    }
}
