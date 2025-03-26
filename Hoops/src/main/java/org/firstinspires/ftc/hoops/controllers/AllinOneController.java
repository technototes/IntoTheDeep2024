package org.firstinspires.ftc.hoops.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.commands.EZCmd;
import org.firstinspires.ftc.hoops.commands.IntakeAndLaunchCommand;
import org.firstinspires.ftc.hoops.commands.IntakeCommands;
import org.firstinspires.ftc.hoops.commands.JoystickDriveCommand;
import org.firstinspires.ftc.hoops.commands.LaunchCommands;

public class AllinOneController {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, turboButton, snailButton;
    public CommandButton override;
    public CommandAxis driveStraighten;
    public CommandButton launch;
    public CommandButton intake;
    public CommandButton intakeandlaunch;

    public AllinOneController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();
        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
        if (Setup.Connected.LAUNCHER) {
            bindLaunchControls();
        }
        if (Setup.Connected.INTAKE) {
            bindIntakeControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.ps_options;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraighten = gamepad.rightTrigger;
        turboButton = gamepad.leftBumper;
        snailButton = gamepad.rightBumper;
        launch = gamepad.ps_square;
        intake = gamepad.ps_circle;
        intakeandlaunch = gamepad.ps_triangle;
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

        resetGyroButton.whenPressed(EZCmd.Drive.ResetGyro(robot.drivebaseSubsystem));
    }

    public void bindLaunchControls() {
        launch.whenPressed(LaunchCommands.launchCommand(robot));
        launch.whenReleased(LaunchCommands.stopLaunchCommand(robot));
        intakeandlaunch.whenPressed(IntakeAndLaunchCommand.IntakeAndLaunch(robot));
        intakeandlaunch.whenReleased(IntakeAndLaunchCommand.IntakeAndLaunchStop(robot));
    }

    public void bindIntakeControls() {
        intake.whenPressed(IntakeCommands.intakeCommand(robot));
        intake.whenReleased(IntakeCommands.stopIntakeCommand(robot));
    }
}
