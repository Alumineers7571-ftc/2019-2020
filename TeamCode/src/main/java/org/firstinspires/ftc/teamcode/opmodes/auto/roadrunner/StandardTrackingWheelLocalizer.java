package org.firstinspires.ftc.teamcode.opmodes.auto.roadrunner;

import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.jetbrains.annotations.NotNull;
import org.openftc.revextensions2.ExpansionHubEx;
import org.openftc.revextensions2.ExpansionHubMotor;
import org.openftc.revextensions2.RevBulkData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.firstinspires.ftc.teamcode.opmodes.auto.roadrunner.DriveConstants.encoderTicksToInches;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 * Note: this could be optimized significantly with REV bulk reads
 */

public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {

    public static double TICKS_PER_REV = 1440;
    public static double WHEEL_RADIUS = 2; // in
    public static double GEAR_RATIO = 14.5/3400; // output (wheel) speed / input (encoder) speed

    public static double LATERAL_DISTANCE = 14; // in; distance between the left and right wheels
    public static double FORWARD_OFFSET = 10; // in; offset of the lateral wheel

    private ExpansionHubEx hub;

    private List<ExpansionHubMotor> motors;

    private List<Double> wheelPositions;

    public StandardTrackingWheelLocalizer(ExpansionHubEx hub, List<ExpansionHubMotor> motors) {
        super(Arrays.asList(
                new Vector2d(0, LATERAL_DISTANCE / 2), // left
                new Vector2d(0, -LATERAL_DISTANCE / 2), // right
                new Vector2d(FORWARD_OFFSET, 0) // front
        ), Arrays.asList(0.0, 0.0, Math.PI / 2));

        this.hub = hub;
        this.motors = motors;

    }

    public static double encoderTicksToInches(int ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }


    @NotNull
    @Override
    public List<Double> getWheelPositions() {
        RevBulkData bulkData = hub.getBulkInputData();

        if (bulkData == null) {
            return Arrays.asList(0.0, 0.0, 0.0);
        }

        List<Double> wheelPositions = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            wheelPositions.add(encoderTicksToInches(bulkData.getMotorCurrentPosition(motors.get(i))));
        }
        return wheelPositions;
    }
}