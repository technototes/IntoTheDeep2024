package org.firstinspires.ftc.hoops.imutesting;

import com.technototes.library.command.Command;

public class ImuCommand implements Command {

    public ImuCommand(ImuOnlySubsystem ss) {
        addRequirements(ss);
    }

    @Override
    public void execute() {}
}
