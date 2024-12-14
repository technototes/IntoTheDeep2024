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
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesSequentials;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesSequentials;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton shiftButton;

    //horizontal buttons
    public Stick horiSlidesManual;

    public CommandButton pickup;

    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton smallClose;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristDecrement;
    public CommandButton wristIncrement;
    public CommandButton wristZero;
    public CommandButton horiSlidesExtend;
    public CommandButton horiSlidesSmallExtend;
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
        //horizontal
        openClaw_closeClaw = gamepad.rightBumper;
        smallClose = gamepad.leftBumper;
        wristTransfer_wristPickup = gamepad.ps_options;
        horiSlidesExtend = gamepad.ps_triangle;
        horiSlidesSmallExtend = gamepad.ps_square;
        horiSlidesManual = gamepad.rightStick;
        //extend_retract = gamepad.ps_cross;
        horislidesRetract = gamepad.ps_cross;
        //vertical
        bucketTransfer_bucketEmpty = gamepad.ps_circle;
        armTransfer_armEmpty = gamepad.leftStickButton;
        slidesHighSequential = gamepad.dpadUp;
        slidesLowSequential = gamepad.dpadDown;
        //slidesDownSequential = gamepad.dpadDown;
        //slidesZero = gamepad.ps_share;
        //down_high = gamepad.dpadDown;
    }

    private void bindSlidesControls() {
        /*shiftButton.whenPressed(this::toggleShift); // might use to have a manual and non manual mode*/

        if (HORIZONTALSLIDESUBSYSTEM) {
            //extend_retract.whenPressed(HorizontalSlidesCommands.horiSlideToggle(robot));
            horiSlidesExtend.whenPressed(HorizontalSlidesSequentials.intake(robot));
            horislidesRetract.whenPressed(HorizontalSlidesSequentials.retract(robot));
            openClaw_closeClaw.whenPressed(HorizontalSlidesCommands.clawToggle(robot));
            smallClose.whenPressed(HorizontalSlidesCommands.smallClose(robot));
            wristTransfer_wristPickup.whenPressed(HorizontalSlidesCommands.wristToggle(robot));
            horiSlidesSmallExtend.whenPressed(HorizontalSlidesSequentials.intakeSmall(robot));
            CommandScheduler.scheduleJoystick(
                new HorizontalAnalogCommand(robot.horizontalSlidesSubsystem, horiSlidesManual)
            );
        }
        if (VERTICALSLIDESUBSYSTEM) {
            //down_high.whenPressed(VerticalSlidesCommands.vertSlideToggle(robot));
            //slidesDownSequential.whenPressed(VerticalSlidesSequentials.SlidesDown(robot));
            bucketTransfer_bucketEmpty.whenPressed(VerticalSlidesCommands.bucketToggle(robot));
            armTransfer_armEmpty.whenPressed(VerticalSlidesCommands.armToggle(robot));
            //armIncrement.whenPressed(VerticalSlidesCommands.ArmIncrement(robot));
            //armDecrement.whenPressed(VerticalSlidesCommands.ArmDecrement(robot));
            slidesHighSequential.whenPressed(VerticalSlidesSequentials.HighBasket(robot));
            slidesLowSequential.whenPressed(VerticalSlidesSequentials.LowBasket(robot));
            //slidesZero.whenPressed(VerticalSlidesCommands.SlidesZero(robot));
            //slidesUpTesting.whenPressed(VerticalSlidesCommands.SlidesUp(robot));
            //slidesDownTesting.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
        }
    }
}
