package org.firstinspires.ftc.sixteen750.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.command.Command;
import com.technototes.library.hardware.servo.Servo;

public class ServoRateCommand implements Command {

    private Servo servo;
    private double time = 0;
    private double startPos = 0;
    private double endPos = 0;
    private ElapsedTime timer = new ElapsedTime();

    public boolean isFinished() {
        timer.time();
    }

    public void initialize() {
        timer.reset();
        servo.setPosition(startPos);
    }

    public void ServoRateCommand(Servo servo, double time, double startPos, double endPos) {
        this.endPos = endPos;
        this.servo = servo;
        this.time = time;
        this.startPos = startPos;
    }
}
