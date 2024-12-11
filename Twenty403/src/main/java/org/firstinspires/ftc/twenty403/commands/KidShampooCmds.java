package org.firstinspires.ftc.twenty403.commands;

import com.technototes.library.command.Command;
import org.firstinspires.ftc.twenty403.subsystems.KidShampooSubsystem;

public class KidShampooCmds {

    public static class cmds {

        public static Command OpenRetainer(KidShampooSubsystem KS) {
            return Command.create(KS::openRetainer);
        }

        public static Command CloseRetainer(KidShampooSubsystem KS) {
            return Command.create(KS::closeRetainer);
        }

        // public static Command EatRetainer(KidShampooSubsystem KS){
        //     return Command.create(KS::eatRetainer);
        // }
        public static Command BiteJaw(KidShampooSubsystem KS) {
            return Command.create(KS::biteJaw);
        }

        public static Command ReleaseJaw(KidShampooSubsystem KS) {
            return Command.create(KS::releaseJaw);
        }

        public static Command SlurpIntake(KidShampooSubsystem KS) {
            return Command.create(KS::slurpIntake);
        }

        public static Command SpitIntake(KidShampooSubsystem KS) {
            return Command.create(KS::spitIntake);
        }

        public static Command StopIntake(KidShampooSubsystem KS) {
            return Command.create(KS::stopIntake);
        }
        public static Command DumpWrist(KidShampooSubsystem KS) {
            return Command.create(KS::dumpWrist);
        }
        public static Command ScoopWrist(KidShampooSubsystem KS) {
            return Command.create(KS::scoopWrist);
        }
    }
}
