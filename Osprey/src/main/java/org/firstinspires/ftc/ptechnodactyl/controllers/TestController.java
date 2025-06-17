package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickIncDecCmd;

public class TestController {

    public Robot robot;
    public CommandGamepad gamepad;
    public CommandButton testPower2;
    public CommandButton testPower;
    public CommandButton armHorizontal;
    public Stick armStick;

    public TestController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
        testPower = gamepad.leftBumper;
        testPower2 = gamepad.rightBumper;
        armStick = gamepad.rightStick;
        armHorizontal = gamepad.ps_circle;
    }

    public void BindControls() {
        bindTestControls();
    }

    public void bindTestControls() {
        testPower.whenPressed(robot.clawSubsystem::powIncrement);
        testPower2.whenPressed(robot.clawSubsystem::powDecrement);
        CommandScheduler.scheduleJoystick(new JoystickIncDecCmd(robot.clawSubsystem, armStick));
    }
}
