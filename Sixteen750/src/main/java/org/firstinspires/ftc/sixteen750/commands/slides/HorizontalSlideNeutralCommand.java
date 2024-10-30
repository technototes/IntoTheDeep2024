package org.firstinspires.ftc.sixteen750.commands.slides;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.sixteen750.subsystems.HorizontalSlidesSubsystem;

public class HorizontalSlideNeutralCommand implements Command {
    private HorizontalSlidesSubsystem subsystem;

    public HorizontalSlideNeutralCommand(HorizontalSlidesSubsystem n){
        subsystem = n;
        addRequirements(n);
    }

    @Override
    public void execute(){
        subsystem.slidesin();
        subsystem.ClawServoChomp();
        subsystem.ClawWristServoPickup();
    }
}
