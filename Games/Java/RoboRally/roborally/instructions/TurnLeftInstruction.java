package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Anweisung, um einen Roboter 90 Grad nach links zu drehen.
 */
public class TurnLeftInstruction extends Instruction {

    /**
     * Dreht den angegebnen Roboter 90 Grad nach links.
     *
     * @param robot Der Roboter, der die Anweisung ausführt.
     */
    @Override
    public void execute( Robot robot ) {
        robot.turnLeft();
    }

    @Override
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(YELLOW);
        drawing.roundedRect(pPixelX, pPixelY, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-left.png", pPixelX + 35, pPixelY + 50, 0.2);
    }

}
