package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;

public class OperatorController {

    public Robot robot;
    public CommandGamepad gamepad;

    public OperatorController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;

        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
    }

    public void BindControls() {
    }
}
