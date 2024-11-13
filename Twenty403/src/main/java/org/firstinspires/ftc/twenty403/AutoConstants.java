package org.firstinspires.ftc.twenty403;

import static java.lang.Math.toRadians;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.technototes.library.command.Command;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.geometry.ConfigurablePoseD;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;


import java.util.function.Function;
import java.util.function.Supplier;

@Config
public class AutoConstants {

        public static ConfigurablePoseD OBSERVATION_START = new ConfigurablePoseD(0, 60, -90);
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
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_SIDE_CYCLE = func ->
                func
                        .apply(OBSERVATION_START.toPose())
                        .lineToLinearHeading(SUBMARINE)
                        .lineToLinearHeading(OBSERVATION_ZONE)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST1 = func ->
                func
                        .apply(OBSERVATION_START.toPose())
                        .lineToLinearHeading(SUBMARINE)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST1QUARTER = func ->
                func
                        .apply(SUBMARINE)
                        .lineToLinearHeading(PUSH_1)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST2 = func ->
                func
                        .apply(PUSH_HALF)
                        //.splineToLinearHeading(PUSH_HALF, Math.PI - PUSH_1.getHeading())
                        .splineToLinearHeading(PUSH_1, Math.PI - PUSH_1.getHeading())
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST3 = func ->
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

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST4 = func ->
                func
                        .apply(OBSERVATION_ZONE)
                        .lineToLinearHeading(SUBMARINE2)
                        .build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST5 = func ->
                func
                        .apply(SUBMARINE2)
                        .lineToLinearHeading(OBSERVATION_ZONE)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST6 = func ->
                func
                        .apply(OBSERVATION_ZONE)
                        .lineToLinearHeading(SUBMARINE3)
                        .build();


        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TANGENT_TEST = func ->
                func
                        .apply(OBSERVATION_ZONE)
                        //.splineToConstantHeading(OBSERVATION_ZONE.vec(), Math.toRadians(130))
                        .splineToLinearHeading(SUBMARINE3, Math.toRadians(0))
                        .build();


        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO1 = func ->
                func
                        .apply(OBSERVATION_START)
                        .lineToLinearHeading(PUSH_1)
                        .build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO2 = func ->
                func
                        .apply(PUSH_1)
                        .lineToLinearHeading(PUSH_2)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO4 = func ->
                func
                        .apply(PUSH_2)
                        .lineToLinearHeading(PUSH_3_AND_A_HALF)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF = func ->
                func
                        .apply(PUSH_3_AND_A_HALF)
                        .lineToLinearHeading(OBSERVATION_ZONE)
                        .build();


        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO5 = func ->
                func
                        .apply(OBSERVATION_ZONE)
                        .lineToLinearHeading(PUSH_3_AND_A_HALF)
                        .build();



        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO6 = func ->
                func
                        .apply(PUSH_3_AND_A_HALF)
                        .lineToLinearHeading(PUSH_4)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO7 = func ->
                func
                        .apply(PUSH_4)
                        .lineToLinearHeading(OBSERVATION_ZONE_2)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO8 = func ->
                func
                        .apply(OBSERVATION_ZONE_2)
                        .lineToLinearHeading(PUSH_4)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO9 = func ->
                func
                        .apply(PUSH_4)
                        .lineToLinearHeading(PUSH_5)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO10 = func ->
                func
                        .apply(PUSH_5)
                        .lineToLinearHeading(OBSERVATION_ZONE_3)
                        .build();

        //forward backward yippee

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD1 = func ->
                func
                        .apply(REST)
                        .lineToLinearHeading(FORWARD)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD2 = func ->
                func
                        .apply(FORWARD)
                        .lineToLinearHeading(REST)
                        .build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD3 = func ->
                func
                        .apply(REST)
                        .lineToLinearHeading(SIDE)
                        .build();

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD4 = func ->
                func
                        .apply(SIDE)
                        .lineToLinearHeading(REST)
                        .build();

        //end of forward backward yippee


//Do i need the meep-meep stuff down below?

        //gonna comment this stuff out below but idk if i still need it here or somewhere else :DDD
        //kms :)

        /*
    .trajectorySequenceBuilder(AutoConstants.OBSERVATION_START)
            .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO1.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO2.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO5.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO6.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO7.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO8.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO9.get())
                .addTrajectory(AutoConstants.PUSH_BOT_OBSERVATION_SIDE_AUTO10.get())
                .build();

*/
}