package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.lib.hardware.base.DriveTrain;
import org.firstinspires.ftc.teamcode.lib.hardware.base.RevMotor;
import org.firstinspires.ftc.teamcode.lib.hardware.base.Robot;
import org.firstinspires.ftc.teamcode.lib.movement.MyPosition;
import org.openftc.revextensions2.ExpansionHubEx;
import org.openftc.revextensions2.ExpansionHubMotor;
import org.openftc.revextensions2.RevBulkData;
import org.openftc.revextensions2.RevExtensions2;

import static org.firstinspires.ftc.teamcode.lib.movement.MyPosition.PosCalc7571;
import static org.firstinspires.ftc.teamcode.lib.movement.MyPosition.worldXPosition;

@Autonomous
public class FollowSimple extends OpMode {

    DriveTrain dt = new DriveTrain();

    private RevBulkData revExpansionMasterBulkData;

    private ExpansionHubEx revMaster;

    private RevMotor[] motors;


    @Override
    public void init(){

        RevExtensions2.init();

        revMaster = hardwareMap.get(ExpansionHubEx.class,"Expansion Hub 2");

        motors = new RevMotor[]{new RevMotor((ExpansionHubMotor) hardwareMap.get("fl"),true), new RevMotor((ExpansionHubMotor) hardwareMap.get("fr"),true), new RevMotor((ExpansionHubMotor) hardwareMap.get("bl"),true), new RevMotor((ExpansionHubMotor) hardwareMap.get("br"),true)};

        dt.initMotors(motors);

        MyPosition.setPosition(0, 0, 0);

    }

    @Override
    public void loop(){

        getRevBulkData();

        //dt.applyMovement();

        MyPosition.PosCalc7571(
                dt.fr.getCurrentPosition(),
                dt.fl.getCurrentPosition(),
                dt.bl.getCurrentPosition());

        if((worldXPosition <= -30)){
            dt.setThrottle(0);
        } else {
            dt.setThrottle(0.4);
        }

        telemetry.addLine("wx count: " + worldXPosition);
        telemetry.update();


    }

    public void getRevBulkData() {
//        boolean needToPollMaster = !AutoFeeder.canPollMasterAtLowerRate ||
//            currTimeMillis-lastUpdateMasterTime > 300;
//        if(needToPollMaster){
        RevBulkData newDataMaster;
        try{
            newDataMaster = revMaster.getBulkInputData();
            if(newDataMaster != null){
                revExpansionMasterBulkData = newDataMaster;
            }
        }catch(Exception e){
            //don't set anything if we get an exception
        }


        for(RevMotor revMotor : motors) {
            if (revMotor == null) {
                continue;
            }
            if (revExpansionMasterBulkData != null) {
                revMotor.setEncoderReading(revExpansionMasterBulkData.getMotorCurrentPosition(revMotor.myMotor));
            }
        }

    }

}
