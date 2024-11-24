package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalAnalogCommand;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;

public class OperatorControllerVertical {

    public Robot robot;
    public Stick vertSlidesManual;
    public CommandGamepad gamepad;
    public CommandButton bucketTransfer;
    public CommandButton bucketEmpty;
    public CommandButton bucketIncrement;
    public CommandButton bucketDecrement;
    public CommandButton armTransfer;
    public CommandButton armEmpty;
    public CommandButton armIncrement;
    public CommandButton armDecrement;
    public CommandButton slidesHigh;
    public CommandButton slidesLow;
    public CommandButton slidesDown;
    public CommandButton slidesHighSequential;
    public CommandButton slidesLowSequential;
    public CommandButton slidesDownSequential;
    public CommandButton transferVertical;
    public CommandButton basketScore;
    public CommandButton bucketTransfer_bucketEmpty;
    public CommandButton armTransfer_armEmpty;

    public OperatorControllerVertical(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindButtons();
    }

    private void AssignNamedControllerButton() {
        bucketTransfer_bucketEmpty = gamepad.ps_circle;
        armTransfer_armEmpty = gamepad.ps_circle;

        //bucketTransfer = gamepad.ps_circle;
        //bucketEmpty = gamepad.ps_triangle;
        bucketIncrement = gamepad.dpadLeft;
        bucketDecrement = gamepad.dpadRight;
        //armTransfer = gamepad.ps_cross;//
        //armEmpty = gamepad.ps_square;
        armIncrement = gamepad.dpadLeft;
        armDecrement = gamepad.dpadRight;
        slidesHigh = gamepad.ps_share;
        slidesLow = gamepad.dpadUp;
        slidesDown = gamepad.dpadDown;
        slidesHighSequential = gamepad.ps_options;
        slidesLowSequential = gamepad.ps_triangle;
        slidesDownSequential = gamepad.ps_cross;
        vertSlidesManual = gamepad.leftStick;
        transferVertical = gamepad.ps_cross; //change
        basketScore = gamepad.ps_square; //change
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            bindVerticalSlidesControls();
        }
    }

    private void bindVerticalSlidesControls() {
        bucketTransfer_bucketEmpty.whenPressed(VerticalSlidesCommands.bucketToggle(robot));
        //bucketTransfer.whenPressed(VerticalSlidesCommands.BucketTransfer(robot));
        //bucketEmpty.whenPressed(VerticalSlidesCommands.BucketEmpty(robot));
        bucketIncrement.whenPressed(VerticalSlidesCommands.BucketIncrement(robot));
        bucketDecrement.whenPressed(VerticalSlidesCommands.BucketDecrement(robot));
        armTransfer_armEmpty.whenPressed(VerticalSlidesCommands.armToggle(robot));
        //armTransfer.whenPressed(VerticalSlidesCommands.ArmTransfer(robot));
        //armEmpty.whenPressed(VerticalSlidesCommands.ArmEmpty(robot));
        armIncrement.whenPressed(VerticalSlidesCommands.ArmIncrement(robot));
        armDecrement.whenPressed(VerticalSlidesCommands.ArmDecrement(robot));
        slidesHigh.whenPressed(VerticalSlidesCommands.HighBasket(robot));
        slidesLow.whenPressed(VerticalSlidesCommands.LowBasket(robot));
        slidesDown.whenPressed(VerticalSlidesSequentials.SlidesDown(robot));
        basketScore.whenPressed(VerticalSlidesSequentials.BasketScore(robot));
        transferVertical.whenPressed(VerticalSlidesSequentials.transferVertical(robot));

        slidesHighSequential.whenPressed(VerticalSlidesSequentials.HighBasket(robot));
        slidesLowSequential.whenPressed(VerticalSlidesSequentials.LowBasket(robot));
        slidesDownSequential.whenPressed(VerticalSlidesSequentials.transferVertical(robot));
        CommandScheduler.scheduleJoystick(
                new VerticalAnalogCommand(robot.verticalSlidesSubsystem, vertSlidesManual)
        );
    }
}
