package roborally.tiles;

import roborally.Factory;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Die Zielkachel, die von den Robotern erreicht werden muss.
 * <p>
 * Die Zielkachel wird in jedem Zug geprüft. Der Spieler des ersten Roboters,
 * der die Kachel betritt, gewinnt das Spiel.
 * <p>
 * Darüber hinaus hat die Kachel keine Funktion.
 */
public class WinTile extends Tile {

    /**
     * Erzeugt eine neue Zielkachel mit den angegebenen Koordinaten und der
     * angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public WinTile( int pX, int pY, Factory pFactory ) {
        super(pX, pY, true, pFactory);
    }

    /**
     * Leere Methode: Die Kachel hat keine Funktion.
     */
    @Override
    public void step() {
        // Nothing
    }

    @Override
    public void draw( DrawingLayer drawing ) {
        drawing.image("roborally/assets/tile-floor.png", getPixelX(), getPixelY(), NORTHWEST);
        drawing.setFillColor(GREEN, 100);
        drawing.noStroke();
        drawing.square(getPixelX(), getPixelY(), TILE_SIZE, NORTHWEST);
    }

}
