package org.firstinspires.ftc.learnbot.imutesting;

import com.technototes.library.command.Command;

public class ImuCommand implements Command {

    public ImuCommand(ImuOnlySubsystem ss) {
        addRequirements(ss);
    }

    @Override
    public void execute() {}
}
