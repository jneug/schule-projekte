package roborally.effects;

import roborally.Robot;
import roborally.tiles.Tile;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Ein Effekt, der den Roboter verwirt und ihn eine zufällige Bewegung ausführen
 * lässt.
 * <p>
 * Sobald ein Roboter diesen Effekt erhält, fährt er jedes Mal, wenn der Effekt
 * angewandt wird, mit einer zehnprozentigen Wahrscheinlichkeit eine Kachel vor
 * oder zurück.
 */
public class ConfusedEffect extends Effect {

    /**
     * Lässt den Roboter mit einer Wahrscheinlichkeit von 10% eine Kachel vor
     * oder zurück fahren.
     * <p>
     * Effektiv fährt der Roboter also mit einer Wahrscheinlichkeit von 5% vor
     * und mit einer Wahrscheinlichkeit von 5% zurück.
     *
     * @param robot Der Roboter, auf den der Effekt wirkt.
     */
    @Override
    public void apply( Robot robot ) {
        if( Constants.randomBool(10) ) {
            if( Constants.randomBool() ) {
                robot.moveForward();
            } else {
                robot.moveBackward();
            }
        }
    }

    @Override
    public void draw( Robot robot, DrawingLayer drawing ) {
        int x = robot.getTile().getX();
        int y = robot.getTile().getY();

        drawing.setStrokeColor(BLACK);
        drawing.setStrokeWeight(1);
        drawing.setFillColor(YELLOW);
        drawing.setFontSize(11);
        drawing.circle(x * Tile.TILE_SIZE + 10, y * Tile.TILE_SIZE + 10, 10);
        drawing.text("?", x * Tile.TILE_SIZE + 10, y * Tile.TILE_SIZE + 10);
    }

}
