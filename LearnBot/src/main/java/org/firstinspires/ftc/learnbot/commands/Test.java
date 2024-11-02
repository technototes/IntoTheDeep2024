package org.firstinspires.ftc.learnbot.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.MethodCommand;
import org.firstinspires.ftc.learnbot.Robot;
import org.firstinspires.ftc.learnbot.subsystems.TestSubsystem;

public class Test {

    public static Command ServoMax(Robot r) {
        return Command.create(r.testsubsystem::servoMaxPos, r.testsubsystem);
    }

    public static Command ServoMin(Robot r) {
        return Command.create(r.testsubsystem::servoMinPos, r.testsubsystem);
    }

    public static Command ServoInc(Robot r) {
        return Command.create(r.testsubsystem::servoIncrement, r.testsubsystem);
    }

    public static Command ServoDec(Robot r) {
        return Command.create(r.testsubsystem::servoDecrement, r.testsubsystem);
    }

    public static Command ServoLeft(TestSubsystem ts) {
        return new MethodCommand(TestSubsystem::servoLeft, ts);
    }

    public static Command ServoRight(TestSubsystem ts) {
        return new MethodCommand(TestSubsystem::servoRight, ts);
    }

    public static Command MotorForward(TestSubsystem ts) {
        return new MethodCommand(TestSubsystem::forwardSpinning, ts);
    }

    public static Command MotorBackward(TestSubsystem ts) {
        return new MethodCommand(TestSubsystem::backwardSpinning, ts);
    }

    public static Command MotorStop(TestSubsystem ts) {
        return new MethodCommand(TestSubsystem::stopSpinning, ts);
    }
}
