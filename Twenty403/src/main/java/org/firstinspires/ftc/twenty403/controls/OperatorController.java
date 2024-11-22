package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.Command;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.commands.HangCmd;
import org.firstinspires.ftc.twenty403.commands.KidShampooCmds;

public class OperatorController {

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


    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
       // openRetainer = gamepad.dpadUp;
      //  closeRetainer = gamepad.dpadRight;
     //   slurpIntake = gamepad.leftBumper;
      //  spitIntake = gamepad.rightBumper;
        //temp changing the button below from biteJaw to intake :DD
        //biteJaw = gamepad.ps_cross;
       // releaseJaw = gamepad.ps_triangle;
        ///dumpWrist = gamepad.ps_share;
        //scoopWrist = gamepad.ps_options;
        slideMax = gamepad.ps_share;
        slideMin = gamepad.ps_options;
        // suspend = gamepad.ps_circle;
        armIntake = gamepad.dpadUp;
        //        armLowNet = gamepad.dpadLeft;
        //        armLowSpecimen = gamepad.leftStickButton;
        //        armHighSpecimen = gamepad.rightStickButton;
        armHorizontal = gamepad.leftStickButton;
        armVertical = gamepad.rightStickButton;
        armIncrement = gamepad.ps_circle;
        armDecrement = gamepad.ps_square;
        slideIn = gamepad.dpadDown;
        slideOut = gamepad.dpadLeft;
    }

    public void BindControls() {
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
         //   bindKidShampooControls();
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            bindHangControls();
        }

        if (Setup.Connected.ARMSUBSYSTEM) {
            bindArmControls();
        }
    }

    /*public void bindKidShampooControls() {
        openRetainer.whenPressed(
            Command.create(robot.kidShampooSubsystem::openRetainer, robot.kidShampooSubsystem)
        );
        closeRetainer.whenPressed(
            Command.create(robot.kidShampooSubsystem::closeRetainer, robot.kidShampooSubsystem)
        );
        // eatRetainer.whenPressed(Command.create(robot.kidShampooSubsystem::eatRetainer, robot.kidShampooSubsystem));
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
        slurpIntake.whenPressed(
            Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
        );
        spitIntake.whenReleased(
            Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
        );
        dumpWrist.whenPressed(
            Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
        );
        scoopWrist.whenPressed(
            Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
        );

    }*/

    public void bindArmControls() {
        armIntake.whenPressed(
            Command.create(robot.armSubsystem::setArmToIntake, robot.armSubsystem)
        );
        //        armLowNet.whenPressed(Command.create(robot.armSubsystem::lowBasket, robot.armSubsystem));
        //        armLowSpecimen.whenPressed(Command.create(robot.armSubsystem::lowSpecimen, robot.armSubsystem));
        //        armHighSpecimen.whenPressed(Command.create(robot.armSubsystem::highSpecimen, robot.armSubsystem));
        armHorizontal.whenPressed(
            Command.create(robot.armSubsystem::horizontal, robot.armSubsystem)
        );
        armVertical.whenPressed(Command.create(robot.armSubsystem::vertical, robot.armSubsystem));
        armIncrement.whenPressed(Command.create(robot.armSubsystem::increment, robot.armSubsystem));
        armDecrement.whenPressed(Command.create(robot.armSubsystem::decrement, robot.armSubsystem));
        slideIn.whenPressed(Command.create(robot.armSubsystem::slideDecrement, robot.armSubsystem));
        slideOut.whenPressed(Command.create(robot.armSubsystem::slideIncrement, robot.armSubsystem));
        slideMin.whenPressed(Command.create(robot.armSubsystem::setSlideToZero, robot.armSubsystem));
        slideMax.whenPressed(Command.create(robot.armSubsystem::specimenSlides, robot.armSubsystem));
    }

    public void bindHangControls() {
        //suspend.whenPressed(Command.create(robot.hangSubsystem::suspend, robot.hangSubsystem));

    }
}
