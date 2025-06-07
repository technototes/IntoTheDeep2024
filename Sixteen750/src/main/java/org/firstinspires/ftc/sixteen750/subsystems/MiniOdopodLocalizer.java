package org.firstinspires.ftc.sixteen750.subsystems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.Localizer;
import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class MiniOdopodLocalizer implements Localizer {

    SparkFunOTOS sparky;
    SparkFunOTOS.Pose2D pose;
    SparkFunOTOS.Pose2D vel;
    SparkFunOTOS.Pose2D offset;

    public MiniOdopodLocalizer(SparkFunOTOS s) {
        sparky = s;
        sparky.setLinearUnit(DistanceUnit.INCH);
        sparky.setAngularUnit(AngleUnit.DEGREES);
        offset = new SparkFunOTOS.Pose2D(0, 0, 0);
        sparky.setOffset(offset);
    }

    @NonNull
    @Override
    public Pose2d getPoseEstimate() {
        pose = sparky.getPosition();
        Pose2d result = new Pose2d(pose.x, pose.y, pose.h);
        return result;
    }

    @Override
    public void setPoseEstimate(@NonNull Pose2d pose2d) {
        sparky.setOffset(
            new SparkFunOTOS.Pose2D(pose2d.getX(), pose2d.getY(), pose2d.getHeading())
        );
    }

    @Nullable
    @Override
    public Pose2d getPoseVelocity() {
        vel = sparky.getVelocity();
        Pose2d result = new Pose2d(vel.x, vel.y, vel.h);
        return result;
    }

    @Override
    public void update() {}
}
