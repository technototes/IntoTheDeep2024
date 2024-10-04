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

public class PrestonTesting {

    public static class AutoConstants {

        public static Function<Pose2d, TrajectoryBuilder> func;
        public static Pose2d FIRST = new Pose2d(0, 65, toRadians(-90));
        public static Pose2d SECOND = new Pose2d(0, 34, toRadians(-90));
        public static Pose2d THIRD = new Pose2d(0, 35, toRadians(180));
        public static Pose2d FOURTH = new Pose2d(-29, 35, toRadians(180));
        public static Pose2d FIFTH = new Pose2d(-34, 13, toRadians(-90));
        public static Pose2d SIXTH = new Pose2d(-48, 13, toRadians(90));
        public static Pose2d SEVENTH = new Pose2d(-48, 48, toRadians(90));
        public static Pose2d EIGHTH = new Pose2d(-48, 13, toRadians(90));
        public static Pose2d NINTH = new Pose2d(-56, 13, toRadians(90));
        public static Pose2d TENTH = new Pose2d(-56, 13, toRadians(90));
        public static final Supplier<Trajectory> FIRST_TO_SECOND = () -> func.apply(FIRST).splineToLinearHeading(SECOND,SECOND.getHeading()).build();
        public static final Supplier<Trajectory> SECOND_TO_THIRD = () -> func.apply(SECOND).splineToLinearHeading(THIRD,THIRD.getHeading()).build();
        public static final Supplier<Trajectory> THIRD_TO_FOURTH = () -> func.apply(THIRD).splineToLinearHeading(FOURTH,FOURTH.getHeading()).build();
        public static final Supplier<Trajectory> FOURTH_TO_FIFTH = () -> func.apply(FOURTH).splineToLinearHeading(FIFTH,FIFTH.getHeading()).build();
        public static final Supplier<Trajectory> FIFTH_TO_SIXTH = () -> func.apply(FIFTH).splineToLinearHeading(SIXTH,SIXTH.getHeading()).build();
        public static final Supplier<Trajectory> SIXTH_TO_SEVENTH = () -> func.apply(SIXTH).splineToLinearHeading(SEVENTH,SEVENTH.getHeading()).build();
        public static final Supplier<Trajectory> SEVENTH_TO_EIGHTH = () -> func.apply(SEVENTH).splineToLinearHeading(EIGHTH,EIGHTH.getHeading()).build();
        public static final Supplier<Trajectory> EIGHTH_TO_NINTH = () -> func.apply(EIGHTH).splineToLinearHeading(NINTH,NINTH.getHeading()).build();

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
            .trajectorySequenceBuilder(AutoConstants.FIRST)
                .addTrajectory(AutoConstants.FIRST_TO_SECOND.get())
                .addTrajectory(AutoConstants.SECOND_TO_THIRD.get())
                .addTrajectory(AutoConstants.THIRD_TO_FOURTH.get())
                .addTrajectory(AutoConstants.FOURTH_TO_FIFTH.get())
                .addTrajectory(AutoConstants.FIFTH_TO_SIXTH.get())
                .addTrajectory(AutoConstants.SIXTH_TO_SEVENTH.get())
                .addTrajectory(AutoConstants.SEVENTH_TO_EIGHTH.get())
                .addTrajectory(AutoConstants.EIGHTH_TO_NINTH.get())
                .build();
    }
}
