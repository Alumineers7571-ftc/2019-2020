package org.firstinspires.ftc.teamcode.opmodes.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.lib.hardware.base.Robot;
import org.firstinspires.ftc.teamcode.lib.movement.MyPosition;
import org.firstinspires.ftc.teamcode.lib.movement.Point;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.lib.movement.RobotMovement.applyTarget;
import static org.firstinspires.ftc.teamcode.lib.util.GlobalVars.*;

@Autonomous
public class GoToPoint extends Robot {

  enum AutoStates{
    START, MOVE, MOVE2, END
  }

  AutoStates states = AutoStates.START;

  ArrayList<Point> points = new ArrayList<>();

  @Override
  public void init(){
    super.init();

    MyPosition.setPosition(0,0,0);

    points.add(new Point(50,50));

  }

  @Override
  public void loop(){
    super.loop();

    switch (states){
      case START:{

        states = AutoStates.MOVE;
        break;
      }
      case MOVE:{

        applyTarget(points.get(0));

        if(atTarget) {
          states = AutoStates.MOVE2;
        }
        break;
      }
      case MOVE2:{
        applyTarget(new Point(0, 0));

        if(atTarget){
          states = AutoStates.END;
        }
        break;
      }
      case END:{

      }
    }


  }

}
