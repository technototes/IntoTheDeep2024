package org.firstinspires.ftc.hoops.controllers;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import com.technototes.library.logger.Loggable;
import org.firstinspires.ftc.hoops.Robot;
import org.firstinspires.ftc.hoops.commands.AnalogMotorControlCmd;
import org.firstinspires.ftc.hoops.commands.HorizontalAnalogCommand;
import org.firstinspires.ftc.hoops.commands.Test;

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
    public Stick servoAnalog;
    public CommandButton slidesLow;
    public CommandButton slidesHigh;
    public CommandButton slidesDown;

    public CommandButton liftLow, liftMid, liftHigh;

    public CommandAxis trigger;
    public CommandButton threshold;

    public AnalogMotorControlCmd motorMovement;

    public TestController(CommandGamepad g, Robot r) {
        if (g == null) {
            throw new RuntimeException("Null CommandGamepad in TestController");
        }
        if (r == null) {
            throw new RuntimeException("Null robot in TestController");
        }
        this.gamepad = g;
        this.robot = r;
        //        this.liftLow = gamepad.ps_triangle;
        //        this.liftMid = gamepad.ps_cross;
        //        this.liftHigh = gamepad.ps_circle;
        this.servoMax = gamepad.ps_triangle;
        this.servoMin = gamepad.ps_cross;
        this.servoInc = gamepad.dpadUp;
        this.servoDec = gamepad.dpadDown;
        this.servoAnalog = gamepad.leftStick;
        this.slidesLow = gamepad.ps_square;
        this.slidesHigh = gamepad.ps_circle;
        this.slidesDown = gamepad.dpadLeft;
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
        slidesLow.whenPressed(Test.LowBasket(robot));
        slidesHigh.whenPressed(Test.HighBasket(robot));
        slidesDown.whenPressed(Test.SlidesZero(robot));
        bindHorizontalAnalogControls();
    }

    public void bindHorizontalAnalogControls() {
        CommandScheduler.scheduleJoystick(
            new HorizontalAnalogCommand(robot.testsubsystem, servoAnalog)
        );
    }
}
