package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristIncrement;
    public CommandButton wristDecrement;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindButtons();
    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.ps_circle;
        closeClaw = gamepad.ps_cross;
        wristTransfer = gamepad.ps_triangle;
        wristIncrement = gamepad.dpadUp;
        wristDecrement = gamepad.dpadDown;
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            bindHorizontalSlidesControls();
        }
    }

    private void bindHorizontalSlidesControls() {
        openClaw.whenPressed(
            Command.create(
                robot.horizontalSlidesSubsystem::ClawServoBigOpen,
                robot.horizontalSlidesSubsystem
            )
        );
        closeClaw.whenPressed(
            Command.create(
                robot.horizontalSlidesSubsystem::ClawServoChomp,
                robot.horizontalSlidesSubsystem
            )
        );
        wristPickup.whenPressed(
            Command.create(
                robot.horizontalSlidesSubsystem::WristServoPickup,
                robot.horizontalSlidesSubsystem
            )
        );
        wristTransfer.whenPressed(
            Command.create(
                robot.horizontalSlidesSubsystem::WristServoTransfer,
                robot.horizontalSlidesSubsystem
            )
        );
        wristIncrement.whenPressed(
            Command.create(
                robot.horizontalSlidesSubsystem::WristServoIncrement,
                robot.horizontalSlidesSubsystem
            )
        );
        wristDecrement.whenPressed(
            Command.create(
                robot.horizontalSlidesSubsystem::WristServoDecrement,
                robot.horizontalSlidesSubsystem
            )
        );
    }
}
