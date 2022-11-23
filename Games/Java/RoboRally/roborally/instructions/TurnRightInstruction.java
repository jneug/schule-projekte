package roborally.instructions;

import roborally.Robot;
import roborally.instructions.Instruction;
import schule.ngb.zm.layers.DrawingLayer;

public class TurnRightInstruction extends Instruction {

    @Override
    public void execute( Robot robot ) {
        robot.turnRight();
    }

    @Override
    public void draw( int x, int y, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(YELLOW);
        drawing.roundedRect(x, y, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-right.png", x+35, y+50, 0.2);
    }

}
