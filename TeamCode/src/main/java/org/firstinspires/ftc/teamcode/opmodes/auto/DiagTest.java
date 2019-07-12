package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class DiagTest extends OpMode {

    DcMotor bl, fr;

    @Override
    public void init(){
        bl = hardwareMap.get(DcMotor.class, "bl");
        fr = hardwareMap.get(DcMotor.class, "fr");

        fr.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop(){
        bl.setPower(0.4);
        fr.setPower(-0.5);
    }

    public void strafe(double power){

    }

}
