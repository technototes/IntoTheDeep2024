package org.firstinspires.ftc.ptechnodactyl.commands.carousel;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.ptechnodactyl.subsystems.CarouselSubsystem;

public class CarouselStopCommand implements Command {

    public CarouselSubsystem subsystem;

    public CarouselStopCommand(CarouselSubsystem s) {
        subsystem = s;
        addRequirements(s);
    }

    @Override
    public void execute() {
        subsystem.stop();
    }
}
