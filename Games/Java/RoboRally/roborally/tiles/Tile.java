package roborally.tiles;

import roborally.Factory;
import roborally.Robot;
import schule.ngb.zm.Constants;
import schule.ngb.zm.layers.DrawingLayer;

/**
 * Basisklasse für eine Kachel auf dem Spielfeld.
 * <p>
 * Kacheln gehören zu genau einer {@link Factory}, die beim Erstellen eines
 * Instanz angegeben werden muss.
 * <p>
 * Jede Kachel ist quadratisch und besitzt eine {@code x}- und eine
 * {@code y}-Koordinate, die die Zeilen und Spaltennummer darstellen. Die
 * Pixelkoordinaten werden abhängig von der KOnstanten {@link #TILE_SIZE}
 * berechnet:
 *
 * <pre>
 * int pixelX = x * TILE_SIZE;
 * int pixelY = y * TILE_SIZE;
 * </pre>
 * <p>
 * Kacheln können {@link #isPassable() passierbar} sein, oder nicht. Passierbare
 * Kacheln können von {@link Robot Robotern} befahren werden. Auf jeder Kachel
 * kann sich maximal ein Roboter gleichzeitig befinden.
 * <p>
 * Kacheln können besondere Funktionen haben, die die Spielsituation
 * beeinflussen. Eine Kachel könnte zum Beispiel einen Roboter reparieren oder
 * ihn zur Seite stoßen. Kacheln werden jedesmal ausgeführt, wenn <em>alle</em>
 * Roboter eine Anweisung ausgeführt haben.
 * <p>
 * Unterklassen müssen einen passenden Konstruktor und die {@link #step()}
 * Methode implementieren, die die Funktion der Kachel implementiert. In der
 * Regel sollte auch die {@link #draw(DrawingLayer)} Methode implementiert
 * werden, um die Kachel unterschiedlich darzustellen.
 */
public abstract class Tile extends Constants {

    /**
     * Konfiguration: Kantenlänge einer Kachel.
     */
    public static final int TILE_SIZE = 40;


    /**
     * Die Fabrikkarte, zu der diese Kachel gehört.
     */
    private Factory factory;

    /**
     * Die Spaltennummer der Kachel.
     */
    protected int x;

    /**
     * Die Zeilennummer der Kachel.
     */
    protected int y;

    /**
     * Referenz auf einen Roboter, der sich auf der Kachel befindet, oder
     * {@code null}.
     */
    protected Robot robot;

    /**
     * Ob diese Kachel befahrbar ist.
     */
    protected boolean passable;

    /**
     * Erzeugt eine neue Kachel mit den angegebenen Koordinaten und der
     * angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public Tile( int pX, int pY, Factory pFactory ) {
        this.x = pX;
        this.y = pY;
        this.factory = pFactory;
        passable = true;
    }

    /**
     * Erzeugt eine neue Kachel mit den angegebenen Koordinaten und der
     * angegebenen Fabrik.
     *
     * @param pX Die Spaltennummer der Kachel.
     * @param pY Die Zeilennummer der Kachel.
     * @param pPassable Ob diese Kachel befahrbar ist, oder nicht.
     * @param pFactory Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public Tile( int pX, int pY, boolean pPassable, Factory pFactory ) {
        this.x = pX;
        this.y = pY;
        this.factory = pFactory;
        this.passable = pPassable;
    }

    /**
     * @return Die Fabrikkarte, zu der diese Kachel gehört.
     */
    public Factory getFactory() {
        return factory;
    }

    /**
     * @return Die Spaltennummer der Kachel.
     */
    public int getX() {
        return x;
    }

    /**
     * @return Die Zeilennummer der Kachel.
     */
    public int getY() {
        return y;
    }

    /**
     * @return Die pixelgenaue x-Koordinate auf der Zeichenfläche.
     */
    public int getPixelX() {
        return x * TILE_SIZE;
    }

    /**
     * @return Die pixelgenaue y-Koordinate auf der Zeichenfläche.
     */
    public int getPixelY() {
        return y * TILE_SIZE;
    }

    /**
     * Gibt an, ob diese Kachel befahrbar ist.
     *
     * @return {@code true}, wenn die Kachel befahrbar ist, {@code false} sonst.
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Prüft, ob sich ein Roboter auf dieser Kachel befindet.
     *
     * @return {@code true}, wenn sich ein Roboter auf der Kachel befindet,
     *     {@code false} sonst.
     */
    public boolean hasRobot() {
        return robot != null;
    }

    /**
     * Gibt den Roboter zurück, der sich auf dieser Kachel befindet. Falls kein
     * Roboter vorhanden ist, wird {@code null} zurückgegeben.
     *
     * @return Ein Roboter oder {@code null}.
     */
    public Robot getRobot() {
        return robot;
    }

    /**
     * Setzt die Referenz auf einen Roboter, der sich auf der Kachel befindet.
     * {@code null} bedeutet, dass sich kein Roboter (mehr) auf dieser Kachel
     * befindet.
     *
     * @param pRobot Der Roboter auf dieser Kachel oder {@code null}.
     * @see Robot#setTile(Tile)
     */
    public void setRobot( Robot pRobot ) {
        this.robot = pRobot;
    }

    /**
     * Führt die Funktion der Kachel einmal aus.
     * <p>
     * Eine Funktion sollte ein kleiner, lokaler Effekt sein, der sich in der
     * Regel auf den Roboter auf dem Feld auswirkt (z.B. Roboter drehen). Eine
     * Kachel kann aber auch bei jedem Aufruf ihren Zustand ändern (z.B. eine
     * Tür, die öffnet und schließt).
     */
    public abstract void step();

    /**
     * Zeichnet die Kachel auf die Zeichenfläche.
     * <p>
     * Jede Kachel sollte eine eindeutige Darstellung im Spiel haben, die ihren
     * Effekt hervorhebt. Als Standard wird ein graues Quadrat dargestellt.
     *
     * @param drawing Die Zeichenfläche.
     */
    public void draw( DrawingLayer drawing ) {
        drawing.setStrokeColor(DARKGRAY);
        drawing.setStrokeWeight(1);
        drawing.setFillColor(LIGHTGRAY);
        drawing.square(getPixelX(), getPixelY(), TILE_SIZE, NORTHWEST);
    }

}
