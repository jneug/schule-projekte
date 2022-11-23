package roborally.tiles;

import roborally.Factory;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Eine nicht befahrbare Kachel mit einem Abgrund.
 */
public class PitTile extends Tile {

    /**
     * Erzeugt einen neuen Abgrunf mit den angegebenen Koordinaten und der
     * angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public PitTile( int pX, int pY, Factory pFactory ) {
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
        drawing.image("roborally/assets/tile-pit.png", getPixelX(), getPixelY(), NORTHWEST);
    }

}
