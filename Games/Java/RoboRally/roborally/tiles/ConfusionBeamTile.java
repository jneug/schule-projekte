package roborally.tiles;

import roborally.Factory;
import roborally.effects.ConfusedEffect;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Eine Kachel, die Robotern den {@link ConfusedEffect} gibt.
 * <p>
 * Befindet sich ein Roboter am Ende einer Anweisung auf der Kachel, wird ihm
 * der Effekt hinzugefügt, sofern er den effekt nicht schon besitzt.
 */
public class ConfusionBeamTile extends Tile {

    /**
     * Erzeugt eine neue ConfusionBeam-Kachel mit den angegebenen Koordinaten
     * und der angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public ConfusionBeamTile( int pX, int pY, Factory pFactory ) {
        super(pX, pY, true, pFactory);
    }

    /**
     * Falls sich ein Roboter auf der Kachel befindet, wird geprüft, ob er schon
     * einen aktiven {@link ConfusedEffect} besitzt. Falls nicht, wird ihm
     * dieser hinzugefügt. Sonst passiert nichts.
     */
    @Override
    public void step() {
        if( robot != null ) {
            robot.getEffects().toFirst();
            while( robot.getEffects().hasAccess() ) {
                // Wurde schon ein RandomMoveEffect hinzugefügt?
                if( robot.getEffects().getContent().getClass().equals(ConfusedEffect.class) ) {
                    return;
                }
            }
            robot.addEffect(new ConfusedEffect());
        }
    }

    @Override
    public void draw( DrawingLayer drawing ) {
        drawing.image("roborally/assets/tile-random.png", x * TILE_SIZE, y * TILE_SIZE, NORTHWEST);
    }

}
