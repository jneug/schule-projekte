package roborally.tiles;

import roborally.Map;
import roborally.RoboRallye;
import schule.ngb.zm.layers.DrawingLayer;

public class WallTile extends Tile {

    public WallTile( int x, int y, Map map ) {
        super(x, y, false, map);
    }

    @Override
    public void step() {
        // Nothing
    }

    @Override
    public void draw( DrawingLayer drawing ) {
        drawing.setFillColor(BLACK);
        drawing.square(x * RoboRallye.TILE_SIZE, y * RoboRallye.TILE_SIZE, RoboRallye.TILE_SIZE, NORTHWEST);
    }

}
