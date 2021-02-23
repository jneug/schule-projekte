import ea.*;

/**
 * Eine Karte besteht aus 20 mal 15 {@link Feld}ern und stellt einen Ausschnitt
 * der {@link Welt} dar. Eine Karte hat bis zu vier Nachbarkarten zu denen man
 * kommt, wenn man den Rand der Karte zu einer der Himmelsrichtungen überschreitet.
 *
 * Die Karten im Prototyp werden zufällig ohne Sinn erstellt. Für ein richtiges
 * Spiel sollten die Karten "von Hand" gebaut werden, indem Felder mit passendem
 * Untergrund gezielt positioniert werden. (Siehe das Beispiel {@link Karte_0}.)
 */
public class Karte extends Knoten {

    protected Feld[][] felder;

    private int weltX;

    private int weltY;

    /**
     * Konstruktor der Karte.
     * @param pX x-Koordinate in der Welt
     * @param pY y-Koordinate in der Welt
     */
    public Karte( int pX, int pY ) {
        weltX = pX;
        weltY = pY;
        felder = new Feld[20][15];

        for( int i = 0; i < felder.length; i++ ) {
            for (int j = 0; j < felder[0].length; j++) {
                felder[i][j] = new Feld(i*48,j*48, "gras");
                add(felder[i][j]);
            }
        }
    }

    /**
     * Gibt die x-Position der Karte im Welt-Array zurück.
     * @return
     */
    public int getWeltX() {
        return weltX;
    }

    /**
     * Gibt die y-Position der Karte im Welt-Array zurück.
     * @return
     */
    public int getWeltY() {
        return weltY;
    }

    /**
     * Gibt das Array aller Felder zurück.
     * @return
     */
    public Feld[][] getFelder() {
        return felder;
    }

    /**
     * Gibt das Feld am angegeben Index zurück.
     * @param pX
     * @param pY
     * @return
     */
    public Feld getFeld(int pX, int pY) {
        return felder[pX][pY];
    }

    /**
     * Gibt das Feld zurück, in dem die angegebenen Koordinaten liegen. Sind die
     * Koordinaten außerhalb der Karte, wird {@code null} zurück gegeben.
     * @param x
     * @param y
     * @return
     */
    public Feld feldAnKoordinate( float x, float y ) {
        int i = (int) (x/48);
        int j = (int) (y/48);
        if( i >= 0 && i < felder.length  && j >= 0 && j < felder[0].length ) {
            return felder[i][j];
        } else {
            return null;
        }
    }

}
