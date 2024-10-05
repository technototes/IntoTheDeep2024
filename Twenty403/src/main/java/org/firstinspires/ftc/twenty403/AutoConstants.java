package org.firstinspires.ftc.twenty403;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.command.Command;
import com.technototes.path.command.TrajectorySequenceCommand;
import com.technototes.path.geometry.ConfigurablePoseD;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;

import java.util.function.Function;

@Config
public class AutoConstants {

        public static ConfigurablePoseD SPLINETEST1 = new ConfigurablePoseD(0, -55, 0);
        public static ConfigurablePoseD SPLINETEST2 = new ConfigurablePoseD(55, 0, 0);


        //testing constants from last year
        public static ConfigurablePoseD START = new ConfigurablePoseD(35, 60, -90);
        public static ConfigurablePoseD RIGHT_SPIKE = new ConfigurablePoseD(31, 32, -180); // near the metal,  fine tuned
        public static ConfigurablePoseD PLACE_RIGHT = new ConfigurablePoseD(-47,40,0);// not fine tuned
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

        public static final Function<Function<Pose2d, TrajectorySequenceBuilder>, TrajectorySequence>
                SPLINETEST1_TO_SPLINETEST2 = b ->
                b.apply(SPLINETEST1.toPose())
                        .splineToConstantHeading(SPLINETEST2.toPose().vec(),SPLINETEST2.getHeading())
                        .build();
    }