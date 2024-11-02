package org.firstinspires.ftc.learnbot.controllers;

import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.logger.Loggable;
import org.firstinspires.ftc.learnbot.Robot;
import org.firstinspires.ftc.learnbot.commands.AnalogMotorControlCmd;
import org.firstinspires.ftc.learnbot.commands.EZCmd;
import org.firstinspires.ftc.learnbot.commands.Test;
import org.firstinspires.ftc.learnbot.subsystems.TestSubsystem;

public class TestController implements Loggable {

    public Robot robot;
    public CommandGamepad gamepad;

    public CommandButton servoleft;
    public CommandButton servoright;
    public CommandAxis motorAxis;
    public CommandButton modeToggle;
    public CommandButton servoMax;
    public CommandButton servoMin;
    public CommandButton servoInc;
    public CommandButton servoDec;

    public CommandButton liftLow, liftMid, liftHigh;

    public CommandAxis trigger;
    public CommandButton threshold;

    public AnalogMotorControlCmd motorMovement;

    public TestController(CommandGamepad g, Robot r) {
        this.gamepad = g;
        this.robot = r;
        //        this.liftLow = gamepad.ps_triangle;
        //        this.liftMid = gamepad.ps_cross;
        //        this.liftHigh = gamepad.ps_circle;
        this.servoMax = gamepad.ps_triangle;
        this.servoMin = gamepad.ps_cross;
        this.servoInc = gamepad.dpadUp;
        this.servoDec = gamepad.dpadDown;
        //        this.servoleft.whenPressed(new ServoLeft(r.test));
        //        this.servoright.whenPressed((new ServoRight(r.test)));
        //this.motorAxis = gamepad.rightStickY;
        this.modeToggle = gamepad.rightStickButton;
        //        this.motorMovement = new MotorMovementCommand(r.test, this.motorAxis);
        //        this.modeToggle.whenPressed(new ToggleMotorStopModeCommand(r.test));
        //        CommandScheduler.scheduleJoystick(motorMovement);
        //this.trigger = gamepad.leftTrigger;
        this.threshold = gamepad.rightTrigger.getAsButton(0.5);
        bindControls();
    }

    public void bindControls() {
        //        liftLow.whenPressed(EZCmd.Placement.LiftLow(robot.placementSubsystem));
        //        liftMid.whenPressed(EZCmd.Placement.LiftMedium(robot.placementSubsystem));
        //        liftHigh.whenPressed(EZCmd.Placement.LiftHigh(robot.placementSubsystem));
        servoMax.whenPressed(Test.ServoMax(robot));
        servoMin.whenPressed(Test.ServoMin(robot));
        servoInc.whenPressed(Test.ServoInc(robot));
        servoDec.whenPressed(Test.ServoDec(robot));
    }
}
