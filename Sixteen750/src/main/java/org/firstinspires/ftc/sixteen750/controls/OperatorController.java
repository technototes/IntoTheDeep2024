package org.firstinspires.ftc.sixteen750.controls;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.driving.JoystickDriveCommand;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalAnalogCommand;
import org.firstinspires.ftc.sixteen750.commands.slides.HorizontalSlidesCommands;
import org.firstinspires.ftc.sixteen750.commands.slides.VerticalSlidesCommands;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton shiftButton;

    public CommandButton openClaw_closeClaw;


    //horizontal buttons
    public Stick horislidesLeftStick;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public CommandButton wristPickup;
    public CommandButton wristTransfer;
    public CommandButton wristIncrement;
    public CommandButton wristDecrement;
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

    public CommandButton openClaw_bucketLow;

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
        BindButtons();
    }

    private void AssignNamedControllerButton() {
        shiftButton = gamepad.ps_options;
        openClaw_bucketLow = gamepad.leftBumper;
        closeClaw = gamepad.rightBumper;
        wristTransfer = gamepad.ps_triangle;
        wristPickup = gamepad.ps_circle;
        wristIncrement = gamepad.dpadRight;
        wristDecrement = gamepad.dpadLeft;
        horislidesLeftStick = gamepad.leftStick;
        horislidesExtend = gamepad.ps_cross;
        horislidesRetract = gamepad.ps_square;

        //vertical commands
        bucketTransfer = gamepad.ps_circle;
        bucketScore = gamepad.ps_triangle;
        armTransfer = gamepad.ps_cross;
        armScore = gamepad.ps_square;
        slidesHigh = gamepad.ps_share;
        slidesLow = gamepad.dpadUp;
        slidesDown = gamepad.dpadDown;
        //bucketIncrement = gamepad.dpadDown;
        //bucketDecrement = gamepad.dpadDown;

        //choice commands
        shiftButton = gamepad.ps_share;
        horizontalSlides_verticalSlides = gamepad.ps_triangle;
        slidesHigh = gamepad.dpadUp;
        slidesLow = gamepad.ps_square;
        slidesDown = gamepad.rightStickButton;
        slidesZero = gamepad.ps_options;
    }

    private void BindButtons() {
        // Remember to only bind buttons for attached subsystems
        if (Setup.Connected.HORIZONTALSLIDESUBSYSTEM) {
            bindHorizontalSlidesControls();
            bindHorizontalAnalogControls();
        }

        if (Setup.Connected.VERTICALSLIDESUBSYSTEM) {
            bindVerticalSlidesControls();
        }
        shiftButton.whenPressed(this::toggleShift);
    }
//can we toggle between regular buttons ex:open/close claw as one button - kevin will work on that
    private void bindHorizontalSlidesControls() {
        shiftButton.whenPressed(this::toggleShift);
        horizontalSlides_verticalSlides.whenPressed(
            new ChoiceCommand(
                new Pair<>(this::notShifted, HorizontalSlidesCommands.horizontalExtend(robot)),
                new Pair<>(this::isShifted, VerticalSlidesCommands.HighBasket(robot))
            )
        );
        closeClaw.whenPressed(HorizontalSlidesCommands.clawChomp(robot));
        wristPickup.whenPressed(HorizontalSlidesCommands.wristPickup(robot));
        wristTransfer.whenPressed(HorizontalSlidesCommands.wristTransfer(robot));
        wristIncrement.whenPressed(HorizontalSlidesCommands.wristIncrement(robot));
        wristDecrement.whenPressed(HorizontalSlidesCommands.wristDecrement(robot));
        horislidesExtend.whenPressed(HorizontalSlidesCommands.intake(robot));
        horislidesRetract.whenPressed(HorizontalSlidesCommands.transferring(robot));
    }
    private void bindVerticalSlidesControls() {
          bucketTransfer.whenPressed(VerticalSlidesCommands.BucketTransfer(robot));
          bucketScore.whenPressed(VerticalSlidesCommands.BucketEmpty(robot));
          armTransfer.whenPressed(VerticalSlidesCommands.ArmTransfer(robot));
          armScore.whenPressed(VerticalSlidesCommands.ArmScore(robot));
        slidesHigh.whenPressed(VerticalSlidesCommands.HighBasket(robot));
        slidesLow.whenPressed(VerticalSlidesCommands.LowBasket(robot));
        slidesDown.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
        slidesZero.whenPressed(VerticalSlidesCommands.SlidesZero(robot));
        //wristDecrement.whenPressed(SlidesCommands.wristDecrement(robot));
//        slidesUpTesting.whenPressed(VerticalSlidesCommands.SlidesUp(robot));
//        slidesDownTesting.whenPressed(VerticalSlidesCommands.SlidesDown(robot));
    }

    public void bindHorizontalAnalogControls() {
        CommandScheduler.scheduleJoystick(
            new HorizontalAnalogCommand(robot.horizontalSlidesSubsystem, horislidesLeftStick)
        );
    }
}
