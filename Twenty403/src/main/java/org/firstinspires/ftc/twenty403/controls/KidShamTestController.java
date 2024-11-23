package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;

import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;

public class KidShamTestController {

        public Robot robot;
        public CommandGamepad gamepad;
        public CommandButton openRetainer;
        public CommandButton closeRetainer;
        public CommandButton eatRetainer;
        public CommandButton biteJaw;
        public CommandButton releaseJaw;
        public CommandButton slurpIntake;
        public CommandButton spitIntake;
        public CommandButton stopIntake;
        public CommandButton suspend;
        public CommandButton SuspendReverse;
        public CommandButton armIntake;
        public CommandButton armLowNet;
        public CommandButton armLowSpecimen;
        public CommandButton armHighSpecimen;
        public CommandButton armHorizontal;
        public CommandButton armVertical;
        public CommandButton armIncrement;
        public CommandButton armDecrement;
        public CommandButton dumpWrist;
        public CommandButton scoopWrist;
        public CommandButton slideIn;
        public CommandButton slideOut;
        public CommandButton slideMax;
        public CommandButton slideMin;
        public CommandButton straightWrist;

        public KidShamTestController(CommandGamepad g, Robot r) {
            robot = r;
            gamepad = g;
            AssignNamedControllerButton();
            BindControls();
        }

        private void AssignNamedControllerButton() {
            openRetainer = gamepad.dpadUp;
            closeRetainer = gamepad.dpadRight;
            slurpIntake = gamepad.leftBumper;
            spitIntake = gamepad.rightBumper;
            //temp changing the button below from biteJaw to intake :DD
            biteJaw = gamepad.dpadLeft;
            releaseJaw = gamepad.dpadDown;
            dumpWrist = gamepad.ps_circle;
            scoopWrist = gamepad.ps_square;
            straightWrist = gamepad.ps_cross;
        }

        public void BindControls() {
            if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
                bindKidShampooControls();
            }
        }

        public void bindKidShampooControls() {
            openRetainer.whenPressed(
                    Command.create(robot.kidShampooSubsystem::openRetainer, robot.kidShampooSubsystem)
            );
            closeRetainer.whenPressed(
                    Command.create(robot.kidShampooSubsystem::closeRetainer, robot.kidShampooSubsystem)
            );
            biteJaw.whenPressed(
                    Command.create(robot.kidShampooSubsystem::biteJaw, robot.kidShampooSubsystem)
            );
            releaseJaw.whenPressed(
                    Command.create(robot.kidShampooSubsystem::releaseJaw, robot.kidShampooSubsystem)
            );
            slurpIntake.whenPressed(
                    Command.create(robot.kidShampooSubsystem::slurpIntake, robot.kidShampooSubsystem)
            );
            spitIntake.whenPressed(
                    Command.create(robot.kidShampooSubsystem::spitIntake, robot.kidShampooSubsystem)
            );
            slurpIntake.whenReleased(
                    Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
            );
            spitIntake.whenReleased(
                    Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
            );
            dumpWrist.whenPressed(
                    Command.create(robot.kidShampooSubsystem::dumpWrist, robot.kidShampooSubsystem)
            );
            scoopWrist.whenPressed(
                    Command.create(robot.kidShampooSubsystem::scoopWrist, robot.kidShampooSubsystem)
            );
            straightWrist.whenPressed(
                    Command.create(robot.kidShampooSubsystem::straightWrist, robot.kidShampooSubsystem)
            );
        }
    }


