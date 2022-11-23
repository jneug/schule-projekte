package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

public class BackwardsInstruction extends Instruction {

    @Override
    public void execute( Robot robot ) {
        robot.moveBackward();
    }

    @Override
    public void draw( int x, int y, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(GREEN);
        drawing.roundedRect(x, y, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-backward.png", x + 35, y + 50, 0.2);
    }

}
