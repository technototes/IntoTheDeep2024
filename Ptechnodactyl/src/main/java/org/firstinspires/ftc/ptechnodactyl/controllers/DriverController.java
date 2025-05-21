package org.firstinspires.ftc.ptechnodactyl.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.ptechnodactyl.Robot;
import org.firstinspires.ftc.ptechnodactyl.Setup;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawCmds;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawDownCmd;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawDpadLCmd;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawDpadRCmd;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawDpadUCmd;
import org.firstinspires.ftc.ptechnodactyl.commands.ClawNeutralCmd;
import org.firstinspires.ftc.ptechnodactyl.commands.DrivingCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickDriveCommand;

public class DriverController {

    public Robot robot;
    public CommandGamepad gamepad;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, turboButton, snailButton;
    public CommandButton override;
    public CommandAxis driveStraighten;
    public CommandAxis drive45;
    public CommandButton openClamp;
    public CommandButton closeClamp;
    public CommandButton clawOpen;
    public CommandButton clawNeutral;
    public CommandButton clawDpadR;
    public CommandButton clawDpadL;
    public CommandButton clawDpadU;

    public DriverController(CommandGamepad g, Robot r) {
        this.robot = r;
        gamepad = g;
        override = g.leftTrigger.getAsButton(0.5);

        AssignNamedControllerButton();
        if (Setup.Connected.DRIVEBASE) {
            bindDriveControls();
        }
        if (Setup.Connected.CLAWSUBSYSTEM) {
            bindClawSubsystemControls();
        }
    }

    private void AssignNamedControllerButton() {
        resetGyroButton = gamepad.ps_options;
        driveLeftStick = gamepad.leftStick;
        driveRightStick = gamepad.rightStick;
        driveStraighten = gamepad.rightTrigger;
        drive45 = gamepad.leftTrigger;
        turboButton = gamepad.leftBumper;
        snailButton = gamepad.rightBumper;
        openClamp = gamepad.ps_square;
        closeClamp = gamepad.ps_circle;
        clawOpen = gamepad.ps_triangle;
        clawNeutral = gamepad.ps_cross;
        clawDpadL = gamepad.dpadLeft;
        clawDpadR = gamepad.dpadRight;
        clawDpadU = gamepad.dpadUp;
    }

    public void bindDriveControls() {
        CommandScheduler.scheduleJoystick(
            new JoystickDriveCommand(
                robot.drivebaseSubsystem,
                driveLeftStick,
                driveRightStick,
                driveStraighten,
                drive45
            )
        );

        turboButton.whenPressed(DrivingCommands.TurboDriving(robot.drivebaseSubsystem));
        turboButton.whenReleased(DrivingCommands.NormalDriving(robot.drivebaseSubsystem));
        snailButton.whenPressed(DrivingCommands.SnailDriving(robot.drivebaseSubsystem));
        snailButton.whenReleased(DrivingCommands.NormalDriving(robot.drivebaseSubsystem));
        resetGyroButton.whenPressed(DrivingCommands.ResetGyro(robot.drivebaseSubsystem));
    }

    public void bindClawSubsystemControls() {
        openClamp.whenPressed(ClawCmds.cmds.OpenClamp(robot.clawSubsystem));
        closeClamp.whenPressed(ClawCmds.cmds.CloseClamp(robot.clawSubsystem));
        clawOpen.whenPressed(ClawDownCmd.ClawDown(robot));
        clawNeutral.whenPressed(ClawNeutralCmd.ClawNeutral(robot));
        clawDpadU.whenPressed(ClawDpadUCmd.ClawDpadU(robot));
        clawDpadR.whenPressed(ClawDpadRCmd.ClawDpadR(robot));
        clawDpadL.whenPressed(ClawDpadLCmd.ClawDpadL(robot));
        //        CommandScheduler.scheduleJoystick(new JoystickIncDecCmd(robot.clawSubsystem, armStick));
    }
}
