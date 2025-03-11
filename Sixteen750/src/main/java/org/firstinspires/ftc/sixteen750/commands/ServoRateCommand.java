package org.firstinspires.ftc.sixteen750.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.command.Command;
import com.technototes.library.hardware.servo.Servo;

public class ServoRateCommand implements Command{

    private Servo servo;
    private double time;
    private double startPos;
    private double endPos;

    public ServoRateCommand(Servo s, double seconds, double start, double end) {
        servo = s;
        time = seconds;
        startPos = start;
        endPos = end;


    }
    public void initialize(){
        ElapsedTime timer = new ElapsedTime();
        servo.setPosition(startPos);
        timer.reset();
    };

    public void execute(){

    };
    public boolean isFinished(){
    return true;
    };
}
}

