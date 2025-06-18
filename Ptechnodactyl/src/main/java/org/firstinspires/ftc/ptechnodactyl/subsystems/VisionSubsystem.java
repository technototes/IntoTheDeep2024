package org.firstinspires.ftc.ptechnodactyl.subsystems;

import static com.technototes.library.hardware.HardwareDevice.hardwareMap;
import static org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem.VisionConstants.HEIGHT;
import static org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem.VisionConstants.ROTATION;
import static org.firstinspires.ftc.ptechnodactyl.subsystems.VisionSubsystem.VisionConstants.WIDTH;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.util.Color;
import com.technototes.vision.hardware.Webcam;
import org.firstinspires.ftc.ptechnodactyl.opmodes.tele.TeleOpBase;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class VisionSubsystem implements Subsystem, Loggable {

    @Config
    public static class VisionConstants {

        public static int WIDTH = 320;
        public static int HEIGHT = 240;
        public static OpenCvCameraRotation ROTATION = OpenCvCameraRotation.UPSIDE_DOWN;
    }

    @LogConfig.DenyList({ TeleOpBase.RedTeleOp.class, TeleOpBase.BlueTeleOp.class })
    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Number(name = "Barcode")
    public BarcodePipeline barcodePipeline = new BarcodePipeline();

    public Webcam camera;

    public VisionSubsystem(Webcam c) {
        camera = c;
    }

    public void startStreaming() {
        camera.startStreaming(WIDTH, HEIGHT, ROTATION);
    }

    public void startBarcodePipeline() {
        camera.setPipeline(barcodePipeline);
        camera.openCameraDeviceAsync(this::startStreaming, i -> startBarcodePipeline());
    }

    public void stopBarcodePipeline() {
        camera.setPipeline(null);
        camera.closeCameraDeviceAsync(() -> {});
    }
}
