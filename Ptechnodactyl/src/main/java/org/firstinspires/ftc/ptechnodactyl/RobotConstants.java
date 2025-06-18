package org.firstinspires.ftc.ptechnodactyl;

import static java.lang.Math.toRadians;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.technototes.library.util.Alliance;
import com.technototes.path.geometry.ConfigurablePose;
import com.technototes.path.trajectorysequence.TrajectorySequence;
import com.technototes.path.trajectorysequence.TrajectorySequenceBuilder;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class RobotConstants {

    @Config
    public static class AutoRedConstants {

        public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, -63, toRadians(90));
        public static ConfigurablePose ALLIANCE_HUB = new ConfigurablePose(7, -52, toRadians(125));
        public static ConfigurablePose CYCLE_TRENCH = new ConfigurablePose(24, -64, toRadians(180));
        public static ConfigurablePose CYCLE_INTERMEDIATE = new ConfigurablePose(
            42,
            -64,
            toRadians(180)
        );
        public static ConfigurablePose[] AUTO_WAREHOUSE = new ConfigurablePose[] {
            new ConfigurablePose(44, -64, toRadians(190)),
            new ConfigurablePose(46.5, -64, toRadians(190)),
            new ConfigurablePose(49, -64, toRadians(190)),
            new ConfigurablePose(51.5, -64, toRadians(190)),
            new ConfigurablePose(54, -64, toRadians(190)),
        };

        public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, -63, toRadians(90));
        public static ConfigurablePose DUCK_HUB = new ConfigurablePose(-32, -52, toRadians(55));
        public static ConfigurablePose CAROUSEL = new ConfigurablePose(-61, -59, toRadians(0));
        public static ConfigurablePose DUCK_INTAKE_START = new ConfigurablePose(
            -30,
            -58,
            toRadians(145)
        );
        public static ConfigurablePose DUCK_INTAKE_END = new ConfigurablePose(
            -60,
            -61,
            toRadians(35)
        );
        public static ConfigurablePose SQUARE = new ConfigurablePose(-67, -36, toRadians(0));
        public static ConfigurablePose BARRIER_PARK = new ConfigurablePose(60, -30, toRadians(0));

        public static ConfigurablePose SHARED_TRENCH = new ConfigurablePose(64, -23, toRadians(90));
        public static ConfigurablePose SHARED_HUB = new ConfigurablePose(64, -17, toRadians(100));
        public static ConfigurablePose SHARED_INTAKE = new ConfigurablePose(64, -50, toRadians(85));
    }

    @Config
    public static class AutoBlueConstants {

        public static ConfigurablePose CYCLE_START = new ConfigurablePose(12, 63, toRadians(-90));
        public static ConfigurablePose ALLIANCE_HUB = new ConfigurablePose(7, 52, toRadians(-125));
        public static ConfigurablePose CYCLE_TRENCH = new ConfigurablePose(24, 64, toRadians(-180));
        public static ConfigurablePose CYCLE_INTERMEDIATE = new ConfigurablePose(
            42,
            64,
            toRadians(-180)
        );
        public static ConfigurablePose[] AUTO_WAREHOUSE = new ConfigurablePose[] {
            new ConfigurablePose(44, 64, toRadians(-190)),
            new ConfigurablePose(46.5, 64, toRadians(-190)),
            new ConfigurablePose(49, 64, toRadians(-190)),
            new ConfigurablePose(51.5, 64, toRadians(-190)),
            new ConfigurablePose(54, 64, toRadians(-190)),
        };

        public static ConfigurablePose DUCK_START = new ConfigurablePose(-36, 63, toRadians(-90));
        public static ConfigurablePose DUCK_HUB = new ConfigurablePose(-32, 52, toRadians(-55));
        public static ConfigurablePose CAROUSEL = new ConfigurablePose(-61, 59, toRadians(-90));
        public static ConfigurablePose DUCK_INTAKE_START = new ConfigurablePose(
            -30,
            58,
            toRadians(-145)
        );
        public static ConfigurablePose DUCK_INTAKE_END = new ConfigurablePose(
            -60,
            61,
            toRadians(-35)
        );
        public static ConfigurablePose SQUARE = new ConfigurablePose(-67, 36, toRadians(0));
        public static ConfigurablePose BARRIER_PARK = new ConfigurablePose(60, 30, toRadians(0));

        public static ConfigurablePose SHARED_TRENCH = new ConfigurablePose(64, 23, toRadians(-90));
        public static ConfigurablePose SHARED_HUB = new ConfigurablePose(64, 17, toRadians(-90));
        public static ConfigurablePose SHARED_INTAKE = new ConfigurablePose(64, 50, toRadians(-90));
    }

    private static Alliance alliance = Alliance.BLUE;

    public static void updateAlliance(Alliance i) {
        alliance = i;
    }

    public static Alliance getAlliance() {
        return alliance;
    }

    public static final Supplier<Pose2d> CYCLE_START_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.CYCLE_START, AutoBlueConstants.CYCLE_START)
            .toPose(), ALLIANCE_HUB_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.ALLIANCE_HUB, AutoBlueConstants.ALLIANCE_HUB)
            .toPose(), SHARED_HUB_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.SHARED_HUB, AutoBlueConstants.SHARED_HUB)
            .toPose(), SHARED_INTAKE_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.SHARED_INTAKE, AutoBlueConstants.SHARED_INTAKE)
            .toPose(), ALLIANCE_TRENCH_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.CYCLE_TRENCH, AutoBlueConstants.CYCLE_TRENCH)
            .toPose(), CYCLE_INTERMEDIATE_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.CYCLE_INTERMEDIATE, AutoBlueConstants.CYCLE_INTERMEDIATE)
            .toPose(), SHARED_TRENCH_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.SHARED_TRENCH, AutoBlueConstants.SHARED_TRENCH)
            .toPose(), DUCK_START_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.DUCK_START, AutoBlueConstants.DUCK_START)
            .toPose(), DUCK_ALLIANCE_HUB_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.DUCK_HUB, AutoBlueConstants.DUCK_HUB)
            .toPose(), CAROUSEL_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.CAROUSEL, AutoBlueConstants.CAROUSEL)
            .toPose(), DUCK_INTAKE_START_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.DUCK_INTAKE_START, AutoBlueConstants.DUCK_INTAKE_START)
            .toPose(), DUCK_INTAKE_END_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.DUCK_INTAKE_END, AutoBlueConstants.DUCK_INTAKE_END)
            .toPose(), SQUARE_SELECT = () ->
        alliance
            .selectOf(AutoRedConstants.SQUARE, AutoBlueConstants.SQUARE)
            .toPose(), BARRIER_SELECT = () ->
        alliance.selectOf(AutoRedConstants.BARRIER_PARK, AutoBlueConstants.BARRIER_PARK).toPose();

    public static final Function<Integer, Pose2d> CYCLE_INTAKE_SELECT = i ->
        alliance
            .selectOf(AutoRedConstants.AUTO_WAREHOUSE[i], AutoBlueConstants.AUTO_WAREHOUSE[i])
            .toPose();

    public static final Function<
        Function<Pose2d, TrajectorySequenceBuilder>,
        TrajectorySequence
    > CYCLE_DEPOSIT_PRELOAD = b ->
        b
            .apply(CYCLE_START_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 30)
            .lineToLinearHeading(ALLIANCE_HUB_SELECT.get())
            .build(), DUCK_DEPOSIT_PRELOAD = b ->
        b
            .apply(DUCK_START_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 30)
            .lineToLinearHeading(DUCK_ALLIANCE_HUB_SELECT.get())
            .build(), HUB_TO_CAROUSEL = b ->
        b
            .apply(DUCK_ALLIANCE_HUB_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 30)
            .lineToLinearHeading(CAROUSEL_SELECT.get())
            .build(), HUB_TO_SQUARE = b ->
        b
            .apply(DUCK_ALLIANCE_HUB_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 30)
            .lineToLinearHeading(SQUARE_SELECT.get())
            .build(), HUB_BARRIER_PARK = b ->
        b
            .apply(DUCK_ALLIANCE_HUB_SELECT.get())
            .turn(BARRIER_SELECT.get().getHeading() - DUCK_ALLIANCE_HUB_SELECT.get().getHeading())
            .setVelConstraint((a, e, c, d) -> 100)
            .lineTo(BARRIER_SELECT.get().vec())
            .build(), HUB_TO_PARK = b ->
        b
            .apply(ALLIANCE_HUB_SELECT.get())
            .setReversed(true)
            .setAccelConstraint((a, e, c, d) -> 30)
            .splineTo(ALLIANCE_TRENCH_SELECT.get().vec(), 0)
            //                    .setAccelConstraint((a, e, c, d) -> 100)
            //                    .setVelConstraint((a, e, c, d)->70)
            .lineToSplineHeading(CYCLE_INTERMEDIATE_SELECT.get())
            .build(), DUCK_INTAKE_TO_HUB = b ->
        b
            .apply(DUCK_INTAKE_END_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 30)
            .lineToLinearHeading(DUCK_ALLIANCE_HUB_SELECT.get())
            .build(), SHARED_HUB_TO_WAREHOUSE = b ->
        b
            .apply(SHARED_HUB_SELECT.get())
            .setReversed(true)
            .setAccelConstraint((a, e, c, d) -> 30)
            .splineTo(
                SHARED_TRENCH_SELECT.get().vec(),
                toRadians(RobotConstants.getAlliance().selectOf(-90, 90))
            )
            //                    .setAccelConstraint((a, e, c, d) -> 100)
            //                    .setVelConstraint((a, e, c, d)->70)
            .lineToSplineHeading(SHARED_INTAKE_SELECT.get())
            .build(), CAROUSEL_TO_DUCK_INTAKE = b ->
        b
            .apply(CAROUSEL_SELECT.get())
            .setAccelConstraint((a, e, c, d) -> 20)
            .setVelConstraint((a, e, c, d) -> 30)
            .turn(DUCK_INTAKE_START_SELECT.get().getHeading() - CAROUSEL_SELECT.get().getHeading())
            .lineToLinearHeading(DUCK_INTAKE_START_SELECT.get())
            .turn(
                DUCK_INTAKE_END_SELECT.get().getHeading() -
                DUCK_INTAKE_START_SELECT.get().getHeading()
            )
            .lineToLinearHeading(DUCK_INTAKE_END_SELECT.get())
            .build();

    public static final BiFunction<
        Function<Pose2d, TrajectorySequenceBuilder>,
        Integer,
        TrajectorySequence
    > HUB_TO_WAREHOUSE = (b, i) ->
        b
            .apply(ALLIANCE_HUB_SELECT.get())
            .setReversed(true)
            .setAccelConstraint((a, e, c, d) -> 30)
            .splineTo(ALLIANCE_TRENCH_SELECT.get().vec(), 0)
            .setAccelConstraint((a, e, c, d) -> 60)
            .lineToSplineHeading(CYCLE_INTERMEDIATE_SELECT.get())
            .setVelConstraint((a, e, c, d) -> 20)
            .lineToSplineHeading(CYCLE_INTAKE_SELECT.apply(i))
            .build();

    public static final BiFunction<
        Function<Pose2d, TrajectorySequenceBuilder>,
        Supplier<Pose2d>,
        TrajectorySequence
    > WAREHOUSE_TO_HUB = (b, p) ->
        b
            .apply(
                new Pose2d(
                    Math.max(p.get().getX(), ALLIANCE_TRENCH_SELECT.get().getX() + 1),
                    ALLIANCE_TRENCH_SELECT.get().getY(),
                    //            p.get().getY(),
                    p.get().getHeading()
                )
            )
            //            .setTurnConstraint(toRadians(30), toRadians(30))
            .lineToSplineHeading(ALLIANCE_TRENCH_SELECT.get())
            //            .splineToSplineHeading(ALLIANCE_TRENCH_SELECT.get(), toRadians(180))
            .setAccelConstraint((a, e, c, d) -> 30)
            .splineTo(ALLIANCE_HUB_SELECT.get().vec(), ALLIANCE_HUB_SELECT.get().getHeading())
            .build(), WAREHOUSE_TO_SHARED_HUB = (b, p) ->
        b
            .apply(
                new Pose2d(
                    p.get().getX(),
                    //                    p.get().getY(),
                    SHARED_TRENCH_SELECT.get().getY(),
                    p.get().getHeading()
                )
            )
            .lineToSplineHeading(SHARED_TRENCH_SELECT.get())
            .splineToSplineHeading(SHARED_HUB_SELECT.get(), SHARED_HUB_SELECT.get().getHeading())
            .build();
}
