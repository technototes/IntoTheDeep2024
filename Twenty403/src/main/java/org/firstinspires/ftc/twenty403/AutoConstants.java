package org.firstinspires.ftc.twenty403;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.geometry.ConfigurablePoseD;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;


import java.util.function.Function;

@Config
public class AutoConstants {

    // Stuff for testing:
    public static ConfigurablePoseD TEST_START = new ConfigurablePoseD(0, 0, 0);
    public static ConfigurablePoseD TEST_LEFT = new ConfigurablePoseD(48, 0, 0);
    public static ConfigurablePoseD TEST_FORWARD = new ConfigurablePoseD(0, 48, 0);
    public static ConfigurablePoseD TEST_SPLINE_1 = new ConfigurablePoseD(24, 48, 0);
    public static ConfigurablePoseD TEST_SPLINE_2 = new ConfigurablePoseD(24, 48, -90);
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_RIGHT_TO_LEFT =
        func -> func.apply(TEST_START.toPose())
            .lineToLinearHeading(TEST_LEFT.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_LEFT_TO_RIGHT =
        func -> func.apply(TEST_LEFT.toPose())
            .lineToLinearHeading(TEST_START.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_FORWARD_PATH =
        func -> func.apply(TEST_START.toPose())
            .lineToLinearHeading(TEST_FORWARD.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_BACKWARD_PATH =
        func -> func.apply(TEST_FORWARD.toPose())
            .lineToLinearHeading(TEST_START.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_SPLINE_PATH_1 =
        func -> func.apply(TEST_START.toPose())
            .splineToLinearHeading(TEST_SPLINE_1.toPose(), TEST_FORWARD.getHeading())
            .splineToLinearHeading(TEST_START.toPose(), TEST_FORWARD.getHeading())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_SPLINE_PATH_2 =
        func -> func.apply(TEST_START.toPose())
            .splineToSplineHeading(TEST_SPLINE_2.toPose(), TEST_FORWARD.getHeading())
            .splineToSplineHeading(TEST_START.toPose(), TEST_SPLINE_2.getHeading())
            .build();

    public static ConfigurablePoseD OBSERVATION_START = new ConfigurablePoseD(0, 60, -90);
    public static ConfigurablePoseD SUBMARINE = new ConfigurablePoseD(-5, 36, (90));
    public static ConfigurablePoseD OBSERVATION_ZONE = new ConfigurablePoseD(-46, 55, (90));
    public static ConfigurablePoseD SUBMARINE2 = new ConfigurablePoseD(0, 36, (90));
    public static ConfigurablePoseD SUBMARINE3 = new ConfigurablePoseD(5, 36, (-90));
    public static ConfigurablePoseD MINI_LINE = new ConfigurablePoseD(-3, 36, (90));

    public static ConfigurablePoseD PUSH_HALF = new ConfigurablePoseD(-16, 36, (90));

    public static ConfigurablePoseD PUSH_1 = new ConfigurablePoseD(-34, 45, (90));
    public static ConfigurablePoseD PUSH_2 = new ConfigurablePoseD(-34, 10, (90));
    public static ConfigurablePoseD PUSH_3_AND_A_HALF = new ConfigurablePoseD(-46, 11, (90));
    public static ConfigurablePoseD PUSH_3 = new ConfigurablePoseD(-35, 10, (90));
    public static ConfigurablePoseD PUSH_4 = new ConfigurablePoseD(-56, 10, (90));
    public static ConfigurablePoseD OBSERVATION_ZONE_2 = new ConfigurablePoseD(-56, 55, (90));
    public static ConfigurablePoseD PUSH_5 = new ConfigurablePoseD(-61, 10, (90));
    public static ConfigurablePoseD OBSERVATION_ZONE_3 = new ConfigurablePoseD(-61, 55, (90));

    public static ConfigurablePoseD SAMPLE_1 = new ConfigurablePoseD(-48, 26, (90));
    public static ConfigurablePoseD OBSERVATION_PUSH_HALF = new ConfigurablePoseD(-47, 40, (90));

    //forward backward constants

    public static ConfigurablePoseD FORWARD = new ConfigurablePoseD(0, 48, (90));
    public static ConfigurablePoseD REST = new ConfigurablePoseD(0, 0, (90));
    public static ConfigurablePoseD SIDE = new ConfigurablePoseD(48, 0, (90));

    //public static ConfigurablePoseD OBSERVATION_ZONE = new ConfigurablePoseD(-60, 55, (135));

    //Lines for Into the Deeeeeeeep
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
        OBSERVATION_SIDE_CYCLE = func ->
        func
            .apply(OBSERVATION_START.toPose())
            .lineToLinearHeading(SUBMARINE.toPose())
            .lineToLinearHeading(OBSERVATION_ZONE.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST1 = func ->
        func
            .apply(OBSERVATION_START.toPose())
            .lineToLinearHeading(SUBMARINE.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST1QUARTER = func ->
        func
            .apply(SUBMARINE.toPose())
            .lineToLinearHeading(PUSH_1.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST2 = func ->
        func
            .apply(PUSH_HALF.toPose())
            //.splineToLinearHeading(PUSH_HALF, Math.PI - PUSH_1.getHeading())
            .splineToLinearHeading(PUSH_1.toPose(), Math.PI - PUSH_1.getHeading())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST3 = func ->
        func
            .apply(SUBMARINE.toPose())
            .lineToLinearHeading(MINI_LINE.toPose())
            .splineToConstantHeading(PUSH_HALF.toVec(), Math.PI - PUSH_HALF.getHeading())
            .splineToConstantHeading(PUSH_1.toVec(), Math.PI - PUSH_1.getHeading())
            .splineToConstantHeading(PUSH_2.toVec(), Math.PI - PUSH_2.getHeading())
            .splineToConstantHeading(PUSH_3.toVec(), Math.PI - PUSH_3.getHeading())
            .splineToConstantHeading(PUSH_3_AND_A_HALF.toVec(), PUSH_3_AND_A_HALF.getHeading())
            .splineToConstantHeading(SAMPLE_1.toVec(), Math.PI - SAMPLE_1.getHeading())
            .splineToConstantHeading(OBSERVATION_PUSH_HALF.toVec(), Math.PI - OBSERVATION_PUSH_HALF.getHeading())
            .splineToConstantHeading(OBSERVATION_ZONE.toVec(), Math.PI - OBSERVATION_ZONE.getHeading())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST4 = func ->
        func
            .apply(OBSERVATION_ZONE.toPose())
            .lineToLinearHeading(SUBMARINE2.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST5 = func ->
        func
            .apply(SUBMARINE2.toPose())
            .lineToLinearHeading(OBSERVATION_ZONE.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> OBSERVATION_TEST6 = func ->
        func
            .apply(OBSERVATION_ZONE.toPose())
            .lineToLinearHeading(SUBMARINE3.toPose())
            .build();


    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TANGENT_TEST = func ->
        func
            .apply(OBSERVATION_ZONE.toPose())
            //.splineToConstantHeading(OBSERVATION_ZONE.vec(), (130))
            .splineToLinearHeading(SUBMARINE3.toPose(), (0))
            .build();


    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO1 = func ->
        func
            .apply(OBSERVATION_START.toPose())
            .lineToLinearHeading(PUSH_1.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO2 = func ->
        func
            .apply(PUSH_1.toPose())
            .lineToLinearHeading(PUSH_2.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO4 = func ->
        func
            .apply(PUSH_2.toPose())
            .lineToLinearHeading(PUSH_3_AND_A_HALF.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO4HALF = func ->
        func
            .apply(PUSH_3_AND_A_HALF.toPose())
            .lineToLinearHeading(OBSERVATION_ZONE.toPose())
            .build();


    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO5 = func ->
        func
            .apply(OBSERVATION_ZONE.toPose())
            .lineToLinearHeading(PUSH_3_AND_A_HALF.toPose())
            .build();


    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO6 = func ->
        func
            .apply(PUSH_3_AND_A_HALF.toPose())
            .lineToLinearHeading(PUSH_4.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO7 = func ->
        func
            .apply(PUSH_4.toPose())
            .lineToLinearHeading(OBSERVATION_ZONE_2.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO8 = func ->
        func
            .apply(OBSERVATION_ZONE_2.toPose())
            .lineToLinearHeading(PUSH_4.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO9 = func ->
        func
            .apply(PUSH_4.toPose())
            .lineToLinearHeading(PUSH_5.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> PUSH_BOT_OBSERVATION_SIDE_AUTO10 = func ->
        func
            .apply(PUSH_5.toPose())
            .lineToLinearHeading(OBSERVATION_ZONE_3.toPose())
            .build();

    //forward backward yippee

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD1 = func ->
        func
            .apply(REST.toPose())
            .lineToLinearHeading(FORWARD.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD2 = func ->
        func
            .apply(FORWARD.toPose())
            .lineToLinearHeading(REST.toPose())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD3 = func ->
        func
            .apply(REST.toPose())
            .lineToLinearHeading(SIDE.toPose())
            .build();

    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FORWARD_BACKWARD4 = func ->
        func
            .apply(SIDE.toPose())
            .lineToLinearHeading(REST.toPose())
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