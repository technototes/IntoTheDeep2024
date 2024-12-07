package org.firstinspires.ftc.twenty403;

import static java.lang.Math.toRadians;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.technototes.path.geometry.ConfigurablePoseD;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;


import java.util.function.Function;
import java.util.function.Supplier;

@Config
public class AutoConstants {

    // Stuff for testing:
    public static ConfigurablePoseD TEST_START = new ConfigurablePoseD(-50, -50, 0);
    public static ConfigurablePoseD TEST_LEFT = new ConfigurablePoseD(-2, -50, 0);
    public static ConfigurablePoseD TEST_FORWARD = new ConfigurablePoseD(-50, -2, 0);
    public static ConfigurablePoseD TEST_SPLINE_1 = new ConfigurablePoseD(-38, -2, 0);
    public static ConfigurablePoseD TEST_SPLINE_2 = new ConfigurablePoseD(-38, -2, -90);

    //preston positions
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
    public static Pose2d EIGHTH = new Pose2d(-52, 47, toRadians(90));
    public static Pose2d EIGHTH1 = new Pose2d(0, 35, toRadians(-90));
    public static Pose2d EIGHTH2 = new Pose2d(0, 35.1, toRadians(180));
    public static Pose2d EIGHTH3 = new Pose2d(-32, 35, toRadians(180));
    public static Pose2d EIGHTH4 = new Pose2d(-42, 10, toRadians(180));
    public static Pose2d NINTH = new Pose2d(-56, 13, toRadians(90));
    public static Pose2d TENTH = new Pose2d(-56, 48, toRadians(90));
    public static Pose2d ELEVENTH = new Pose2d(-56, 13, toRadians(90));
    public static Pose2d TWELFTH= new Pose2d(-61, 13, toRadians(90));
    public static Pose2d THIRTEENTH= new Pose2d(-61, 48, toRadians(90));
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FIRST_TO_SECOND = func -> func.apply(FIRST).lineToLinearHeading(SECOND).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SECOND_TO_THIRD = func -> func.apply(SECOND).lineToLinearHeading(THIRD).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> THIRD_TO_FOURTH =func -> func.apply(THIRD).lineToLinearHeading(FOURTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FOURTH_TO_FIFTH =func -> func.apply(FOURTH).lineToLinearHeading(FIFTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> FIFTH_TO_SIXTH =func -> func.apply(FIFTH).lineToLinearHeading(SIXTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SIXTH_TO_SEVENTH =func -> func.apply(SIXTH).lineToLinearHeading(SEVENTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SEVENTH_TO_SEVENTH1 =func -> func.apply(SEVENTH).lineToLinearHeading(SEVENTH1).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SEVENTH1_TO_SEVENTH2 =func -> func.apply(SEVENTH1).lineToLinearHeading(SEVENTH2).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SEVENTH2_TO_SEVENTH3 =func -> func.apply(SEVENTH2).lineToLinearHeading(SEVENTH3).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SEVENTH3_TO_SEVENTH4 =func -> func.apply(SEVENTH3).lineToLinearHeading(SEVENTH4).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> SEVENTH4_TO_EIGHTH =func -> func.apply(SEVENTH4).lineToLinearHeading(EIGHTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> EIGHTH_TO_EIGHTH1 =func -> func.apply(EIGHTH).lineToLinearHeading(EIGHTH1).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> EIGHTH1_TO_EIGHTH2 =func -> func.apply(EIGHTH1).lineToLinearHeading(EIGHTH2).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> EIGHTH2_TO_EIGHTH3 =func -> func.apply(EIGHTH2).lineToLinearHeading(EIGHTH3).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> EIGHTH3_TO_EIGHTH4 =func -> func.apply(EIGHTH3).lineToLinearHeading(EIGHTH4).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> EIGHTH4_TO_NINTH =func -> func.apply(EIGHTH4).lineToLinearHeading(NINTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> NINTH_TO_TENTH =func -> func.apply(NINTH).lineToLinearHeading(TENTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TENTH_TO_ELEVENTH =func -> func.apply(TENTH).lineToLinearHeading(ELEVENTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> ELEVENTH_TO_TWELFTH =func -> func.apply(ELEVENTH).lineToLinearHeading(TWELFTH).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TWELFTH_TO_THIRTEENTH =func -> func.apply(TWELFTH).lineToLinearHeading(THIRTEENTH).build();




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
            .splineToLinearHeading(TEST_SPLINE_1.toPose(), TEST_START.getHeading())
            .splineToLinearHeading(TEST_START.toPose(), TEST_START.getHeading())
            .build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence> TEST_SPLINE_PATH_2 =
        func -> func.apply(TEST_START.toPose())
            .splineToSplineHeading(TEST_SPLINE_2.toPose(), Math.toRadians(90))
            .splineToSplineHeading(TEST_START.toPose(), Math.toRadians(-45))
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

    public static ConfigurablePoseD FORWARD = new ConfigurablePoseD(0, 18, 90);
    public static ConfigurablePoseD REST = new ConfigurablePoseD(0, 0, 90);
    public static ConfigurablePoseD SIDE = new ConfigurablePoseD(18, 0, 90);

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




    //New testing constants for this year's game
    public static ConfigurablePoseD START = new ConfigurablePoseD(35, 63, 0);
    public static ConfigurablePoseD OBS_START = new ConfigurablePoseD(22, -65, 90);
    public static ConfigurablePoseD NETSCORING_TEST = new ConfigurablePoseD(55, 55, 45);
    public static ConfigurablePoseD NETSCORING_CLEAR = new ConfigurablePoseD(45, 35, -45);
    public static ConfigurablePoseD SPLINETEST1 = new ConfigurablePoseD(0, -55, 0);
    public static ConfigurablePoseD SPLINETEST2 = new ConfigurablePoseD(55, 0, 0);
    public static ConfigurablePoseD OBS_PARK = new ConfigurablePoseD(62, -6, 90);

    public static ConfigurablePoseD NETSCORING = new ConfigurablePoseD(55, 55, 45);
    public static ConfigurablePoseD NET_START = new ConfigurablePoseD(35, 63, 0);
    public static ConfigurablePoseD NETCLEAR = new ConfigurablePoseD(52, 52, 45);
    public static ConfigurablePoseD INTAKE1 = new ConfigurablePoseD(47, 40, 90);
    public static ConfigurablePoseD INTAKE2 = new ConfigurablePoseD(60, 35, 90);
    public static ConfigurablePoseD INTAKE3 = new ConfigurablePoseD(64, 37, 90);
    public static ConfigurablePoseD ASCENT = new ConfigurablePoseD(23, 12, -0);



    // These are 'trajectory pieces' which should be named like this:
    // {STARTING_POSITION}_TO_{ENDING_POSITION}
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            START_TO_NETSCORING = b ->
            b.apply(NET_START.toPose()).lineToLinearHeading(NETSCORING.toPose()).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            NETSCORING_TO_INTAKE1 = b ->
            b.apply(NETSCORING.toPose()).lineToLinearHeading(INTAKE1.toPose()).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            INTAKE1_TO_NETSCORING = b ->
            b.apply(INTAKE1.toPose()).lineToLinearHeading(NETSCORING.toPose()).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            NETSCORING_TO_INTAKE2 = b ->
            b.apply(NETSCORING.toPose()).lineToLinearHeading(INTAKE2.toPose()).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            INTAKE2_TO_NETSCORING = b ->
            b.apply(INTAKE2.toPose()).lineToLinearHeading(NETSCORING.toPose()).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            NETSCORING_TO_INTAKE3 = b ->
            b.apply(NETSCORING.toPose()).lineToLinearHeading(INTAKE3.toPose()).build();
    public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            INTAKE3_TO_NETSCORING = b ->
            b.apply(INTAKE3.toPose()).lineToLinearHeading(NETSCORING.toPose()).build();}

    //end of forward backward yippee


//Do i need the meep-meep stuff down below?

    //gonna comment this stuff out below but idk if i still need it here or somewhere else :DDD

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