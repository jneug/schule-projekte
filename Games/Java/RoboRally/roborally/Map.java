package roborally;

import roborally.tiles.FloorTile;
import roborally.tiles.Tile;
import roborally.tiles.WallTile;
import roborally.tiles.WinTile;
import schule.ngb.zm.Constants;
import schule.ngb.zm.Options;
import schule.ngb.zm.layers.DrawingLayer;

public class Map extends Constants {

    private Tile[][] tiles;

    private WinTile winTile;

    public Map() {
        tiles = new Tile[20][15];
    }

    public void createMap() {
        for( int i = 0; i < 20; i++ ) {
            for( int j = 0; j < 15; j++ ) {
                if( i == 0 || i == 19 || j == 0 || j == 14 ) {
                    tiles[i][j] = new WallTile(i, j, this);
                } else {
                    if( i > 15 && winTile == null && randomBool() ) {
                        winTile = new WinTile(i, j, this);
                        tiles[i][j] = winTile;
                    } else {
                        tiles[i][j] = new FloorTile(i, j, this);
                    }
                }
            }
        }

        if( winTile == null ) {
            int i = random(15,19);
            int j = random(1,14);
            winTile = new WinTile(i, j, this);
            tiles[i][j] = winTile;
        }
    }

    public WinTile getWinTile() {
        return winTile;
    }

    public Tile getTile( int x, int y ) {
        if( x < RoboRallye.MAP_WIDTH && y < RoboRallye.MAP_HEIGHT ) {
            return tiles[x][y];
        } else {
            return null;
        }
    }

    public Tile getNextTile( Tile tile, Options.Direction dir ) {
        return tiles[tile.getX() + dir.x][tile.getY() + dir.y];
    }

    public void executeTiles() {
        for( int i = 0; i < 20; i++ ) {
            for( int j = 0; j < 15; j++ ) {
                tiles[i][j].step();

                if( tiles[i][j].getClass().equals(WinTile.class) && tiles[i][j].hasRobot() ) {
                    // Game won
                }
            }
        }
    }

    public void draw( DrawingLayer drawing ) {
        for( int i = 0; i < 20; i++ ) {
            for( int j = 0; j < 15; j++ ) {
                tiles[i][j].draw(drawing);
            }
        }
    }

}
