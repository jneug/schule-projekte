package roborally.tiles;

import roborally.Factory;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Eine Kachel, die Roboter repariert.
 * <p>
 * Befindet sich ein Roboter am Ende einer Anweisung auf der Kachel, wird einer
 * seiner aktiven Effekte entfernt.
 */
public class RepairTile extends Tile {

    /**
     * Erzeugt eine neue Reparaturkachel mit den angegebenen Koordinaten und der
     * angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public RepairTile( int pX, int pY, Factory pFactory ) {
        super(pX, pY, true, pFactory);
    }

    /**
     * Falls sich ein Roboter auf der Kachel befindet wird sein neuster aktiver
     * Effekt entfernt.
     */
    @Override
    public void step() {
        if( robot != null ) {
            robot.getEffects().toFirst();
            robot.getEffects().remove();
        }
    }

    @Override
    public void draw( DrawingLayer drawing ) {
        drawing.image("roborally/assets/tile-repair.png", x * TILE_SIZE, y * TILE_SIZE, NORTHWEST);
    }

}
