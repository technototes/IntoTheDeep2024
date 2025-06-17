package org.firstinspires.ftc.ptechnodactyl;

import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.ARM_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.BRAKE_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.CAP_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.CAROUSEL_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.DRIVE_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.EXTENSION_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.INTAKE_ENABLED;
import static org.firstinspires.ftc.ptechnodactyl.Robot.SubsystemConstants.LIFT_ENABLED;

import com.technototes.library.command.CommandScheduler;
import com.technototes.library.command.SequentialCommandGroup;
import com.technototes.library.command.WaitCommand;
import com.technototes.library.control.CommandAxis;
import com.technototes.library.control.CommandButton;
import com.technototes.library.control.CommandGamepad;
import com.technototes.library.control.CommandInput;
import com.technototes.library.control.Stick;
import com.technototes.library.util.Alliance;
import com.technototes.path.command.ResetGyroCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.DrivingCommands;
import org.firstinspires.ftc.ptechnodactyl.commands.JoystickDriveCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmAllianceCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmInCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.ArmSharedCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.arm.BucketDumpVariableCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapArmTranslateCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapCloseCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapDownCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapOpenCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapOutCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapStoreCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.cap.CapTurretTranslateCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.carousel.CarouselLeftCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.carousel.CarouselRightCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionCollectCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.ExtensionSideCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.extension.TurretTranslateCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.intake.IntakeInCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.intake.IntakeOutCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.intake.IntakeSafeCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftCollectCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftLevel1Command;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftLevelCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftSharedCommand;
import org.firstinspires.ftc.ptechnodactyl.commands.lift.LiftTranslateCommand;
import org.firstinspires.ftc.ptechnodactyl.subsystems.ExtensionSubsystem;

public class Controls {

    public CommandGamepad driverGamepad, codriverGamepad;

    public Robot robot;

    public CommandAxis dumpAxis;
    public CommandInput<?> toIntakeButton;
    public CommandButton sharedHubButton, allianceHubButton;

    public CommandButton liftAdjustUpButton, liftAdjustDownButton, turretAdjustRightButton, turretAdjustLeftButton;

    public CommandButton intakeInButton, intakeOutButton;

    public CommandButton carouselButton, carouselBackButton;

    public CommandButton capUpButton, capDownButton;

    public Stick driveLeftStick, driveRightStick;
    public CommandButton resetGyroButton, brakeButton;

    public CommandButton strategy1Button, strategy2Button;

    public Controls(Robot r, CommandGamepad driver, CommandGamepad codriver) {
        driverGamepad = driver;
        codriverGamepad = codriver;
        robot = r;

        dumpAxis = driverGamepad.leftTrigger.setTriggerThreshold(0.2);
        sharedHubButton = driverGamepad.leftBumper;
        allianceHubButton = driverGamepad.rightBumper;
        toIntakeButton = driverGamepad.rightTrigger.setTriggerThreshold(0.3);

        liftAdjustUpButton = driverGamepad.dpadUp;
        liftAdjustDownButton = driverGamepad.dpadDown;
        turretAdjustRightButton = driverGamepad.dpadRight;
        turretAdjustLeftButton = driverGamepad.dpadLeft;

        intakeInButton = driverGamepad.ps_cross;
        intakeOutButton = driverGamepad.ps_circle;

        carouselButton = driverGamepad.ps_square;
        carouselBackButton = driverGamepad.ps_triangle;

        resetGyroButton = driverGamepad.rightStickButton;
        brakeButton = driverGamepad.leftStickButton;

        driveLeftStick = driverGamepad.leftStick;
        driveRightStick = driverGamepad.rightStick;

        capUpButton = driverGamepad.ps_options;

        strategy1Button = driverGamepad.ps_options;
        strategy2Button = driverGamepad.ps_share;

        RobotState.setStrategy(
            RobotState.AllianceHubStrategy.HIGH,
            RobotState.SharedHubStrategy.OWN
        );

        bindControls();
    }

    public void bindControls() {
        if (LIFT_ENABLED) bindLiftControls();
        if (ARM_ENABLED) bindArmControls();
        if (DRIVE_ENABLED) bindDriveControls();
        if (INTAKE_ENABLED) bindIntakeControls();
        if (CAROUSEL_ENABLED) bindCarouselControls();
        if (CAP_ENABLED) bindCapControls();
        if (EXTENSION_ENABLED) bindExtensionControls();
        if (BRAKE_ENABLED) bindBrakeControls();

        strategy1Button.whenPressed(() -> RobotState.strategy1());
        codriverGamepad.ps_options.whenPressed(() -> RobotState.strategy1());
        strategy2Button.whenPressed(() -> RobotState.strategy2());
        codriverGamepad.ps_share.whenPressed(() -> RobotState.strategy2());
    }

    public void bindBrakeControls() {
        brakeButton.whenPressed(robot.brakeSubsystem::lower);
        CommandScheduler.scheduleJoystick(
            robot.brakeSubsystem::raise,
            () -> driveLeftStick.getDistanceFromCenter() > 0.1 && robot.brakeSubsystem.get()
        );
    }

    public void bindArmControls() {
        dumpAxis.whilePressedOnce(
            new BucketDumpVariableCommand(robot.armSubsystem, dumpAxis).asConditional(
                EXTENSION_ENABLED ? robot.extensionSubsystem::isSlideOut : () -> true
            )
        );
        toIntakeButton.whenPressed(new ArmInCommand(robot.armSubsystem));
        allianceHubButton.whileReleasedOnce(new ArmAllianceCommand(robot.armSubsystem));
        sharedHubButton.whileReleasedOnce(new ArmSharedCommand(robot.armSubsystem));
        intakeInButton.whenPressed(new ArmInCommand(robot.armSubsystem));
    }

    public void bindLiftControls() {
        sharedHubButton.whileReleasedOnce(
            new LiftSharedCommand(robot.liftSubsystem).withTimeout(0.5)
        );
        allianceHubButton.whileReleasedOnce(
            new LiftLevelCommand(robot.liftSubsystem).withTimeout(0.5)
        );
        toIntakeButton.whenPressed(
            new LiftLevel1Command(robot.liftSubsystem)
                .withTimeout(0.5)
                .alongWith(new WaitCommand(0.5))
                .andThen(new LiftCollectCommand(robot.liftSubsystem).withTimeout(0.4))
        );
        liftAdjustUpButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, 50));
        liftAdjustDownButton.whilePressed(new LiftTranslateCommand(robot.liftSubsystem, -50));
    }

    public void bindDriveControls() {
        if (EXTENSION_ENABLED && ARM_ENABLED && LIFT_ENABLED && INTAKE_ENABLED) {
            //            allianceHubButton.whilePressed( new TeleopDepositAllianceCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem).andThen(new TeleopIntakeAllianceWarehouseCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem)));
            //            sharedHubButton.whilePressed(new TeleopDepositSharedCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem).andThen(new TeleopIntakeSharedWarehouseCommand(robot.drivebaseSubsystem, robot.intakeSubsystem, robot.liftSubsystem, robot.armSubsystem, robot.extensionSubsystem)));
        }
        robot.drivebaseSubsystem.setDefaultCommand(
            new JoystickDriveCommand(robot.drivebaseSubsystem, driveLeftStick, driveRightStick)
        );
        codriverGamepad.leftTrigger.whilePressedOnce(
            new JoystickDriveCommand(
                robot.drivebaseSubsystem,
                codriverGamepad.leftStick,
                codriverGamepad.rightStick
            )
        ); //        allianceHubButton.whenPressed(new DriveSpeedCommand(robot.drivebaseSubsystem).cancelUpon(toIntakeButton));
        resetGyroButton.whenPressed(new ResetGyroCommand(robot.drivebaseSubsystem));
    }

    public void bindIntakeControls() {
        if (ARM_ENABLED) {
            toIntakeButton.whenPressed(
                new SequentialCommandGroup(
                    new WaitCommand(0.8),
                    new IntakeSafeCommand(robot.intakeSubsystem),
                    driverGamepad::rumbleBlip,
                    robot.armSubsystem::slightCarry,
                    new WaitCommand(0.2),
                    new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.5)
                ).ignoreCancel()
            );
            intakeInButton.whilePressedContinuous(
                new SequentialCommandGroup(
                    new IntakeSafeCommand(robot.intakeSubsystem),
                    driverGamepad::rumbleBlip,
                    robot.armSubsystem::slightCarry,
                    new WaitCommand(0.2),
                    new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.5)
                ).ignoreCancel()
            );
        } else {
            toIntakeButton.whenPressed(
                new WaitCommand(0.8).andThen(new IntakeSafeCommand(robot.intakeSubsystem))
            );
            intakeInButton.whilePressedContinuous(new IntakeInCommand(robot.intakeSubsystem));
        }
        intakeOutButton.whilePressedOnce(new IntakeOutCommand(robot.intakeSubsystem));
        allianceHubButton.whenReleased(
            new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2)
        );
        sharedHubButton.whenReleased(new IntakeOutCommand(robot.intakeSubsystem).withTimeout(0.2));
    }

    public void bindCarouselControls() {
        carouselButton.whilePressedOnce(
            RobotConstants.getAlliance()
                .selectOf(
                    new CarouselLeftCommand(robot.carouselSubsystem),
                    new CarouselRightCommand(robot.carouselSubsystem)
                )
        );
        carouselBackButton.whilePressedOnce(
            RobotConstants.getAlliance()
                .selectOf(
                    new CarouselRightCommand(robot.carouselSubsystem),
                    new CarouselLeftCommand(robot.carouselSubsystem)
                )
        );
    }

    public void bindCapControls() {
        codriverGamepad.ps_circle.whenPressed(new CapOpenCommand(robot.capSubsystem));
        codriverGamepad.ps_cross.whenPressed(new CapCloseCommand(robot.capSubsystem));
        codriverGamepad.rightBumper.whenPressed(new CapOutCommand(robot.capSubsystem));
        codriverGamepad.leftBumper.whenPressed(new CapStoreCommand(robot.capSubsystem));
        codriverGamepad.rightTrigger.whenPressed(new CapDownCommand(robot.capSubsystem));
        codriverGamepad.dpadLeft.whilePressed(
            new CapTurretTranslateCommand(robot.capSubsystem, -0.02)
        );
        codriverGamepad.dpadRight.whilePressed(
            new CapTurretTranslateCommand(robot.capSubsystem, 0.02)
        );
        codriverGamepad.dpadUp.whilePressed(new CapArmTranslateCommand(robot.capSubsystem, 0.05));
        codriverGamepad.dpadDown.whilePressed(
            new CapArmTranslateCommand(robot.capSubsystem, -0.05)
        );
    }

    public void bindExtensionControls() {
        allianceHubButton.whileReleasedOnce(
            new ExtensionCommand(
                robot.extensionSubsystem,
                ExtensionSubsystem.ExtensionConstants.TELEOP_ALLIANCE,
                ExtensionSubsystem.ExtensionConstants.CENTER
            )
        );
        sharedHubButton.whileReleasedOnce(new ExtensionSideCommand(robot.extensionSubsystem));
        toIntakeButton.whenPressed(new ExtensionCollectCommand(robot.extensionSubsystem));
        turretAdjustLeftButton.whilePressed(
            new TurretTranslateCommand(
                robot.extensionSubsystem,
                0.05,
                () ->
                    DRIVE_ENABLED &&
                    ((RobotConstants.getAlliance() == Alliance.RED) ^
                        (robot.drivebaseSubsystem.getExternalHeading() >
                                ExtensionSubsystem.ExtensionConstants.SNAP_1 &&
                            robot.drivebaseSubsystem.getExternalHeading() <
                            ExtensionSubsystem.ExtensionConstants.SNAP_2))
            )
        );
        turretAdjustRightButton.whilePressed(
            new TurretTranslateCommand(
                robot.extensionSubsystem,
                -0.05,
                () ->
                    DRIVE_ENABLED &&
                    ((RobotConstants.getAlliance() == Alliance.RED) ^
                        (robot.drivebaseSubsystem.getExternalHeading() >
                                ExtensionSubsystem.ExtensionConstants.SNAP_1 &&
                            robot.drivebaseSubsystem.getExternalHeading() <
                            ExtensionSubsystem.ExtensionConstants.SNAP_2))
            )
        );
    }
}
