package roborally.tiles;

import roborally.Map;
import roborally.RoboRallye;
import schule.ngb.zm.layers.DrawingLayer;

public class WinTile extends Tile {

    public WinTile( int x, int y, Map map ) {
        super(x, y, map);
    }

    @Override
    public void step() {
        // Nothing
    }

    @Override
    public void draw( DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(1);
        drawing.setFillColor(RED.brighter(60));
        drawing.square(x * RoboRallye.TILE_SIZE, y * RoboRallye.TILE_SIZE, RoboRallye.TILE_SIZE, NORTHWEST);
    }

}
