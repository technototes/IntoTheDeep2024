package org.firstinspires.ftc.ptechnodactyl.commands.lift;

import static org.firstinspires.ftc.ptechnodactyl.RobotState.AllianceHubStrategy.HIGH;
import static org.firstinspires.ftc.ptechnodactyl.RobotState.AllianceHubStrategy.MID;
import static org.firstinspires.ftc.ptechnodactyl.RobotState.getAllianceStrategy;

import android.util.Pair;
import com.technototes.library.command.ChoiceCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.LiftSubsystem;

public class LiftLevelCommand extends ChoiceCommand {

    public LiftLevelCommand(LiftSubsystem ls) {
        super(
            new Pair<>(() -> getAllianceStrategy() == HIGH, new LiftLevel3Command(ls)),
            new Pair<>(() -> getAllianceStrategy() == MID, new LiftLevel2TeleCommand(ls))
        );
    }
}
