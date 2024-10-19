package org.firstinspires.ftc.sixteen750.commands.slides.vertical;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystems.VerticalSlidesSubsystem;

public class HighChamber implements Command {
    private VerticalSlidesSubsystem vSlide;

    public HighChamber(VerticalSlidesSubsystem v) {
        vSlide = v;
    }

    @Override
    public void execute() {
        vSlide.SlideChamberHigh();
    }
}
