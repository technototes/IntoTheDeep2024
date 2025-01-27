package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;
import com.acmerobotics.roadrunner.trajectory.constraints.*;
import com.rowlandhall.meepmeep.MeepMeep;
import com.rowlandhall.meepmeep.roadrunner.*;
import com.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.rowlandhall.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;
import java.io.*;
import java.util.Arrays;
import java.util.function.*;
import javax.imageio.ImageIO;

public class ViggoTesting {

    public static class AutoConstants {

        public static Function<Pose2d, TrajectoryBuilder> func;
        public static Pose2d TEST1 = new Pose2d(23.3, 60, toRadians(0));
        public static Pose2d TEST2 = new Pose2d(54, 48, toRadians(45));
        public static Pose2d TEST3 = new Pose2d(56, 37, toRadians(90));
        public static Pose2d TEST4 = new Pose2d(56, 50, toRadians(45));
        public static Pose2d TEST5 = new Pose2d(48, 38, toRadians(90));
        public static Pose2d TEST6 = new Pose2d(56, 50, toRadians(45));
        public static Pose2d TEST6a = new Pose2d(58, 26, toRadians(180));
        public static Pose2d TEST6b = new Pose2d(56, 50, toRadians(45));
        public static Pose2d TEST7 = new Pose2d(40, 13, toRadians(-180));
        public static Pose2d TEST8 = new Pose2d(23.6, 11, toRadians(-180));
        public static Pose2d TEST9 = new Pose2d(1, 38, toRadians(-180));
        public static final Supplier<Trajectory> TEST1_TO_TEST2 = () ->
            func.apply(TEST1).splineToSplineHeading(TEST2, Math.PI - TEST2.getHeading()).build();
        public static final Supplier<Trajectory> TEST2_TO_TEST2 = () ->
            func.apply(TEST2).lineToLinearHeading(TEST3).build();
        public static final Supplier<Trajectory> TEST2_TO_TESTB = () ->
            func.apply(TEST3).lineToLinearHeading(TEST4).build();

        public static final Supplier<Trajectory> TESTB_TO_TESTC = () ->
            func.apply(TEST4).lineToLinearHeading(TEST5).build();
        public static final Supplier<Trajectory> TESTC_TO_TESTD = () ->
            func.apply(TEST5).lineToLinearHeading(TEST6).build();
        public static final Supplier<Trajectory> TESTD_TO_TESTE = () ->
            func.apply(TEST6).splineToSplineHeading(TEST6a, Math.PI - TEST6a.getHeading()).build();
        public static final Supplier<Trajectory> TEST2_TO_TEST3 = () ->
            func.apply(TEST6a).lineToLinearHeading(TEST6b).build();
        public static final Supplier<Trajectory> TEST3_TO_TEST4 = () ->
            func.apply(TEST6b).lineToLinearHeading(TEST7).build();
        public static final Supplier<Trajectory> TEST4_TO_TEST5 = () ->
            func.apply(TEST7).lineToLinearHeading(TEST8).build();
    }

    public static void main(String[] args) {
        // Make this as large as possible while still fitting on our laptop screens:
        MeepMeep meepMeep = new MeepMeep(550);
        try {
            // Try to load the field image from the repo:
            meepMeep.setBackground(ImageIO.read(new File("Field.jpg")));
        } catch (IOException io) {
            // If we can't find the field image, fall back to the gray grid
            meepMeep.setBackground(MeepMeep.Background.GRID_GRAY);
        }
        // Constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, trackWidth
        // maxVel: The fastest dist/sec we'll travel (velocity)
        // maxAcc: The fastest rate (dist/sec/sec) we'll change our velocity (acceleration)
        // maxAngVel: the fastest degrees/sec we'll rotate (angular velocity)
        // maxAngAcc: the fastest rate (deg/sec/sec) we'll change our rotation (angular acceleration) @MaxAngleAccel
        // trackWidth: The width of our wheelbase (not clear what this really affects...) @TrackWidth
        MinVelocityConstraint min_vel = new MinVelocityConstraint(
            Arrays.asList(
                new AngularVelocityConstraint(60/* @MaxAngleVelo */),
                new MecanumVelocityConstraint(60/* @MaxVelo */, 14.75/* @TrackWidth */)
            )
        );
        ProfileAccelerationConstraint prof_accel = new ProfileAccelerationConstraint(
            30/* @MaxAccel */
        );
        AutoConstants.func = (Pose2d pose) -> new TrajectoryBuilder(pose, min_vel, prof_accel);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setDimensions(17.5, 16.5)
            .followTrajectorySequence(drive -> getTestTrajectory(drive));
        meepMeep.setBackgroundAlpha(0.75f).addEntity(myBot).start();
    }

    private static TrajectorySequence getTestTrajectory(DriveShim drive) {
        return drive
            .trajectorySequenceBuilder(AutoConstants.TEST1)
            .addTrajectory(AutoConstants.TEST1_TO_TEST2.get())
            .addTrajectory(AutoConstants.TEST2_TO_TEST2.get())
            .addTrajectory(AutoConstants.TEST2_TO_TESTB.get())
            .addTrajectory(AutoConstants.TESTB_TO_TESTC.get())
            .addTrajectory(AutoConstants.TESTC_TO_TESTD.get())
            .addTrajectory(AutoConstants.TESTD_TO_TESTE.get())
            .addTrajectory(AutoConstants.TEST2_TO_TEST3.get())
            .addTrajectory(AutoConstants.TEST3_TO_TEST4.get())
            .addTrajectory(AutoConstants.TEST4_TO_TEST5.get())
            .build();
    }
}
