package org.firstinspires.ftc.sixteen750.controls;

import static org.firstinspires.ftc.sixteen750.Setup.Connected.HORIZONTALSLIDESUBSYSTEM;
import static org.firstinspires.ftc.sixteen750.Setup.Connected.VERTICALSLIDESUBSYSTEM;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;

import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalAnalogCommand;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton shiftButton;

    //horizontal buttons
    public Stick horiSlidesManual;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristDecrement;
    public CommandButton wristIncrement;
    public CommandButton wristZero;
    public CommandButton horislidesExtend;
    public CommandButton horislidesRetract;
    //vertical buttons
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
    public CommandButton slidesZero;
    public CommandButton slidesHighSequential;
    public CommandButton slidesLowSequential;
    public CommandButton slidesDownSequential;
    public CommandButton transferVertical;
    public CommandButton basketScore;
    public CommandButton bucketTransfer_bucketEmpty;
    public CommandButton armTransfer_armEmpty;
    //public CommandButton wristDecrement;

    //can we make a command happen when we switch modes? like horizontal retract when vertical is active vice versa
    //can make sequential on the button to retract slides when shifting

    //choice commands
    public CommandButton horizontalSlides_verticalSlides;
    public CommandButton horizontalSlides_verticalSlidesRetract;

    //toggle commands
    public CommandButton openClaw_closeClaw;
    public CommandButton wristTransfer_wristPickup;
    public CommandButton extend_retract;
    public CommandButton down_high;

    public boolean shifted = false;

    public boolean isShifted() {
        return shifted;
    }

    public boolean notShifted() {
        return !shifted;
    }

    public void toggleShift() {
        shifted = !shifted;
    }

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        bindSlidesControls();
    }

    private void AssignNamedControllerButton() {
        openClaw_closeClaw = gamepad.rightBumper;
        wristTransfer_wristPickup = gamepad.ps_square;
        bucketTransfer_bucketEmpty = gamepad.leftBumper;
        //armTransfer_armEmpty = gamepad.dpadLeft;
        //closeClaw = gamepad.rightBumper;
        //wristTransfer = gamepad.ps_square;
        //wristPickup = gamepad.ps_circle;
        //wristZero = gamepad.ps_options;
        //wristDecrement = gamepad.dpadRight;
        //wristIncrement = gamepad.dpadLeft;

        horiSlidesManual = gamepad.rightStick;
        //horislidesExtend = gamepad.ps_cross;
        //horislidesRetract = gamepad.ps_square;

        //vertical commands
        //bucketTransfer = gamepad.ps_circle;
        //bucketScore = gamepad.ps_triangle;

        //bucketIncrement = gamepad.dpadDown;
        //bucketDecrement = gamepad.dpadDown;

        //choice commands
        /*shiftButton = gamepad.ps_share; - need to change button location and set up choice commands*/
        //horizontalSlides_verticalSlides = gamepad.ps_triangle;
        //slidesHigh = gamepad.dpadUp;
        slidesLowSequential = gamepad.dpadUp;
        //slidesDown = gamepad.dpadDown;
        slidesZero = gamepad.ps_share;
        extend_retract = gamepad.ps_cross;
        down_high = gamepad.dpadDown;
    }

    private void bindSlidesControls() {
        /*shiftButton.whenPressed(this::toggleShift); // might use to have a manual and non manual mode*/
        openClaw_closeClaw.whenPressed(HorizontalSlidesCommands.clawToggle(robot));
        wristTransfer_wristPickup.whenPressed(HorizontalSlidesCommands.wristToggle(robot));
        extend_retract.whenPressed(HorizontalSlidesCommands.horiSlideToggle(robot));
        down_high.whenPressed(VerticalSlidesCommands.vertSlideToggle(robot));
        if (HORIZONTALSLIDESUBSYSTEM){
        //closeClaw.whenPressed(HorizontalSlidesCommands.clawChomp(robot));
//        wristPickup.whenPressed(HorizontalSlidesCommands.wristPickup(robot));
//        wristTransfer.whenPressed(HorizontalSlidesCommands.wristTransfer(robot));
        //wristDecrement.whenPressed(HorizontalSlidesCommands.wristIncrement(robot));//flipped but haven't flipped it in code
        //wristIncrement.whenPressed(HorizontalSlidesCommands.wristDecrement(robot));
        //horislidesExtend.whenPressed(HorizontalSlidesSequentials.intake(robot));
        //horislidesRetract.whenPressed(HorizontalSlidesSequentials.transferring(robot));
        //wristZero.whenPressed(HorizontalSlidesCommands.resetWristZero(robot));
        CommandScheduler.scheduleJoystick(
                new HorizontalAnalogCommand(robot.horizontalSlidesSubsystem, horiSlidesManual)
        );
        }
        if (VERTICALSLIDESUBSYSTEM) {
            bucketTransfer_bucketEmpty.whenPressed(VerticalSlidesCommands.bucketToggle(robot));
            //bucketTransfer.whenPressed(VerticalSlidesCommands.BucketTransfer(robot));
            //bucketEmpty.whenPressed(VerticalSlidesCommands.BucketEmpty(robot));
            //bucketIncrement.whenPressed(VerticalSlidesCommands.BucketIncrement(robot));
            //bucketDecrement.whenPressed(VerticalSlidesCommands.BucketDecrement(robot));
            armTransfer_armEmpty.whenPressed(VerticalSlidesCommands.armToggle(robot));
            //armTransfer.whenPressed(VerticalSlidesCommands.ArmTransfer(robot));
            //armEmpty.whenPressed(VerticalSlidesCommands.ArmEmpty(robot));
//            armIncrement.whenPressed(VerticalSlidesCommands.ArmIncrement(robot));
//            armDecrement.whenPressed(VerticalSlidesCommands.ArmDecrement(robot));
            //slidesHigh.whenPressed(VerticalSlidesSequentials.HighBasket(robot));
            slidesLowSequential.whenPressed(VerticalSlidesSequentials.LowBasket(robot));
            //slidesDown.whenPressed(VerticalSlidesSequentials.SlidesDown(robot));
            slidesZero.whenPressed(VerticalSlidesCommands.SlidesZero(robot));
            //wristDecrement.whenPressed(SlidesCommands.wristDecrement(robot));
            //        slidesUpTesting.whenPressed(VerticalSlidesCommands.SlidesUp(robot));
            //        slidesDownTesting.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
        }
    }
}
