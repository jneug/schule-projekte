package roborally.instructions;

import roborally.Robot;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Anweisung, um einen Roboter bis zu drei Kacheln vorwärts zu bewegen.
 */
public class ForwardInstruction extends Instruction {

    /**
     * Anzahl der Kacheln, um die sich der Roboter bewegt.
     */
    protected int tiles = 1;

    /**
     * Erstellt eine neue Anweisung mit einer Bewegung um eine Kachel.
     */
    public ForwardInstruction() {

    }

    /**
     * Erstellt eine neue Anweisung mit einer Bewegung um die angegeben Anzahl
     * Kacheln.
     *
     * @param pTiles Die Anzahl Kacheln zwischen 1 und 3.
     */
    public ForwardInstruction( int pTiles ) {
        if( pTiles > 0 && pTiles <= 3 ) {
            tiles = pTiles;
        } else {
            tiles = 1;
        }
    }

    /**
     * Bewegt den angegebenen Roboter die angegeben Anzahl Kacheln vorwärts.
     * <p>
     * Stößt der Roboter auf ein Hindernis, das die Bewegung verhindert, bleibt
     * der Roboter stehen und der Rest der Bewegung verfällt.
     *
     * @param pRobot Der Roboter, der die Anweisung ausführt.
     */
    @Override
    public void execute( Robot pRobot ) {
        for( int i = 0; i < tiles; i++ ) {
            if( !pRobot.moveForward() ) {
                return;
            }
        }
    }

    @Override
    public void draw( int pPixelX, int pPixelY, DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(4);
        drawing.setFillColor(GREEN);
        drawing.roundedRect(pPixelX, pPixelY, 70, 100, 4, NORTHWEST);
        drawing.setStrokeWeight(1);
        drawing.setFillColor(GREEN.darker());
        drawing.circle(pPixelX + 70, pPixelY, 15, NORTHEAST);
        drawing.setFontSize(16);
        drawing.text(tiles + "", pPixelX + 55, pPixelY + 15);
        drawing.image("roborally/assets/arrow-forward.png", pPixelX + 35, pPixelY + 50, 0.2);
    }

}
