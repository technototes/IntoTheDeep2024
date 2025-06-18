package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickIncDecCmd;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton openClaw;
    public CommandButton closeClaw;
    public Stick armStick;
    public CommandButton ArmHorizontal;
    public CommandButton ArmVertical;
    public CommandButton intake;

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
        ArmHorizontal = gamepad.ps_circle;
        ArmVertical = gamepad.ps_triangle;
        intake = gamepad.dpadRight;
    }

    public void BindControls() {
        if (Setup.Connected.CLAWSUBSYSTEM) {
            bindClawSubsystemControls();
        }
    }

    public void bindClawSubsystemControls() {
        openClaw.whenPressed(robot.clawSubsystem::openClaw);
        closeClaw.whenPressed(robot.clawSubsystem::closeClaw);
        ArmVertical.whenPressed(robot.clawSubsystem::setArmVertical);
        ArmHorizontal.whenPressed(robot.clawSubsystem::setArmHorizontal);
        intake.whenPressed(robot.clawSubsystem::setArmIntake);
        CommandScheduler.scheduleJoystick(new JoystickIncDecCmd(robot.clawSubsystem, armStick));
    }
}
