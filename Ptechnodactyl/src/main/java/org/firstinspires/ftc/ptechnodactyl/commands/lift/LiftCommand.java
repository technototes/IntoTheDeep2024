package org.firstinspires.ftc.ptechnodactyl.commands.lift;

import com.technototes.library.command.Command;
import java.util.function.DoubleSupplier;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class LiftCommand implements Command {

    public LiftSubsystem liftSys;
    public DoubleSupplier doubleSupplier;

    public LiftCommand(LiftSubsystem ls, DoubleSupplier ds) {
        liftSys = ls;
        doubleSupplier = ds;
        addRequirements(ls);
    }

    public LiftCommand(LiftSubsystem ls, double ds) {
        this(ls, () -> ds);
    }

    @Override
    public void initialize() {
        liftSys.setLiftPosition(doubleSupplier.getAsDouble());
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return liftSys.isAtTarget();
    }
}
