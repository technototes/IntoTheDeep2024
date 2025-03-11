package org.firstinspires.ftc.sixteen750.commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.command.Command;
import com.technototes.library.hardware.servo.Servo;

public class ServoRateCommand implements Command{
    //implements = the command looks like an interface
    //extends = the command is the class [is an extension of that class]

    private Servo servo; //technototes servo not the qualcomm one
    private double time = 0;
    private double startPos = 0;
    private double endPos = 0;
    ElapsedTime timer = new ElapsedTime();


    public ServoRateCommand(Servo s, double seconds, double start, double end){
        servo = s;
        time = seconds;
        startPos = start;
        endPos = end;
    }

    public void initialize(){

        servo.setPosition(startPos);
        timer.reset(); //it resets the timer so that it can start timing accurately
    };

    public void execute(){
        double x = timer.time();
        double m = (endPos - startPos) / time;
        double b = startPos;
    servo.setPosition(m*x + b);
    //slope intercept form (m = rise/run)
    };

    public boolean isFinished(){
        return timer.time() >= time;
        //if the timer is greater or equal to the totaltime
    };

    //init command
    //start a timer
    //set servo position (start pos)

}
