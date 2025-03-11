package org.firstinspires.ftc.sixteen750.commands;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.command.Command;

public class servoThingThatIsWayBetterTHanKeplers {
    //do some cool stuff
    //todo some cool stuff
    public Servo servo;
    public int time = 0;
    public int startPos = 0;
    public int endPos = 0;
    public int rise = 0;
    public int slope = 0;
    public ElapsedTime timer = new ElapsedTime();
    public void newCommandSecondOne(int slope){
        rise = endPos - startPos;
        slope = rise / timer;
    }

    public void startTimer(){
        timer.reset();
    }
    public void theCommand(Servo servo, double startPos, double endPos){
        //todo good stuff
        timer.reset();
        servo.setPosition(startPos);
    }

}
