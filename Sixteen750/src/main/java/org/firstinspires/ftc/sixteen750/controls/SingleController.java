package org.firstinspires.ftc.sixteen750.controls;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.sixteen750.Robot;
import org.firstinspires.ftc.sixteen750.Setup;
import org.firstinspires.ftc.sixteen750.commands.driving.JoystickDriveCommand;
import org.firstinspires.ftc.sixteen750.commands.driving.NormalModeCommand;
import org.firstinspires.ftc.sixteen750.commands.driving.ResetGyroCommand;
import org.firstinspires.ftc.sixteen750.commands.driving.SnailModeCommand;
import org.firstinspires.ftc.sixteen750.commands.driving.TurboModeCommand;

public class SingleController {

    public Robot robot;
    public Setup setup;
    public CommandGamepad gamepad;
    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, driveStraight, turboButton, snailButton;

    public SingleController(CommandGamepad g, Robot r, Setup s) {
        this.robot = r;
        this.setup = s;
        gamepad = g;

        AssignNamedControllerButton();

        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
    }

    private void AssignNamedControllerButton() {
        //drive buttons
        resetGyroButton = gamepad.rightStickButton;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraight = gamepad.ps_circle;

        turboButton = gamepad.rightBumper;
        snailButton = gamepad.leftBumper;

    }

    public void bindDriveControls() {
        CommandScheduler.scheduleJoystick(
            new JoystickDriveCommand(robot.drivebase, driveLeftStick, driveRightStick)
        );
        turboButton.whenPressed(new TurboModeCommand(robot.drivebase));
        turboButton.whenReleased(new NormalModeCommand(robot.drivebase));
        snailButton.whenPressed(new SnailModeCommand(robot.drivebase));
        snailButton.whenReleased(new NormalModeCommand(robot.drivebase));

        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebase));
    }

}
