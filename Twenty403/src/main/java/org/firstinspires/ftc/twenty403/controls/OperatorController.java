package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.commands.HangCmd;
import org.firstinspires.ftc.twenty403.commands.KidShampooCmds;

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
        suspend = gamepad.dpadLeft;
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
        openRetainer.whenPressed(KidShampooCmds.cmds.OpenRetainer(robot.kidShampooSubsystem));
        closeRetainer.whenPressed(KidShampooCmds.cmds.CloseRetainer(robot.kidShampooSubsystem));
        // eatRetainer.whenPressed(KidShampooCmds.cmds.EatRetainer(robot.kidShampooSubsystem));
        biteJaw.whenPressed(KidShampooCmds.cmds.BiteJaw(robot.kidShampooSubsystem));
        releaseJaw.whenPressed(KidShampooCmds.cmds.ReleaseJaw(robot.kidShampooSubsystem));
        slurpIntake.whenPressed(KidShampooCmds.cmds.SlurpIntake(robot.kidShampooSubsystem));
        spitIntake.whenPressed(KidShampooCmds.cmds.SpitIntake(robot.kidShampooSubsystem));
        slurpIntake.whenReleased(KidShampooCmds.cmds.StopIntake(robot.kidShampooSubsystem));
        spitIntake.whenReleased(KidShampooCmds.cmds.StopIntake(robot.kidShampooSubsystem));
    }
    public void bindArmControls() {
        //lowBasket.whenPressed(new LowBasketCommand.LowBasket(robot));

    }
    public void bindHangControls() {
        suspend.whenPressed(HangCmd.hang.Suspend(robot.hangSubsystem));
    }
}
