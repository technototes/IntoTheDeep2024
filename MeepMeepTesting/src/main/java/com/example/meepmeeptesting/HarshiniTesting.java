package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;
import com.acmerobotics.roadrunner.trajectory.constraints.*;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.*;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;
import java.io.*;
import java.util.Arrays;
import java.util.function.*;
import javax.imageio.ImageIO;

public class HarshiniTesting {

    public static class AutoConstants {

        //constants for Into the Deeeeeeeep
        public static Function<Pose2d, TrajectoryBuilder> func;
        public static Pose2d OBSERVATION_START = new Pose2d(0, 60, toRadians(-90));
        public static Pose2d SUBMARINE = new Pose2d(-5, 32, toRadians(90));
        public static Pose2d OBSERVATION_ZONE = new Pose2d(-57, 55, toRadians(120));
        public static Pose2d SUBMARINE2 = new Pose2d(0, 32, toRadians(90));
        public static Pose2d PUSH_HALF = new Pose2d(-16, 35, toRadians(180));

        public static Pose2d PUSH_1 = new Pose2d(-32, 35, toRadians(120));
        public static Pose2d PUSH_2 = new Pose2d(-32, 10, toRadians(90));
        public static Pose2d PUSH_3_AND_A_HALF = new Pose2d(-47, 17, toRadians(270));
        public static Pose2d PUSH_3 = new Pose2d(-35, 10, toRadians(225));
        public static Pose2d SAMPLE_1 = new Pose2d(-48, 26, toRadians(90));
        //public static Pose2d OBSERVATION_ZONE = new Pose2d(-60, 55, toRadians(135));

        //Lines for Into the Deeeeeeeep
        public static final Supplier<Trajectory> OBSERVATION_SIDE_CYCLE = () ->
            func
                .apply(OBSERVATION_START)
                .lineToLinearHeading(SUBMARINE)
                .lineToLinearHeading(OBSERVATION_ZONE)
                .build();
        public static final Supplier<Trajectory> OBSERVATION_TEST1 = () ->
            func.apply(OBSERVATION_START).lineToLinearHeading(SUBMARINE).build();
        public static final Supplier<Trajectory> OBSERVATION_TEST2 = () ->
            func.apply(OBSERVATION_ZONE).lineToLinearHeading(SUBMARINE2).build();
        public static final Supplier<Trajectory> OBSERVATION_TEST3 = () ->
            func.apply(OBSERVATION_ZONE).lineToLinearHeading(SUBMARINE2).build();
        //public static final Supplier<Trajectory> OBSERVATION_TEST4 = () ->
            //func.apply(SUBMARINE).lineToLinearHeading(PUSH_1).build();
        public static final Supplier<Trajectory> OBSERVATION_TEST5 = () ->
            func
                .apply(SUBMARINE)
                    .splineToLinearHeading(PUSH_HALF, 0)
//                    .splineToLinearHeading(PUSH_1, -90)
//                .splineToLinearHeading(PUSH_3, PUSH_3.getHeading())
//                .splineToLinearHeading(PUSH_3_AND_A_HALF, PUSH_3_AND_A_HALF.getHeading())
//                .splineToLinearHeading(SAMPLE_1, Math.PI - SAMPLE_1.getHeading())
//                .splineToLinearHeading(OBSERVATION_ZONE, Math.PI - OBSERVATION_ZONE.getHeading())
                .build();
        public static final Supplier<Trajectory> OBSERVATION_TEST6 = () ->
            func
                .apply(PUSH_2)
                .splineToConstantHeading(PUSH_3.vec(), Math.PI - PUSH_3.getHeading())
                .splineToConstantHeading(
                    OBSERVATION_ZONE.vec(),
                    Math.PI - OBSERVATION_ZONE.getHeading()
                )
                .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST7 = () ->
            func
                .apply(PUSH_3)
                .splineToConstantHeading(
                    OBSERVATION_ZONE.vec(),
                    Math.PI - OBSERVATION_ZONE.getHeading()
                )
                .build();
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
            .trajectorySequenceBuilder(AutoConstants.OBSERVATION_START)
            .addTrajectory(AutoConstants.OBSERVATION_TEST1.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST4.get())
            .addTrajectory(AutoConstants.OBSERVATION_TEST5.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST6.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST7.get())
            .addTrajectory(AutoConstants.OBSERVATION_TEST2.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST3.get())
            .build();
    }
}
