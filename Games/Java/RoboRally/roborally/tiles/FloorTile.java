package roborally.tiles;

import roborally.Map;

public class FloorTile extends Tile {

    public FloorTile( int x, int y, Map map ) {
        super(x, y, true, map);
    }

    @Override
    public void step() {
        // Nothing
    }

}
