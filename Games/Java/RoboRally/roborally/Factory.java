package roborally;

import roborally.tiles.*;
import schule.ngb.zm.Constants;
import schule.ngb.zm.Options.Direction;
import schule.ngb.zm.layers.DrawingLayer;

public class Factory extends Constants {

    /**
     * Konfiguration: Breite der Fabrikkarte (Anzahl Kacheln).
     */
    public static final int MAP_WIDTH = 30;

    /**
     * Konfiguration: Höhe der Fabrikkarte (Anzahl Kacheln).
     */
    public static final int MAP_HEIGHT = 15;

    private Tile[][] tiles;

    private byte[][] walls;

    private WinTile winTile;

    public Factory() {
        tiles = new Tile[MAP_WIDTH][MAP_HEIGHT];
        walls = new byte[MAP_WIDTH][MAP_HEIGHT];
    }

    public void createMap() {
        // Grundkarte erstellen (Boden mit Wänden am Rand).
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                tiles[i][j] = new FloorTile(i, j, this);
            }
        }

        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                if( randomBool(30) ) {
                    walls[i][j] = (byte) choice(new int[]{0, 1, 2, 3});
                }
            }
        }

        // Zusätzliche Wände
        int a = random(5, 8);
        int b = random(1, 4);
        for( int i = 0; i < 10; i++ ) {
            tiles[a][b + i] = new PitTile(a, b + i, this);
        }

        // Zielkachel platzieren
        int i = random(15, 19);
        int j = random(1, 14);
        winTile = new WinTile(i, j, this);
        tiles[i][j] = winTile;

        // Sonderkacheln platzieren
        int x = a;
        int y = random(12, 13);
        if( b > 2 ) {
            y = random(1, 2);
        }
        tiles[x][y] = new ConfusionBeamTile(x, y, this);

        for( int k = 0; k < 2; k++ ) {
            x = random(9, 10);
            y = random(3, 12);
            tiles[x][y] = new RepairTile(x, y, this);
        }
    }

    public boolean isValidTile( int x, int y ) {
        return x >= 0 && x < MAP_WIDTH && y >= 0 && y < MAP_HEIGHT;
    }

    public boolean hasWall( int x, int y, Direction dir ) {
        if( isValidTile(x, y) ) {
            switch( dir ) {
                case DOWN:
                    return (walls[x][y] & 1) == 1;
                case RIGHT:
                    return (walls[x][y] & 2) == 2;
                case UP:
                    return (y == 0) || (walls[x][y - 1] & 1) == 1;
                case LEFT:
                    return (x == 0) || (walls[x - 1][y] & 2) == 2;
            }
        }
        return false;
    }

    public boolean isPassable( int pX, int pY, Direction pDir ) {
        Tile next = getNextTile(pX, pY, pDir);
        return !hasWall(pX, pY, pDir) && next != null && next.isPassable();
    }

    public boolean isPassable( Tile pTile, Direction pDir ) {
        return isPassable(pTile.getX(), pTile.getY(), pDir);
    }

    public Tile getTile( int x, int y ) {
        if( isValidTile(x, y) ) {
            return tiles[x][y];
        } else {
            return null;
        }
    }

    public Tile getNextTile( int x, int y, Direction dir ) {
        return getTile(x + dir.x, y + dir.y);
    }

    public Tile getNextTile( Tile tile, Direction dir ) {
        return getNextTile(tile.getX(), tile.getY(), dir);
    }

    public WinTile getWinTile() {
        return winTile;
    }

    public void executeTiles() {
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                tiles[i][j].step();
            }
        }
    }

    public void draw( DrawingLayer drawing ) {
        // Kacheln einzeichnen
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                tiles[i][j].draw(drawing);
            }
        }
        // Wände einzeichnen
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                if( hasWall(i, j, RIGHT) ) {
                    drawWall(i, j, RIGHT, drawing);
                }
                if( hasWall(i, j, DOWN) ) {
                    drawWall(i, j, DOWN, drawing);
                }
            }
        }
    }

    private void drawWall( int pX, int pY, Direction pDir, DrawingLayer drawing ) {
        drawing.noStroke();
        if( pDir == RIGHT ) {
            for( int i = 0; i < 8; i += 2 ) {
                drawing.setFillColor(YELLOW);
                drawing.square((pX + 1) * Tile.TILE_SIZE, (pY * Tile.TILE_SIZE) + i * 5 + 3, 5);
                drawing.setFillColor(BLACK);
                drawing.square((pX + 1) * Tile.TILE_SIZE, (pY * Tile.TILE_SIZE) + (i + 1) * 5 + 3, 5);
            }
        }
        if( pDir == DOWN ) {
            for( int i = 0; i < 8; i += 2 ) {
                drawing.setFillColor(YELLOW);
                drawing.square((pX * Tile.TILE_SIZE) + i * 5 + 3, (pY + 1) * Tile.TILE_SIZE, 5);
                drawing.setFillColor(BLACK);
                drawing.square((pX * Tile.TILE_SIZE) + (i + 1) * 5 + 3, (pY + 1) * Tile.TILE_SIZE, 5);
            }
        }
    }

}
