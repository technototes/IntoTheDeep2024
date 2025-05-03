package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawCmds;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickIncDecCmd;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ClawSubsystem;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public Stick armStick;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
        openClaw = gamepad.leftBumper;
        closeClaw = gamepad.rightBumper;
        armStick = gamepad.rightStick;
    }

    public void BindControls() {
        if (Setup.Connected.CLAWSUBSYSTEM) {
            bindClawSubsystemControls();
        }
    }

    public void bindClawSubsystemControls() {
        openClaw.whenPressed(ClawCmds.cmds.OpenClaw(robot.clawSubsystem));
        closeClaw.whenPressed(ClawCmds.cmds.CloseClaw(robot.clawSubsystem));
        CommandScheduler.scheduleJoystick(new JoystickIncDecCmd(robot.clawSubsystem, armStick));
    }
}
