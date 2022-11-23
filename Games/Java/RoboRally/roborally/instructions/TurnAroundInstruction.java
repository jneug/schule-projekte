package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

public class TurnAroundInstruction extends Instruction {

    @Override
    public void execute( Robot robot ) {
        robot.turnLeft();
        robot.turnLeft();
    }

    @Override
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(YELLOW);
        drawing.roundedRect(pPixelX, pPixelY, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-around.png", pPixelX +35, pPixelY +50, 0.2);
    }

}
