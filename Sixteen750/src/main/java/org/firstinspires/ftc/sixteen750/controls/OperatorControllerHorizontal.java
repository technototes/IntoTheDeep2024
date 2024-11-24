package org.firstinspires.ftc.sixteen750.controls;

import static org.firstinspires.ftc.sixteen750.Setup.Connected.HORIZONTALSLIDESUBSYSTEM;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalAnalogCommand;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesSequentials;

public class OperatorControllerHorizontal {

    public Robot robot;
    public Stick horiSlidesManual;
    public CommandGamepad gamepad;

    //horizontal buttons
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristDecrement;
    public CommandButton wristIncrement;
    public CommandButton wristZero;
    public CommandButton horislidesExtend;
    public CommandButton horislidesRetract;
    public CommandButton extend_retract;
    public CommandButton openClaw_closeClaw;
    public CommandButton wristTransfer_wristPickup;

    public OperatorControllerHorizontal(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        bindSlidesControls();
    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.leftBumper;
        closeClaw = gamepad.rightBumper;
        wristTransfer = gamepad.ps_square;
        wristPickup = gamepad.ps_circle;
        wristZero = gamepad.ps_share; //test this
        wristDecrement = gamepad.dpadRight;
        wristIncrement = gamepad.dpadLeft;
        horiSlidesManual = gamepad.rightStick;
        horislidesExtend = gamepad.ps_cross;
        horislidesRetract = gamepad.ps_triangle;
        extend_retract = gamepad.ps_options;
        openClaw_closeClaw = gamepad.dpadUp;
        wristTransfer_wristPickup = gamepad.dpadDown;

    }

    private void bindSlidesControls() {
        if (HORIZONTALSLIDESUBSYSTEM){
        openClaw.whenPressed(HorizontalSlidesCommands.clawOpen(robot));
        closeClaw.whenPressed(HorizontalSlidesCommands.clawChomp(robot));
        wristZero.whenPressed(HorizontalSlidesCommands.resetWristZero(robot)); //test this
        wristPickup.whenPressed(HorizontalSlidesCommands.wristPickup(robot));
        wristTransfer.whenPressed(HorizontalSlidesCommands.wristTransfer(robot));
        wristDecrement.whenPressed(HorizontalSlidesCommands.wristDecrement(robot));
        wristIncrement.whenPressed(HorizontalSlidesCommands.wristIncrement(robot));
        horislidesExtend.whenPressed(HorizontalSlidesSequentials.intake(robot));
        horislidesRetract.whenPressed(HorizontalSlidesSequentials.transferring(robot));
        //toggle command
        extend_retract.whenPressed(HorizontalSlidesCommands.horiSlideToggle(robot));
        openClaw_closeClaw.whenPressed(HorizontalSlidesCommands.clawToggle(robot));
        wristTransfer_wristPickup.whenPressed(HorizontalSlidesCommands.wristToggle(robot));
        //manual extend/retract
        CommandScheduler.scheduleJoystick(
                new HorizontalAnalogCommand(robot.horizontalSlidesSubsystem, horiSlidesManual)
        );
        }

    }




}
