package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.commands.LowBasketCommand;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openRetainer;
    public CommandButton closeRetainer;
    public CommandButton eatRetainer;
    public CommandButton biteJaw;
    public CommandButton releaseJaw;
    public CommandButton slurpIntake;
    public CommandButton spitIntake;
    public CommandButton stopIntake;
    public CommandButton suspend;
    public CommandButton SuspendReverse;
    public CommandButton lowBasket;
    public CommandButton highBasket;
    public CommandButton lowSpecimen;
    public CommandButton highSpecimen;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;

        AssignNamedControllerButton();

        BindControls();
    }

    private void AssignNamedControllerButton() {
        openRetainer = gamepad.dpadUp;
        closeRetainer = gamepad.dpadUp;
        eatRetainer = gamepad.dpadRight;
        slurpIntake = gamepad.leftBumper;
        spitIntake = gamepad.rightBumper;
        biteJaw = gamepad.ps_cross;
        releaseJaw = gamepad.ps_triangle;
        suspend = gamepad.ps_circle;
        lowBasket = gamepad.ps_square;
    }

    public void BindControls() {
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM){
            bindKidShampooControls();
        }
        if (Setup.Connected.HANGSUBSYSTEM){
            bindHangControls();
        }

        if (Setup.Connected.ARMSUBSYSTEM) {
            bindArmControls();
        }
    }
    public void bindKidShampooControls() {
        openRetainer.whilePressed(Command.create(robot.kidShampooSubsystem::openRetainer, robot.kidShampooSubsystem));
        closeRetainer.whileReleased(Command.create(robot.kidShampooSubsystem::closeRetainer, robot.kidShampooSubsystem));
        eatRetainer.whenPressed(Command.create(robot.kidShampooSubsystem::eatRetainer, robot.kidShampooSubsystem));
        biteJaw.whenPressed(Command.create(robot.kidShampooSubsystem::biteJaw, robot.kidShampooSubsystem));
        releaseJaw.whenPressed(Command.create(robot.kidShampooSubsystem::releaseJaw, robot.kidShampooSubsystem));
        slurpIntake.whenPressed(Command.create(robot.kidShampooSubsystem::slurpIntake, robot.kidShampooSubsystem));
        spitIntake.whenPressed(Command.create(robot.kidShampooSubsystem::spitIntake, robot.kidShampooSubsystem));
        slurpIntake.whenReleased(Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem));
        spitIntake.whenReleased(Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem));
    }
    public void bindArmControls() {
        lowBasket.whenPressed(new LowBasketCommand.LowBasket(robot));

    }
    public void bindHangControls() {
        suspend.whenPressed(Command.create(robot.hangSubsystem::suspend, robot.hangSubsystem));

    }
}
