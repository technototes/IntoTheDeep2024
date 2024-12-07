package org.firstinspires.ftc.twenty403.controls;

import com.technototes.library.command.Command;
import com.technototes.library.command.CommandScheduler;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.Stick;
import org.firstinspires.ftc.twenty403.Robot;
import org.firstinspires.ftc.twenty403.Setup;
import org.firstinspires.ftc.twenty403.commands.EZCmd;
import org.firstinspires.ftc.twenty403.commands.HangCmd;
import org.firstinspires.ftc.twenty403.commands.HighBasketCommand;
import org.firstinspires.ftc.twenty403.commands.HighSpecimenCommand;
import org.firstinspires.ftc.twenty403.commands.IntakeSampleCommand;
import org.firstinspires.ftc.twenty403.commands.IntakeSpecimenCommand;
import org.firstinspires.ftc.twenty403.commands.JoystickIncDecCommand;
import org.firstinspires.ftc.twenty403.commands.JoystickSlideIncDecCommand;
import org.firstinspires.ftc.twenty403.commands.KidShampooCmds;
import org.firstinspires.ftc.twenty403.commands.LowBasketCommand;
import org.firstinspires.ftc.twenty403.commands.LowSpecimenCommand;
import org.firstinspires.ftc.twenty403.commands.driving.JoystickDriveCommand;

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
    public CommandButton armLowNet;
    public CommandButton armLowSpecimen;
    public CommandButton armHighSpecimen;
    public CommandButton straightWrist;
    public CommandButton armHorizontal;
    public CommandButton armVertical;
    public CommandButton dumpWrist;
    public CommandButton scoopWrist;
    public CommandButton slideIn;
    public CommandButton slideOut;
    public CommandButton slideMax;
    public CommandButton slideMin;
    public Stick armStick;
    public Stick slideStick;
    public CommandButton HighBasket;
    public CommandButton IntakeSample;
    public CommandButton IntakeSpecimen;

    public OperatorController(CommandGamepad g, Robot r) {
        robot = r;
        gamepad = g;
        AssignNamedControllerButton();
        BindControls();
    }

    private void AssignNamedControllerButton() {
        openRetainer = gamepad.dpadRight;
        closeRetainer = gamepad.dpadLeft;
        slurpIntake = gamepad.leftBumper;
        spitIntake = gamepad.rightBumper;
        //temp changing the button below from biteJaw to intake :DD
        /*biteJaw = gamepad.ps_cross;
       releaseJaw = gamepad.ps_triangle;*/
        dumpWrist = gamepad.ps_triangle;
        scoopWrist = gamepad.ps_cross;
        slideMax = gamepad.ps_share;
        slideMin = gamepad.ps_options;
        // suspend = gamepad.ps_circle;

        //        armLowNet = gamepad.dpadLeft;
        //        armLowSpecimen = gamepad.leftStickButton;
        //        armHighSpecimen = gamepad.rightStickButton;

        straightWrist = gamepad.dpadUp;
        armStick = gamepad.rightStick;
        slideStick = gamepad.leftStick;
        /*HighBasket = gamepad.ps_circle;
        armHighSpecimen = gamepad.ps_triangle;
        armLowSpecimen = gamepad.ps_square;
        armLowNet = gamepad.ps_cross;
        IntakeSample = gamepad.ps_share;
        IntakeSpecimen = gamepad.ps_options;*/
    }

    public void BindControls() {
        if (Setup.Connected.KIDSSHAMPOOSUBSYSTEM) {
            bindKidShampooControls();
        }
        if (Setup.Connected.HANGSUBSYSTEM) {
            //bindHangControls();
        }

        if (Setup.Connected.ARMSUBSYSTEM) {
            bindArmControls();
        }
    }

    public void bindKidShampooControls() {
        openRetainer.whenPressed(
            Command.create(robot.kidShampooSubsystem::openRetainer, robot.kidShampooSubsystem)
        );
        closeRetainer.whenPressed(
            Command.create(robot.kidShampooSubsystem::closeRetainer, robot.kidShampooSubsystem)
        );
        // eatRetainer.whenPressed(Command.create(robot.kidShampooSubsystem::eatRetainer, robot.kidShampooSubsystem));
        /*biteJaw.whenPressed(
            Command.create(robot.kidShampooSubsystem::biteJaw, robot.kidShampooSubsystem)
        );
        releaseJaw.whenPressed(
            Command.create(robot.kidShampooSubsystem::releaseJaw, robot.kidShampooSubsystem)
        );*/
        slurpIntake.whenPressed(
            Command.create(robot.kidShampooSubsystem::slurpIntake, robot.kidShampooSubsystem)
        );
        slurpIntake.whenReleased(
            Command.create(robot.kidShampooSubsystem::stopIntake, robot.kidShampooSubsystem)
        );
        spitIntake.whenPressed(
            Command.create(robot.kidShampooSubsystem::spitIntake, robot.kidShampooSubsystem)
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

    public void bindArmControls() {
        /*armLowNet.whenPressed(LowBasketCommand.LowBasket(robot));
        armLowSpecimen.whenPressed(LowSpecimenCommand.LowSpecimen(robot));
        armHighSpecimen.whenPressed(HighSpecimenCommand.HighSpecimen(robot));
        HighBasket.whenPressed(HighBasketCommand.HighBasket(robot));
        IntakeSample.whenPressed(IntakeSampleCommand.IntakeSample(robot));
        IntakeSpecimen.whenPressed(IntakeSpecimenCommand.IntakeSpecimen(robot));*/

        /*armHorizontal.whenPressed(
            Command.create(robot.armSubsystem::horizontal, robot.armSubsystem)
        );
        armVertical.whenPressed(Command.create(robot.armSubsystem::vertical, robot.armSubsystem));*/
        // slideIn.whenPressed(Command.create(robot.armSubsystem::slideDecrement, robot.armSubsystem));
        // slideOut.whenPressed(Command.create(robot.armSubsystem::slideIncrement, robot.armSubsystem));
        // slideMin.whenPressed(Command.create(robot.armSubsystem::setSlideToZero, robot.armSubsystem));
        //slideMax.whenPressed(Command.create(robot.armSubsystem::slideSpecimen, robot.armSubsystem));*/
        CommandScheduler.scheduleJoystick(new JoystickIncDecCommand(robot.armSubsystem, armStick));

        CommandScheduler.scheduleJoystick(
            new JoystickSlideIncDecCommand(robot.armSubsystem, slideStick)
        );
    }

    public void bindHangControls() {
        //suspend.whenPressed(Command.create(robot.hangSubsystem::suspend, robot.hangSubsystem));

    }
}
