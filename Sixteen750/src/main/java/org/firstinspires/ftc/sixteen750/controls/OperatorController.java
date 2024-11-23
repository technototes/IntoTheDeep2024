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
    public Stick horislidesLeftStick;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristDecrement;
    public CommandButton wristIncrement;
    public CommandButton horislidesExtend;
    public CommandButton horislidesRetract;
    //vertical buttons
    public Stick vertslidesLeftStick;
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

    //can we make a command happen when we switch modes? like horizontal retract when vertical is active vice versa
    //can make sequential on the button to retract slides when shifting

    //choice commands
    public CommandButton horizontalSlides_verticalSlides;
    public CommandButton horizontalSlides_verticalSlidesRetract;

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
        wristTransfer_wristPickup = gamepad.dpadLeft;
        //closeClaw = gamepad.rightBumper;
        wristTransfer = gamepad.ps_square;
        wristPickup = gamepad.ps_circle;
        //wristDecrement = gamepad.dpadRight;
        //wristIncrement = gamepad.dpadLeft;
        horislidesLeftStick = gamepad.leftStick;
        //horislidesExtend = gamepad.ps_cross;
        //horislidesRetract = gamepad.ps_square;

        //vertical commands
        //bucketTransfer = gamepad.ps_circle;
        //bucketScore = gamepad.ps_triangle;

        //bucketIncrement = gamepad.dpadDown;
        //bucketDecrement = gamepad.dpadDown;

        //choice commands
        shiftButton = gamepad.ps_share;
        horizontalSlides_verticalSlides = gamepad.ps_triangle;
        //slidesHigh = gamepad.dpadUp;
        slidesLow = gamepad.dpadDown;
        //slidesDown = gamepad.dpadDown;
        slidesZero = gamepad.ps_options;
        extend_retract = gamepad.ps_cross;
        down_high = gamepad.dpadUp;
    }

    //can we toggle between regular buttons ex:open/close claw as one button - kevin will work on that
    private void bindSlidesControls() {
        shiftButton.whenPressed(this::toggleShift); // might use to have a manual and non manual mode
        openClaw_closeClaw.whenPressed(HorizontalSlidesCommands.clawToggle(robot));
        wristTransfer_wristPickup.whenPressed(HorizontalSlidesCommands.wristToggle(robot));
        extend_retract.whenPressed(HorizontalSlidesCommands.slideToggle(robot));
        down_high.whenPressed(VerticalSlidesCommands.slideToggle(robot));
        if (HORIZONTALSLIDESUBSYSTEM){
        //closeClaw.whenPressed(HorizontalSlidesCommands.clawChomp(robot));
//        wristPickup.whenPressed(HorizontalSlidesCommands.wristPickup(robot));
//        wristTransfer.whenPressed(HorizontalSlidesCommands.wristTransfer(robot));
        //wristDecrement.whenPressed(HorizontalSlidesCommands.wristIncrement(robot));//flipped but haven't flipped it in code
        //wristIncrement.whenPressed(HorizontalSlidesCommands.wristDecrement(robot));
        //horislidesExtend.whenPressed(HorizontalSlidesCommands.intake(robot));
        //horislidesRetract.whenPressed(HorizontalSlidesCommands.transferring(robot));

        CommandScheduler.scheduleJoystick(
                new HorizontalAnalogCommand(robot.horizontalSlidesSubsystem, horislidesLeftStick)
        );
        }
        if (VERTICALSLIDESUBSYSTEM) {
        /*bucketTransfer.whenPressed(VerticalSlidesCommands.BucketTransfer(robot));
          bucketScore.whenPressed(VerticalSlidesCommands.BucketEmpty(robot));
          armTransfer.whenPressed(VerticalSlidesCommands.ArmTransfer(robot));
          armScore.whenPressed(VerticalSlidesCommands.ArmScore(robot));*/
            //slidesHigh.whenPressed(VerticalSlidesSequentials.HighBasket(robot));
            slidesLow.whenPressed(VerticalSlidesSequentials.LowBasket(robot));
            //slidesDown.whenPressed(VerticalSlidesSequentials.SlidesDown(robot));
            slidesZero.whenPressed(VerticalSlidesCommands.SlidesZero(robot));
            //wristDecrement.whenPressed(SlidesCommands.wristDecrement(robot));
            //        slidesUpTesting.whenPressed(VerticalSlidesCommands.SlidesUp(robot));
            //        slidesDownTesting.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
        }

        //commented out the choice command thingy for now but we should test this at some point
        /*if (HORIZONTALSLIDESUBSYSTEM && VERTICALSLIDESUBSYSTEM) {
            horizontalSlides_verticalSlides.whenPressed(
                    new ChoiceCommand(
                            new Pair<>(this::notShifted, HorizontalSlidesCommands.intake(robot)),
                            new Pair<>(this::isShifted, VerticalSlidesSequentials.HighBasket(robot))
                    )
            );
            horizontalSlides_verticalSlidesRetract.whenPressed(
                    new ChoiceCommand(
                            new Pair<>(this::notShifted, HorizontalSlidesCommands.transferring(robot)),
                            new Pair<>(this::isShifted, VerticalSlidesSequentials.SlidesDown(robot))
                    )
            );
        }*/

    }




}
