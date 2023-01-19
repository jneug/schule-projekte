package roborally;

import roborally.tiles.*;
import schule.ngb.zm.Color;
import schule.ngb.zm.Constants;
import schule.ngb.zm.Options.Direction;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Die Fabrikkarte, auf der gespielt wird.
 * <p>
 * Eine Fabrik besteht aus einem Feld von {@link Tile Kacheln}, die in Zeilen
 * und Spalten angeordnet sind. Darüber hinaus können sich zwischen zwei Kacheln
 * Wände befinden, die die Bewegung der Roboter verhindern.
 * <p>
 * Auf jeder Fabrikkarte gibt es genau eine {@link WinTile Zielkachel}, die zu
 * befahren das Ziel für die Spieler ist.
 */
public class Factory extends Constants {

    /**
     * Konfiguration: Breite der Fabrikkarte (Anzahl Kacheln).
     */
    public static final int MAP_WIDTH = 30;

    /**
     * Konfiguration: Höhe der Fabrikkarte (Anzahl Kacheln).
     */
    public static final int MAP_HEIGHT = 15;

    /**
     * Das Array der Kacheln.
     */
    private Tile[][] tiles;

    /**
     * Das Array der Wände. Jedes Feld im Array enthält einen der Werte 0, 1, 2
     * oder 3. Als Binärzahl interpretiert beschreiben die Zahlen, ob die Kachel
     * unten und/oder rechts eine Wand hat. Das erste Bit bezieht sich auf die
     * Wand unten und das zweite auf die Wand rechts. Die Werte werden also wie
     * folgt interpretiert:
     * <ul>
     * <li>{@code 0 = 00}: Keine Wände
     * <li>{@code 1 = 01}: Wand unten
     * <li>{@code 2 = 10}: Wand rechts
     * <li>{@code 3 = 11}: Wand unten und rechts
     * </ul>
     * <p>
     * Die Wände einer Kachel mit den Koordinaten {@code (x, y)} werden also
     * durch den Wert im Feld {@code [x][y]} und den Feldern links ({@code [x-1][y]})
     * und oberhalb davon ({@code [x][y-1]}) beschrieben.
     * <p>
     * Die Ränder der Fabrik werden immer als Wände interpretiert.
     */
    private byte[][] walls;

    /**
     * Die Zielkachel.
     */
    private WinTile winTile;

    /**
     * Erstellt eine neue Fabrikkarte.
     * <p>
     * Die Fabrik ist noch leer und muss durch den Aufruf von
     * {@link #createMap()} explizit erzeugt werden.
     */
    public Factory() {
        tiles = new Tile[MAP_WIDTH][MAP_HEIGHT];
        walls = new byte[MAP_WIDTH][MAP_HEIGHT];
    }

    /**
     * Erzeugt die Kacheln und Wände der Karte.
     */
    public void createMap() {
        // Grundkarte erstellen (Boden mit Wänden am Rand).
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                tiles[i][j] = new FloorTile(i, j, this);
            }
        }

        // Zufällige Wände verteilen.
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                if( randomBool(30) ) {
                    walls[i][j] = (byte) choice(new int[]{0, 1, 2, 3});
                }
            }
        }

        // Eine Reihe mit Abgründen als Hindernis erstellen.
        int a = MAP_WIDTH/2;
        int b = random(1, MAP_HEIGHT-12);
        for( int i = 0; i < 10; i++ ) {
            tiles[a][b + i] = new PitTile(a, b + i, this);
        }

        // Zielkachel platzieren.
        int i = random(MAP_WIDTH-10, MAP_WIDTH-3);
        int j = random(2, MAP_HEIGHT-3);
        winTile = new WinTile(i, j, this);
        tiles[i][j] = winTile;

        // Sonderkacheln platzieren.
        int x = a;
        int y = random(12, 13);
        if( b > 2 ) {
            y = random(1, 2);
        }
        tiles[x][y] = new ConfusionBeamTile(x, y, this);
        walls[x][y] = 0;

        for( int k = 0; k < 2; k++ ) {
            x = random(a+2, i-2);
            y = random(3, MAP_HEIGHT-4);
            tiles[x][y] = new RepairTile(x, y, this);
        }
    }

    /**
     * Prüft, ob die angegebenen Koordinaten innerhalb der Fabrikkarte gültig
     * sind.
     * <p>
     * Wenn das Ergebnis {@code true} ist, dann wird ein Aufruf von
     * {@link #getTile(int, int)} mit denselben Werten nicht {@code null} sein.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @return {@code true}, wenn an den Koordinaten eine Kachel existiert,
     *     {@code false} sonst.
     */
    public boolean isValidTile( int pX, int pY ) {
        return pX >= 0 && pX < MAP_WIDTH && pY >= 0 && pY < MAP_HEIGHT;
    }

    /**
     * Prüft, ob die Kachel an den angegebenen Koordinaten in der angegebenen
     * Richtung eine Wand hat.
     * <p>
     * Falls an den angegebenen Koordinaten keine Kachel existiert (@code
     * isValidTile(pX, pY) == false), dann wird immer {@code false}
     * zurückgegeben.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pDir Die Richtung, in die geprüft werden soll.
     * @return {@code true}, wenn die Kachel in der angegebenen Richtung eine
     *     Wand hat.
     */
    public boolean hasWall( int pX, int pY, Direction pDir ) {
        if( isValidTile(pX, pY) ) {
            switch( pDir ) {
                case DOWN:
                    return pY == MAP_HEIGHT - 1 || (walls[pX][pY] & 1) == 1;
                case RIGHT:
                    return pX == MAP_WIDTH - 1 || (walls[pX][pY] & 2) == 2;
                case UP:
                    if( pY - 1 >= 0 ) {
                        return (pY == 0) || (walls[pX][pY - 1] & 1) == 1;
                    }
                case LEFT:
                    if( pX - 1 >= 0 ) {
                        return (pX == 0) || (walls[pX - 1][pY] & 2) == 2;
                    }
            }
        }
        return false;
    }

    /**
     * Fügt eine Wand an der angegebenen Kachel in der angegebenen Richtung
     * ein.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pDir Die Richtung, in die eine Wand eingefügt werden soll.
     */
    public void addWall( int pX, int pY, Direction pDir ) {
        if( isValidTile(pX, pY) ) {
            switch( pDir ) {
                case DOWN:
                    walls[pX][pY] |= 1;
                    break;
                case RIGHT:
                    walls[pX][pY] |= 2;
                    break;
                case UP:
                    if( pY - 1 >= 0 ) {
                        walls[pX][pY - 1] |= 1;
                    }
                    break;
                case LEFT:
                    if( pX - 1 >= 0 ) {
                        walls[pX - 1][pY] |= 2;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Entfernt eine Wand von der angegebenen Kachel in der angegebenen
     * Richtung.
     * <p>
     * Wände am Rand des Feldes können nicht entfernt werden.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pDir Die Richtung, in die eine Wand entfernt werden soll.
     */
    public void removeWall( int pX, int pY, Direction pDir ) {
        if( isValidTile(pX, pY) ) {
            switch( pDir ) {
                case DOWN:
                    walls[pX][pY] &= 2;
                    break;
                case RIGHT:
                    walls[pX][pY] &= 1;
                    break;
                case UP:
                    if( pY - 1 >= 0 ) {
                        walls[pX][pY - 1] &= 2;
                    }
                    break;
                case LEFT:
                    if( pX - 1 >= 0 ) {
                        walls[pX - 1][pY] &= 1;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Prüft, ob es möglich ist, sich von der Kachel mit den angegebenen
     * Koordinaten zur nächsten Kachel in der angegebenen Richtung zu bewgen.
     * <p>
     * Falls an den angegebenen Koordinaten keine Kachel existiert
     * ({@code isValidTile(pX, pY) == false}), zwischen der angegebenen Kachel
     * und der nächsten eine Wand besteht
     * ({@code hasWall(pX, pY, pDir) == true}) oder die Zielkachel nicht
     * befahrbar ist ({@code getNextTile(pX, pY, pDir).isPassable() == false}),
     * wird {@code false} zurückgegeben.
     * <p>
     * Es wird <em>nicht</em> berücksichtigt, ob sich auf dem Zielfeld ein
     * anderer Roboter befindet, der gegebenenfalls nicht verschoben werden
     * kann.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pDir Die Richtung, in die geprüft werden soll.
     * @return {@code true}, wenn die Kachel in der angegebenen Richtung eine
     *     Wand hat.
     */
    public boolean isPassable( int pX, int pY, Direction pDir ) {
        Tile next = getNextTile(pX, pY, pDir);
        return !hasWall(pX, pY, pDir) && next != null && next.isPassable();
    }

    /**
     * Prüft, ob es möglich ist, sich von der angegebenen Kachel zur nächsten
     * Kachel in der angegebenen Richtung zu bewgen.
     * <p>
     * Falls zwischen der angegebenen Kachel und der nächsten eine Wand besteht
     * ({@code hasWall(pTile.getX(), pTile.getY(), pDir) == true}) oder die
     * Zielkachel nicht befahrbar ist
     * ({@code getNextTile(pTile, pDir).isPassable() == false}), wird
     * {@code false} zurückgegeben.
     * <p>
     * Es wird <em>nicht</em> berücksichtigt, ob sich auf dem Zielfeld ein
     * anderer Roboter befindet, der gegebenenfalls nicht verschoben werden
     * kann.
     *
     * @param pTile Die Kachel, von der aus geprüft werden soll.
     * @param pDir Die Richtung, in die geprüft werden soll.
     * @return {@code true}, wenn die Kachel in der angegebenen Richtung eine
     *     Wand hat.
     * @see #isPassable(int, int, Direction)
     */
    public boolean isPassable( Tile pTile, Direction pDir ) {
        return isPassable(pTile.getX(), pTile.getY(), pDir);
    }

    /**
     * Ermittelt die Kachel mit den angegebenen Koordinaten.
     * <p>
     * Wenn es an den angegebenen Koordinaten keine Kachel gibt
     * ({@code isValidTile(pX, pY) == false}), dann wird {@code null}
     * zurückgegeben.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @return Die Kachel mit den angegbenen Koordinaten oder {@code null}.
     */
    public Tile getTile( int pX, int pY ) {
        if( isValidTile(pX, pY) ) {
            return tiles[pX][pY];
        } else {
            return null;
        }
    }

    /**
     * Ermittelt die nächste Kachel in der angegebenen Richtung ausgehen von der
     * Kachel an den angegebnen Koordinaten.
     * <p>
     * Gibt es keine Kachel in der angegebenen Richtung, dann wird {@code null}
     * zurückgegeben.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @return Die Nachbarkachel in der angegbenen Richtung oder {@code null}.
     */
    public Tile getNextTile( int pX, int pY, Direction pDir ) {
        return getTile(pX + pDir.x, pY + pDir.y);
    }

    /**
     * Ermittelt die nächste Kachel in der angegebenen Richtung ausgehen von der
     * angegebnen Kachel.
     * <p>
     * Gibt es keine Kachel in der angegebenen Richtung, dann wird {@code null}
     * zurückgegeben.
     *
     * @param pTile Die Kachel, von der aus geprüft werden soll.
     * @return Die Nachbarkachel in der angegbenen Richtung oder {@code null}.
     * @see #getNextTile(int, int, Direction)
     */
    public Tile getNextTile( Tile pTile, Direction pDir ) {
        return getNextTile(pTile.getX(), pTile.getY(), pDir);
    }

    /**
     * @return Die Zielkachel dieser Fabrik.
     */
    public WinTile getWinTile() {
        return winTile;
    }

    /**
     * Führt die Funktionen aller Kacheln in dieser Fabrikkarte aus.
     * <p>
     * Die Kacheln werden von links oben ({@code [0][0]}) zeilenweise bis zur
     * letzten Kachel rechts unten ausgeführt.
     */
    public void executeTiles() {
        for( int i = 0; i < MAP_WIDTH; i++ ) {
            for( int j = 0; j < MAP_HEIGHT; j++ ) {
                tiles[i][j].step();
            }
        }
    }

    /**
     * Zeichnet die Fabrikkarte auf die Zeichenfläche.
     * <p>
     * Die Kacheln werden von links oben ({@code [0][0]}) zeilenweise bis zur
     * letzten Kachel rechts unten gezeichnet. Danach werden die Wände in der
     * gleichen Reihenfolge eingezeichnet.
     *
     * @param drawing Die Zeichenfläche.
     */
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
                if( i == 0 ) {
                    drawWall(i - 1, j, RIGHT, drawing);
                }
                if( j == 0 ) {
                    drawWall(i, j - 1, DOWN, drawing);
                }
                if( hasWall(i, j, RIGHT) ) {
                    drawWall(i, j, RIGHT, drawing);
                }
                if( hasWall(i, j, DOWN) ) {
                    drawWall(i, j, DOWN, drawing);
                }
            }
        }
    }

    /**
     * Hilfsmethode, um eine Wand zwischen der Kachel an der angegebenen
     * Koordinate und der Nachbarkachel in der angegebenen Richtung auf die
     * Zeichenfläche zu zeichnen.
     * <p>
     * Als Richtung werden nur {@link Direction#RIGHT RIGHT} und
     * {@link Direction#DOWN DOWN} akzeptiert.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pDir Die Richtung der Wand ({@code RIGHT} oder {@code UP}).
     * @param drawing Die Zeichenfläche.
     */
    private void drawWall( int pX, int pY, Direction pDir, DrawingLayer drawing ) {
        // Wände werden nur rechts und unten gezeichnet
        if( pDir != RIGHT && pDir != DOWN ) {
            return;
        }

        drawing.noStroke();

        Color[] clr = new Color[]{YELLOW, BLACK};
        for( int i = 0; i < 8; i += 1 ) {
            int size = i * 5 + 3;

            drawing.setFillColor(clr[i % 2]);
            drawing.square(
                (pX + pDir.x) * Tile.TILE_SIZE + size * pDir.y,
                (pY + pDir.y) * Tile.TILE_SIZE + size * pDir.x,
                5
            );
        }
    }

}
