package roborally.tiles;

import roborally.Factory;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Eine normale Fußbodenkachel ohne besondere Funktion.
 */
public class FloorTile extends Tile {

    /**
     * Erzeugt eine neue Fußbodenkachel mit den angegebenen Koordinaten und der
     * angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public FloorTile( int pX, int pY, Factory pFactory ) {
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
    }

}
