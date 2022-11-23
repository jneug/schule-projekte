package roborally.effects;

import roborally.Robot;
import roborally.tiles.Tile;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

public class RandomMoveEffect extends Effect {

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
