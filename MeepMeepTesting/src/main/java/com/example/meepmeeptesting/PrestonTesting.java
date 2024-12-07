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
        public static Pose2d FIRST = new Pose2d(0, 65, toRadians(90));
        public static Pose2d SECOND = new Pose2d(0, 38, toRadians(90));
        public static Pose2d THIRD = new Pose2d(0, 35, toRadians(180));
        public static Pose2d FOURTH = new Pose2d(-32, 35, toRadians(180));
        public static Pose2d FIFTH = new Pose2d(-42, 10, toRadians(180));
        public static Pose2d SIXTH = new Pose2d(-45, 10, toRadians(90));
        public static Pose2d SEVENTH = new Pose2d(-45, 47, toRadians(90));
        public static Pose2d SEVENTH1 = new Pose2d(0, 35, toRadians(-90));
        public static Pose2d SEVENTH2 = new Pose2d(0, 35.1, toRadians(180));
        public static Pose2d SEVENTH3 = new Pose2d(-32, 35, toRadians(180));
        public static Pose2d SEVENTH4 = new Pose2d(-45, 10, toRadians(180));
        public static Pose2d EIGHTH = new Pose2d(-45, 47, toRadians(90));
        public static Pose2d EIGHTH1 = new Pose2d(0, 35, toRadians(-90));
        public static Pose2d EIGHTH2 = new Pose2d(0, 35.1, toRadians(180));
        public static Pose2d EIGHTH3 = new Pose2d(-32, 35, toRadians(180));
        public static Pose2d EIGHTH4 = new Pose2d(-42, 10, toRadians(180));
        public static Pose2d NINTH = new Pose2d(-56, 13, toRadians(90));
        public static Pose2d TENTH = new Pose2d(-56, 48, toRadians(90));
        public static Pose2d ELEVENTH = new Pose2d(-56, 13, toRadians(90));
        public static Pose2d TWELFTH= new Pose2d(-61, 13, toRadians(90));
        public static Pose2d THIRTEENTH= new Pose2d(-61, 48, toRadians(90));
        public static final Supplier<Trajectory> FIRST_TO_SECOND = () -> func.apply(FIRST).lineToLinearHeading(SECOND).build();
        public static final Supplier<Trajectory> SECOND_TO_THIRD = () -> func.apply(SECOND).lineToLinearHeading(THIRD).build();
        public static final Supplier<Trajectory> THIRD_TO_FOURTH = () -> func.apply(THIRD).lineToLinearHeading(FOURTH).build();
        public static final Supplier<Trajectory> FOURTH_TO_FIFTH = () -> func.apply(FOURTH).lineToLinearHeading(FIFTH).build();
        public static final Supplier<Trajectory> FIFTH_TO_SIXTH = () -> func.apply(FIFTH).lineToLinearHeading(SIXTH).build();
        public static final Supplier<Trajectory> SIXTH_TO_SEVENTH = () -> func.apply(SIXTH).lineToLinearHeading(SEVENTH).build();
        public static final Supplier<Trajectory> SEVENTH_TO_SEVENTH1 = () -> func.apply(SEVENTH).lineToLinearHeading(SEVENTH1).build();
        public static final Supplier<Trajectory> SEVENTH1_TO_SEVENTH2 = () -> func.apply(SEVENTH1).lineToLinearHeading(SEVENTH2).build();
        public static final Supplier<Trajectory> SEVENTH2_TO_SEVENTH3 = () -> func.apply(SEVENTH2).lineToLinearHeading(SEVENTH3).build();
        public static final Supplier<Trajectory> SEVENTH3_TO_SEVENTH4 = () -> func.apply(SEVENTH3).lineToLinearHeading(SEVENTH4).build();
        public static final Supplier<Trajectory> SEVENTH4_TO_EIGHTH = () -> func.apply(SEVENTH4).lineToLinearHeading(EIGHTH).build();
        public static final Supplier<Trajectory> EIGHTH_TO_EIGHTH1 = () -> func.apply(EIGHTH).lineToLinearHeading(EIGHTH1).build();
        public static final Supplier<Trajectory> EIGHTH1_TO_EIGHTH2 = () -> func.apply(EIGHTH1).lineToLinearHeading(EIGHTH2).build();
        public static final Supplier<Trajectory> EIGHTH2_TO_EIGHTH3 = () -> func.apply(EIGHTH2).lineToLinearHeading(EIGHTH3).build();
        public static final Supplier<Trajectory> EIGHTH3_TO_EIGHTH4 = () -> func.apply(EIGHTH3).lineToLinearHeading(EIGHTH4).build();
        public static final Supplier<Trajectory> EIGHTH4_TO_NINTH = () -> func.apply(EIGHTH4).lineToLinearHeading(NINTH).build();
        public static final Supplier<Trajectory> NINTH_TO_TENTH = () -> func.apply(NINTH).lineToLinearHeading(TENTH).build();
        public static final Supplier<Trajectory> TENTH_TO_ELEVENTH = () -> func.apply(TENTH).lineToLinearHeading(ELEVENTH).build();
        public static final Supplier<Trajectory> ELEVENTH_TO_TWELFTH = () -> func.apply(ELEVENTH).lineToLinearHeading(TWELFTH).build();
        public static final Supplier<Trajectory> TWELFTH_TO_THIRTEENTH = () -> func.apply(TWELFTH).lineToLinearHeading(THIRTEENTH).build();

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
            .setDimensions(17.5, 17)
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
                .addTrajectory(AutoConstants.SEVENTH_TO_SEVENTH1.get())
                .addTrajectory(AutoConstants.SEVENTH1_TO_SEVENTH2.get())
                .addTrajectory(AutoConstants.SEVENTH2_TO_SEVENTH3.get())
                .addTrajectory(AutoConstants.SEVENTH3_TO_SEVENTH4.get())
                .addTrajectory(AutoConstants.SEVENTH4_TO_EIGHTH.get())
                .addTrajectory(AutoConstants.EIGHTH_TO_EIGHTH1.get())
                .addTrajectory(AutoConstants.EIGHTH1_TO_EIGHTH2.get())
                .addTrajectory(AutoConstants.EIGHTH2_TO_EIGHTH3.get())
                .addTrajectory(AutoConstants.EIGHTH3_TO_EIGHTH4.get())
                .addTrajectory(AutoConstants.EIGHTH4_TO_NINTH.get())
                .addTrajectory(AutoConstants.NINTH_TO_TENTH.get())
                .addTrajectory(AutoConstants.TENTH_TO_ELEVENTH.get())
                .addTrajectory(AutoConstants.ELEVENTH_TO_TWELFTH.get())
                .addTrajectory(AutoConstants.TWELFTH_TO_THIRTEENTH.get())
                .build();
    }
}
