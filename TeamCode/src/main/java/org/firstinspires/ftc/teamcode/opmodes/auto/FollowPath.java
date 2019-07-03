package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.lib.hardware.base.Robot;
import org.firstinspires.ftc.teamcode.lib.movement.CurvePoint;
import org.firstinspires.ftc.teamcode.lib.movement.MyPosition;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.lib.movement.RobotMovement.followCurve;


@Autonomous (group = "test")
public class FollowPath extends Robot {

    private final double MOVE_SPEED = 0.5;
    private final double FOLLOW_DISTANCE = 20;

    @Override
    public void init(){
        super.init();

        MyPosition.setPosition(0,0,0);

    }


    @Override
    public void loop(){

        super.loop();

        ArrayList<CurvePoint> allPoints = new ArrayList<>();

        allPoints.add(new CurvePoint(30, 0, MOVE_SPEED, 1.0, FOLLOW_DISTANCE, Math.toRadians(50), 1.0));
        allPoints.add(new CurvePoint(30, 30, MOVE_SPEED, 1.0, FOLLOW_DISTANCE, Math.toRadians(50), 1.0));

        followCurve(allPoints, Math.toRadians(0));
    }


}
