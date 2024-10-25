package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.slides.SlidesCommands;

public class OperatorController {

    public Robot robot;
    public Stick horislidesLeftStick;
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
        wristPickup = gamepad.ps_square;
        wristIncrement = gamepad.dpadUp;
        wristDecrement = gamepad.dpadDown;
        horislidesLeftStick = gamepad.leftStick;
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            bindHorizontalSlidesControls();
        }
    }

    private void bindHorizontalSlidesControls() {
        openClaw.whenPressed(Command.create(SlidesCommands.clawOpen(robot)));
        closeClaw.whenPressed(Command.create(SlidesCommands.clawChomp(robot)));
        wristPickup.whenPressed(Command.create(SlidesCommands.wristPickup(robot)));
        wristTransfer.whenPressed(SlidesCommands.wristTransfer(robot));
        wristIncrement.whenPressed(SlidesCommands.wristIncrement(robot));
        wristDecrement.whenPressed(SlidesCommands.wristDecrement(robot));
    }
}
