package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Anweisung, um einen Roboter eine Kacheln rückwärts zu bewegen.
 */
public class BackwardsInstruction extends Instruction {

    /**
     * Bewegt den angegebenen Roboter die eine Kacheln rückwärts.
     *
     * @param robot Der Roboter, der die Anweisung ausführt.
     */
    @Override
    public void execute( Robot robot ) {
        robot.moveBackward();
    }

    @Override
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(GREEN);
        drawing.roundedRect(pPixelX, pPixelY, 70, 100, 4, NORTHWEST);
        drawing.image("roborally/assets/arrow-backward.png", pPixelX + 35, pPixelY + 50, 0.2);
    }

}
