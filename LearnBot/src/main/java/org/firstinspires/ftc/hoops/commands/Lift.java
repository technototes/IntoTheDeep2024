package org.firstinspires.ftc.hoops.commands;

import com.technototes.library.command.Command;
import com.technototes.library.command.MethodCommand;
import org.firstinspires.ftc.hoops.subsystems.PlacementSubsystem;

public class Lift {

    public static Command HighCommand(PlacementSubsystem ps) {
        return new MethodCommand(PlacementSubsystem::liftHeightHigh, ps);
    }

    public static Command MediumCommand(PlacementSubsystem ps) {
        return new MethodCommand(PlacementSubsystem::liftHeightMedium, ps);
    }

    public static Command LowCommand(PlacementSubsystem ps) {
        return new MethodCommand(PlacementSubsystem::liftHeightLow, ps);
    }
}
