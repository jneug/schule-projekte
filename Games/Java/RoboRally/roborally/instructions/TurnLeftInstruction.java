package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

public class TurnLeftInstruction extends Instruction {

    @Override
    public void step( Robot robot ) {
        robot.turnLeft();
    }

    @Override
    public void draw( int x, int y, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(YELLOW);
        drawing.roundedRect(x, y, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-left.png", x+35, y+50, 0.2);
    }

}
