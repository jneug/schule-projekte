package roborally.instructions;

import roborally.Robot;
import roborally.instructions.Instruction;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Anweisung, um einen Roboter 90 Grad nach rechts zu drehen.
 */
public class TurnRightInstruction extends Instruction {

    /**
     * Dreht den angegebnen Roboter 90 Grad nach rechts.
     *
     * @param robot Der Roboter, der die Anweisung ausführt.
     */
    @Override
    public void execute( Robot robot ) {
        robot.turnRight();
    }

    @Override
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(YELLOW);
        drawing.roundedRect(pPixelX, pPixelY, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-right.png", pPixelX + 35, pPixelY + 50, 0.2);
    }

}
