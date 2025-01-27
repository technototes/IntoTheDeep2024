package com.example.meepmeeptesting;

import static java.lang.Math.toRadians;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.*;
import com.acmerobotics.roadrunner.trajectory.constraints.*;
import java.io.*;
import java.util.Arrays;
import java.util.function.*;
import javax.imageio.ImageIO;
import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.roadrunner.*;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import org.rowlandhall.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class KeplerTesting {

    public static class AutoConstants {

        public static Function<Pose2d, TrajectoryBuilder> func;
        public static Pose2d SAMPLE_PUSH_AREA = new Pose2d(-60, 54, toRadians(90));
        public static Pose2d SAMPLE_COLLECT_AREA = new Pose2d(-40, 57, toRadians(120));
        public static Pose2d SAMPLES_START_PUSH = new Pose2d(-60, 20, toRadians(90));
        public static Pose2d SAMPLE_BAR_SCORE = new Pose2d(0, 34, toRadians(270));
        public static Pose2d START_POS = new Pose2d(-34, 58, toRadians(270));
        public static Pose2d CORNER_OBS = new Pose2d(10, 50, toRadians(270));
        public static Pose2d CORNER_SECOND = new Pose2d(-35, 25.5, toRadians(260));
        public static Pose2d CORNER_THIRD = new Pose2d(-50, 50, toRadians(180));
        public static final Supplier<Trajectory> BASKET_HOLDERS_ONE = () ->
            func
                .apply(START_POS)
                .splineToSplineHeading(SAMPLE_BAR_SCORE, Math.PI - SAMPLE_BAR_SCORE.getHeading())
                .splineToSplineHeading(CORNER_OBS, Math.PI - CORNER_OBS.getHeading())
                .splineToSplineHeading(CORNER_SECOND, Math.PI - CORNER_SECOND.getHeading())
                .splineToSplineHeading(
                    SAMPLES_START_PUSH,
                    Math.PI - SAMPLES_START_PUSH.getHeading()
                )
                .splineToSplineHeading(SAMPLE_PUSH_AREA, Math.PI - SAMPLE_PUSH_AREA.getHeading())
                .splineToSplineHeading(CORNER_THIRD, Math.PI - CORNER_THIRD.getHeading())
                .build();
        public static final Supplier<Trajectory> BASKET_HOLDERS_TWO = () ->
            func
                .apply(CORNER_THIRD)
                .splineToSplineHeading(
                    SAMPLE_COLLECT_AREA,
                    Math.PI - SAMPLE_COLLECT_AREA.getHeading()
                )
                .splineToSplineHeading(SAMPLE_BAR_SCORE, Math.PI - SAMPLE_BAR_SCORE.getHeading())
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
            .trajectorySequenceBuilder(AutoConstants.START_POS)
            .addTrajectory(AutoConstants.BASKET_HOLDERS_ONE.get())
            .addTrajectory(AutoConstants.BASKET_HOLDERS_TWO.get())
            .build();
    }
}
