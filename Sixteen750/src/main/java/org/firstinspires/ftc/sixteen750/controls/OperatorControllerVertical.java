package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;

public class OperatorControllerVertical {

    public Robot robot;
    public Stick vertslidesLeftStick;
    public CommandGamepad gamepad;
    public CommandButton bucketTransfer;
    public CommandButton armTransfer;
    public CommandButton armScore;
    public CommandButton slidesLow;
    public CommandButton slidesHigh;
    public CommandButton slidesDown;
    //public CommandButton wristDecrement;

    public OperatorControllerVertical(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindButtons();
    }

    private void AssignNamedControllerButton() {
        bucketTransfer = gamepad.ps_circle;
        armTransfer = gamepad.ps_cross;
        slidesLow = gamepad.ps_triangle;
        slidesHigh = gamepad.dpadUp;
        armScore = gamepad.ps_square;
        slidesDown = gamepad.dpadDown;
        //wristDecrement = gamepad.dpadDown;
        vertslidesLeftStick = gamepad.leftStick;
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            bindVerticalSlidesControls();
        }
    }

    private void bindVerticalSlidesControls() {
        bucketTransfer.whenPressed(VerticalSlidesCommands.BucketTransfer(robot));
        armTransfer.whenPressed(VerticalSlidesCommands.ArmTransfer(robot));
        armScore.whenPressed(VerticalSlidesCommands.ArmScore(robot));
        slidesHigh.whenPressed(VerticalSlidesCommands.LowBasket(robot));
        slidesLow.whenPressed(VerticalSlidesCommands.LowBasket(robot));
        slidesDown.whenPressed(VerticalSlidesCommands.SlidesZero(robot));
        //wristDecrement.whenPressed(SlidesCommands.wristDecrement(robot));
    }
}
