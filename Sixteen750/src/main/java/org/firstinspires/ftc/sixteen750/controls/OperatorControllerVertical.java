package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;

public class OperatorControllerVertical {

    public Robot robot;
    public Stick vertslidesLeftStick;
    public CommandGamepad gamepad;
    public CommandButton bucketTransfer;
    public CommandButton bucketScore;
    public CommandButton bucketIncrement;
    public CommandButton bucketDecrement;
    public CommandButton armTransfer;
    public CommandButton armScore;
    public CommandButton slidesLow;
    public CommandButton slidesHigh;
    public CommandButton slidesZero;
    public CommandButton slidesDown;
    //public CommandButton wristDecrement;
    public CommandButton slidesUpTesting;

    public CommandButton slidesDownTesting;

    public OperatorControllerVertical(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindButtons();
    }

    private void AssignNamedControllerButton() {
        bucketTransfer = gamepad.ps_circle;
        bucketScore = gamepad.ps_triangle;
        armTransfer = gamepad.ps_cross;
        armScore = gamepad.ps_square;
        slidesHigh = gamepad.ps_options;
        slidesLow = gamepad.ps_triangle;
        slidesDown = gamepad.ps_cross;
        bucketIncrement = gamepad.dpadDown;
        bucketDecrement = gamepad.dpadDown;
        vertslidesLeftStick = gamepad.leftStick;
        slidesZero = gamepad.ps_square;
//        slidesUpTesting = gamepad.ps_triangle;
//        slidesDownTesting = gamepad.ps_cross;
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            bindVerticalSlidesControls();
        }
    }


    private void bindVerticalSlidesControls() {
//          bucketTransfer.whenPressed(VerticalSlidesCommands.BucketTransfer(robot));
//          bucketScore.whenPressed(VerticalSlidesCommands.BucketEmpty(robot));
//          armTransfer.whenPressed(VerticalSlidesCommands.ArmTransfer(robot));
//          armScore.whenPressed(VerticalSlidesCommands.ArmScore(robot));
          slidesHigh.whenPressed(VerticalSlidesCommands.HighBasket(robot));
          slidesLow.whenPressed(VerticalSlidesCommands.LowBasket(robot));
          slidesDown.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
          slidesZero.whenPressed(VerticalSlidesCommands.SlidesZero(robot));
        //wristDecrement.whenPressed(SlidesCommands.wristDecrement(robot));
//        slidesUpTesting.whenPressed(VerticalSlidesCommands.SlidesUp(robot));
//        slidesDownTesting.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
    }
}
