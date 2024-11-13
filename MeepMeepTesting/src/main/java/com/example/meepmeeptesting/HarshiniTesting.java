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
        public static Pose2d SUBMARINE = new Pose2d(-5, 36, toRadians(90));
        public static Pose2d OBSERVATION_ZONE = new Pose2d(-46, 55, toRadians(90));
        public static Pose2d SUBMARINE2 = new Pose2d(0, 36, toRadians(90));
        public static Pose2d SUBMARINE3 = new Pose2d(5, 36, toRadians(-90));
        public static Pose2d MINI_LINE = new Pose2d(-3, 36, toRadians(90));

        public static Pose2d PUSH_HALF = new Pose2d(-16, 36, toRadians(90));

        public static Pose2d PUSH_1 = new Pose2d(-34, 45, toRadians(90));
        public static Pose2d PUSH_2 = new Pose2d(-34, 10, toRadians(90));
        public static Pose2d PUSH_3_AND_A_HALF = new Pose2d(-46, 11, toRadians(90));
        public static Pose2d PUSH_3 = new Pose2d(-35, 10, toRadians(90));
        public static Pose2d PUSH_4 = new Pose2d(-56, 10, toRadians(90));
        public static Pose2d OBSERVATION_ZONE_2 = new Pose2d(-56, 55, toRadians(90));
        public static Pose2d PUSH_5 = new Pose2d(-61, 10, toRadians(90));
        public static Pose2d OBSERVATION_ZONE_3 = new Pose2d(-61, 55, toRadians(90));

        public static Pose2d SAMPLE_1 = new Pose2d(-48, 26, toRadians(90));
        public static Pose2d OBSERVATION_PUSH_HALF = new Pose2d(-47, 40, toRadians(90));

        //forward backward constants

        public static Pose2d FORWARD = new Pose2d(0,48, toRadians(90));
        public static Pose2d REST = new Pose2d(0,0, toRadians(90));
        public static Pose2d SIDE = new Pose2d(48,0, toRadians(90));

        //public static Pose2d OBSERVATION_ZONE = new Pose2d(-60, 55, toRadians(135));

        //Lines for Into the Deeeeeeeep

        public static final Supplier<Trajectory> FORWARD_BACKWARD1 = () ->
                func
                        .apply(REST)
                        .lineToLinearHeading(FORWARD)
                        .build();

        public static final Supplier<Trajectory> FORWARD_BACKWARD2 = () ->
                func
                        .apply(FORWARD)
                        .lineToLinearHeading(REST)
                        .build();
        public static final Supplier<Trajectory> FORWARD_BACKWARD3 = () ->
                func
                        .apply(REST)
                        .lineToLinearHeading(SIDE)
                        .build();

        public static final Supplier<Trajectory> FORWARD_BACKWARD4 = () ->
                func
                        .apply(SIDE)
                        .lineToLinearHeading(REST)
                        .build();
        public static final Supplier<Trajectory> OBSERVATION_SIDE_CYCLE = () ->
            func
                .apply(OBSERVATION_START)
                .lineToLinearHeading(SUBMARINE)
                .lineToLinearHeading(OBSERVATION_ZONE)
                .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST1 = () ->
                func
                        .apply(OBSERVATION_START)
                        .lineToLinearHeading(SUBMARINE)
                        .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST1QUARTER = () ->
                func
                        .apply(SUBMARINE)
                        .lineToLinearHeading(PUSH_1)
                        .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST2 = () ->
            func
                .apply(PUSH_HALF)
                    //.splineToLinearHeading(PUSH_HALF, Math.PI - PUSH_1.getHeading())
                    .splineToLinearHeading(PUSH_1, Math.PI - PUSH_1.getHeading())
                    .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST3 = () ->
                func
                        .apply(SUBMARINE)
                        .lineToLinearHeading(MINI_LINE)
                        .splineToConstantHeading(PUSH_HALF.vec(), Math.PI - PUSH_HALF.getHeading())
                        .splineToConstantHeading(PUSH_1.vec(), Math.PI - PUSH_1.getHeading())
                        .splineToConstantHeading(PUSH_2.vec(), Math.PI - PUSH_2.getHeading())
                        .splineToConstantHeading(PUSH_3.vec(), Math.PI - PUSH_3.getHeading())
                        .splineToConstantHeading(PUSH_3_AND_A_HALF.vec(), PUSH_3_AND_A_HALF.getHeading())
                        .splineToConstantHeading(SAMPLE_1.vec(), Math.PI - SAMPLE_1.getHeading())
                        .splineToConstantHeading(OBSERVATION_PUSH_HALF.vec(), Math.PI - OBSERVATION_PUSH_HALF.getHeading())
                        .splineToConstantHeading(OBSERVATION_ZONE.vec(), Math.PI - OBSERVATION_ZONE.getHeading())
                        .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST4 = () ->
                func
                        .apply(OBSERVATION_ZONE)
                        .lineToLinearHeading(SUBMARINE2)
                        .build();
        public static final Supplier<Trajectory> OBSERVATION_TEST5 = () ->
                func
                        .apply(SUBMARINE2)
                        .lineToLinearHeading(OBSERVATION_ZONE)
                        .build();

        public static final Supplier<Trajectory> OBSERVATION_TEST6 = () ->
                func
                        .apply(OBSERVATION_ZONE)
                        .lineToLinearHeading(SUBMARINE3)
                        .build();


        public static final Supplier<Trajectory> TANGENT_TEST = () ->
                func
                        .apply(OBSERVATION_ZONE)
                        //.splineToConstantHeading(OBSERVATION_ZONE.vec(), Math.toRadians(130))
                        .splineToLinearHeading(SUBMARINE3, Math.toRadians(0))
                        .build();


        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO1 = () ->
                func
                        .apply(OBSERVATION_START)
                        .lineToLinearHeading(PUSH_1)
                        .build();
        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO2 = () ->
                func
                        .apply(PUSH_1)
                        .lineToLinearHeading(PUSH_2)
                        .build();

        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO4 = () ->
                func
                        .apply(PUSH_2)
                        .lineToLinearHeading(PUSH_3_AND_A_HALF)
                        .build();

        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF = () ->
                func
                        .apply(PUSH_3_AND_A_HALF)
                        .lineToLinearHeading(OBSERVATION_ZONE)
                        .build();


        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO5 = () ->
                func
                        .apply(OBSERVATION_ZONE)
                        .lineToLinearHeading(PUSH_3_AND_A_HALF)
                        .build();



        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO6 = () ->
                func
                        .apply(PUSH_3_AND_A_HALF)
                        .lineToLinearHeading(PUSH_4)
                        .build();

        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO7 = () ->
                func
                        .apply(PUSH_4)
                        .lineToLinearHeading(OBSERVATION_ZONE_2)
                        .build();

        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO8 = () ->
                func
                        .apply(OBSERVATION_ZONE_2)
                        .lineToLinearHeading(PUSH_4)
                        .build();

        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO9 = () ->
                func
                        .apply(PUSH_4)
                        .lineToLinearHeading(PUSH_5)
                        .build();

        public static final Supplier<Trajectory> PUSH_BOT_OBSERVATION_SIDE_AUTO10 = () ->
                func
                        .apply(PUSH_5)
                        .lineToLinearHeading(OBSERVATION_ZONE_3)
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
            //.addTrajectory(AutoConstants.OBSERVATION_TEST1.get())
            // dont use.addTrajectory(AutoConstants.OBSERVATION_TEST1QUARTER.get())
            // dont use.addTrajectory(AutoConstants.OBSERVATION_TEST2.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST3.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST4.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST5.get())
            //.addTrajectory(AutoConstants.OBSERVATION_TEST6.get())
            //.addTrajectory(AutoConstants.TANGENT_TEST.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO1.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO2.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO5.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO6.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO7.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO8.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO9.get())
//            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO10.get())
//            .build();
            .addTrajectory(AutoConstants.FORWARD_BACKWARD1.get())
            .addTrajectory(AutoConstants.FORWARD_BACKWARD2.get())
            .addTrajectory(AutoConstants.FORWARD_BACKWARD3.get())
            .addTrajectory(AutoConstants.FORWARD_BACKWARD4.get())
            .build();

    }
}
