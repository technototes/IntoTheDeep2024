package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.sixteen750.subsystems.VerticalSlidesSubsystem;

public class SimpleSlidesCommands {

    public static Command HighBasket(VerticalSlidesSubsystem vs) {
        Command.create(vs::SlideBucketHigh, vs);
    }

    public static Command LowBasket(VerticalSlidesSubsystem vs) {
        Command.create(vs::SlideBucketLow, vs);
    }

    public static Command HighChamber(VerticalSlidesSubsystem vs) {
        Command.create(vs::SlideChamberHigh, vs);
    }

    public static Command LowChamber(VerticalSlidesSubsystem vs) {
        Command.create(vs::SlideChamberLow, vs);
    }

    public static Command LiftIntake(VerticalSlidesSubsystem vs) {
        Command.create(vs::LiftHeightIntake, vs);
    }
    //transfer
    //inc/dec

}
