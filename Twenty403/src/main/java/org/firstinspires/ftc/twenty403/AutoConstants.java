package org.firstinspires.ftc.twenty403;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.path.geometry.ConfigurablePoseD;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

@Config
public class AutoConstants {
        //constants for Into the Deeeeeeeep

        public static ConfigurablePoseD OBSERVATION_START = new ConfigurablePoseD(0, 60, -90);
        public static ConfigurablePoseD SUBMARINE = new ConfigurablePoseD(-5, 32, -90);
        public static ConfigurablePoseD OBSERVATION_ZONE = new ConfigurablePoseD(-60, 55, 120);
        public static ConfigurablePoseD SUBMARINE2 = new ConfigurablePoseD(0, 32, -90);

        public static ConfigurablePoseD PUSH_1 = new ConfigurablePoseD(-32, 35, 90);
        public static ConfigurablePoseD PUSH_2 = new ConfigurablePoseD(-32, 10, 90);
        public static ConfigurablePoseD PUSH_3 = new ConfigurablePoseD(-44, 10, 90);
        //public static ConfigurablePoseD OBSERVATION_ZONE = new ConfigurablePoseD(-60, 55, 135);



        //constants for CenterStoige
        public static ConfigurablePoseD START = new ConfigurablePoseD(35, 60, -90);
        public static ConfigurablePoseD LEFT_SPIKE = new ConfigurablePoseD(43, 35, -60); // fine tuned
        public static ConfigurablePoseD MIDDLE_SPIKE = new ConfigurablePoseD(35, 29, -90); //  fine tuned
        public static ConfigurablePoseD RIGHT_SPIKE = new ConfigurablePoseD(31, 32, -180); // near the metal,  fine tuned
        // This is "clear of the pixels, ready to go somewhere else"
        public static ConfigurablePoseD CLEAR = new ConfigurablePoseD(39,56,-90);
        public static ConfigurablePoseD MID_CLEAR =  new ConfigurablePoseD(39, 32, -180); // fine tuned OKAYY
        public static ConfigurablePoseD PARK_CENTER = new ConfigurablePoseD(-59,12,0); // may need to be 180 (0 needs test)

        public static ConfigurablePoseD PARK_CORNER = new ConfigurablePoseD(-60,56,0); // may need to be 180 (0 needs test)
        public static ConfigurablePoseD RIGHT_CLEAR = new ConfigurablePoseD(-35,56,-90); // for pixel placement
        public static ConfigurablePoseD PLACE_LEFT = new ConfigurablePoseD(-47,30,0); //not fine tuned heading needs to be zero (backwards placement)
        public static ConfigurablePoseD PLACE_MIDDLE = new ConfigurablePoseD(-47,35,0);// not fine tuned
        public static ConfigurablePoseD PLACE_RIGHT = new ConfigurablePoseD(-47,40,0);// not fine tuned
        //testing constants
        public static ConfigurablePoseD TELESTART = new ConfigurablePoseD(0,0,90);
        public static ConfigurablePoseD FORWARD = new ConfigurablePoseD(48,0,0);
        public static ConfigurablePoseD BACKWARD = new ConfigurablePoseD(0, 0, 0);
        public static ConfigurablePoseD SIDE_RIGHT = new ConfigurablePoseD(0,-48,0);
        public static ConfigurablePoseD SIDE_LEFT = new ConfigurablePoseD(0,0,0);
        public static ConfigurablePoseD MID_PARK_CENTER = new ConfigurablePoseD(-35,12,0);
        public static ConfigurablePoseD PIXEL_INTAKE = new ConfigurablePoseD(50,12,0);
        public static ConfigurablePoseD MID_SPLINE_CLEAR = new ConfigurablePoseD(35,34,-180);
        public static ConfigurablePoseD START_STAGE = new ConfigurablePoseD(35,58,0);
        public static ConfigurablePoseD HEAD_TO_STAGE = new ConfigurablePoseD(0,58,0);
        // These are 'trajectory pieces' which should be named like this:
        // {STARTING_POSITION}_TO_{ENDING_POSITION}

        //Lines for Into the Deeeeeeeep

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_SIDE_CYCLE = b ->
                b.apply(OBSERVATION_START.toPose())
                        .lineToLinearHeading(SUBMARINE.toPose())
                        .lineToLinearHeading(OBSERVATION_ZONE.toPose())
                        //.lineToLinearHeading(SUBMARINE2.toPose())
                        //.lineToLinearHeading(PUSH_1.toPose())
                        //.lineToLinearHeading(PUSH_2.toPose())
                        //.lineToLinearHeading(PUSH_3.toPose())
                        //.lineToLinearHeading(OBSERVATION_ZONE.toPose())
                        .build();


        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_TEST1 = b ->
                b.apply(OBSERVATION_START.toPose())
                        .lineToLinearHeading(SUBMARINE.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_TEST2 = b ->
                b.apply(OBSERVATION_ZONE.toPose())
                        .lineToLinearHeading(SUBMARINE2.toPose())
                        .build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_TEST3 = b ->
                b.apply(OBSERVATION_ZONE.toPose())
                        .lineToLinearHeading(SUBMARINE2.toPose())
                        .build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_TEST4 = b ->
                b.apply(SUBMARINE.toPose())
                        .lineToLinearHeading(PUSH_1.toPose())
                        .build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_TEST5 = b ->
                b.apply(PUSH_1.toPose())
                        .splineToConstantHeading(PUSH_2.toPose().vec(), Math.PI - PUSH_2.getHeading())
                        .splineToConstantHeading(PUSH_3.toPose().vec(), Math.PI - PUSH_3.getHeading())
                        .splineToConstantHeading(OBSERVATION_ZONE.toPose().vec(), Math.PI - OBSERVATION_ZONE.getHeading())
                        .build();
        /*public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                OBSERVATION_TEST6 = b ->
                b.apply(PUSH_2.toPose())
                        .splineToConstantHeading(PUSH_3.toPose().vec(), Math.PI - PUSH_3.getHeading())
                        .splineToConstantHeading(OBSERVATION_ZONE.toPose().vec(), Math.PI - OBSERVATION_ZONE.getHeading())
                        .build();
                        */

        //public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                //OBSERVATION_TEST7 = b ->
                //b.apply(PUSH_3.toPose())
                        //.splineToConstantHeading(OBSERVATION_ZONE.toPose().vec(), Math.PI - OBSERVATION_ZONE.getHeading())
                        //.build();



        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
            START_TO_LEFT_SPIKE = b ->
                b.apply(START.toPose()).lineToLinearHeading(LEFT_SPIKE.toPose()).build(),
            START_TO_MIDDLE_SPIKE = b ->
                b.apply(START.toPose()).lineToLinearHeading(MIDDLE_SPIKE.toPose()).build(),
            START_TO_RIGHT_SPIKE = b ->
                b.apply(START.toPose()).lineToLinearHeading(RIGHT_SPIKE.toPose()).build(),
            LEFT_SPIKE_TO_CLEAR = b ->
                b.apply(LEFT_SPIKE.toPose()).lineToLinearHeading(CLEAR.toPose()).build(),
            MIDDLE_SPIKE_TO_CLEAR = b ->
                b.apply(MIDDLE_SPIKE.toPose()).lineToLinearHeading(CLEAR.toPose()).build(),
            RIGHT_SPIKE_TO_CLEAR = b ->
                b.apply(RIGHT_SPIKE.toPose()).lineToLinearHeading(CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

                START_TO_MID_CLEAR = b ->
                b.apply(START.toPose()).lineToLinearHeading(MID_CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

                MID_CLEAR_TO_RIGHT_SPIKE = b ->
                b.apply(MID_CLEAR.toPose()).lineToLinearHeading(RIGHT_SPIKE.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

                RIGHT_SPIKE_TO_MID_CLEAR = b ->
                //
                b.apply(RIGHT_SPIKE.toPose()).lineToLinearHeading(MID_CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>

                MID_CLEAR_TO_CLEAR = b ->
                //
                b.apply(MID_CLEAR.toPose()).lineToLinearHeading(CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                CLEAR_TO_PARK_CORNER = b ->
                b.apply(CLEAR.toPose()).lineToLinearHeading(PARK_CORNER.toPose()).build();
        // pixel placement trajectories
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                ClEAR_TO_RIGHT_CLEAR = b ->
                b.apply(CLEAR.toPose()).lineToLinearHeading(RIGHT_CLEAR.toPose()).build();

        // placement trajectories
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                RIGHT_CLEAR_TO_PLACE_LEFT = b ->
                b.apply(RIGHT_CLEAR.toPose()).lineToLinearHeading(PLACE_LEFT.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                RIGHT_CLEAR_TO_PLACE_MIDDLE = b ->
                b.apply(RIGHT_CLEAR.toPose()).lineToLinearHeading(PLACE_MIDDLE.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                RIGHT_CLEAR_TO_PLACE_RIGHT = b ->
                b.apply(RIGHT_CLEAR.toPose()).lineToLinearHeading(PLACE_RIGHT.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                PLACE_LEFT_TO_RIGHT_CLEAR = b ->
                b.apply(PLACE_LEFT.toPose()).lineToLinearHeading(RIGHT_CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                PLACE_MIDDLE_TO_RIGHT_CLEAR = b ->
                b.apply(PLACE_MIDDLE.toPose()).lineToLinearHeading(RIGHT_CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                PLACE_RIGHT_TO_RIGHT_CLEAR = b ->
                b.apply(PLACE_RIGHT.toPose()).lineToLinearHeading(RIGHT_CLEAR.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                RIGHT_CLEAR_TO_PARK_CORNER = b ->
                b.apply(RIGHT_CLEAR.toPose()).lineToLinearHeading(PARK_CORNER.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                RIGHT_CLEAR_TO_MID_PARK_CENTER = b ->
                b.apply(RIGHT_CLEAR.toPose()).lineToLinearHeading(MID_PARK_CENTER.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                MID_PARK_CENTER_TO_PARK_CENTER= b ->
                b.apply(MID_PARK_CENTER.toPose()).lineToLinearHeading(PARK_CENTER.toPose()).build();
        // pixel intake trajectories
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                MID_PARK_CENTER_TO_PIXEL_INTAKE= b ->
                b.apply(MID_PARK_CENTER.toPose()).lineToLinearHeading(PIXEL_INTAKE.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                PIXEL_INTAKE_MID_PARK_CENTER= b ->
                b.apply(PIXEL_INTAKE.toPose()).lineToLinearHeading(MID_PARK_CENTER.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                MID_PARK_CENTER_TO_PLACE_MIDDLE= b ->
                b.apply(MID_PARK_CENTER.toPose()).lineToLinearHeading(PLACE_MIDDLE.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                PLACE_MIDDLE_TO_MID_PARK_CENTER= b ->
                b.apply(PLACE_MIDDLE.toPose()).lineToLinearHeading(MID_PARK_CENTER.toPose()).build();
        // testing trajectories
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                BACKWARD_TO_FORWARD = b ->
                b.apply(BACKWARD.toPose()).lineToLinearHeading(FORWARD.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                FORWARD_TO_BACKWARD = b ->
                b.apply(FORWARD.toPose()).lineToLinearHeading(BACKWARD.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                SIDE_LEFT_TO_SIDE_RIGHT = b ->
                b.apply(SIDE_LEFT.toPose()).lineToLinearHeading(SIDE_RIGHT.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                SIDE_RIGHT_TO_SIDE_LEFT = b ->
                b.apply(SIDE_RIGHT.toPose()).lineToLinearHeading(SIDE_LEFT.toPose()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                SPLINE_START_TO_RIGHT_SPIKE = b ->
                b.apply(START.toPose())
                        .splineTo(MID_SPLINE_CLEAR.toPose().vec(), Math.PI - MID_SPLINE_CLEAR.getHeading())
                        .splineToLinearHeading(RIGHT_SPIKE.toPose(), RIGHT_SPIKE.getHeading()).build();
        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                RIGHT_SPIKE_TO_STAGE = b ->
                b.apply(RIGHT_SPIKE.toPose())
                        .splineToLinearHeading(MID_SPLINE_CLEAR.toPose(), Math.PI - MID_SPLINE_CLEAR.getHeading())
                        .splineToConstantHeading(START_STAGE.toPose().vec(),Math.PI - START_STAGE.getHeading())
                        .splineToConstantHeading(HEAD_TO_STAGE.toPose().vec(),Math.PI - START_STAGE.getHeading())
                        .splineToConstantHeading(PLACE_RIGHT.toPose().vec(),Math.PI - START_STAGE.getHeading())
                        .build();
    }