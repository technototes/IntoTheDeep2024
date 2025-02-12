package org.firstinspires.ftc.hoops.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.Setup;
import org.firstinspires.ftc.hoops.commands.EZCmd;
import org.firstinspires.ftc.hoops.commands.JoystickDriveCommand;
import org.firstinspires.ftc.hoops.commands.LaunchCommands;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton launch;

    public OperatorController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();
        if (Setup.Connected.LAUNCHER) {
            bindLaunchControls();
        }
    }

    private void AssignNamedControllerButton() {
        launch = gamepad.ps_square;
    }

    public void bindLaunchControls() {
        launch.whenPressed(LaunchCommands.launchCommand(robot));
        launch.whenReleased(LaunchCommands.stopLaunchCommand(robot));
    }
}
