import ea.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

    // Referenz auf die Welt, zu der die Karte gehört
    protected Welt welt;

    // Die Felder, aus denen die Karte besteht
    protected Feld[][] felder;

    // Der X-Index der Karte in der Welt
    private int weltX;

    // Der Y-Index der Karte in der Welt
    private int weltY;

    // Liste aller Gegenstände, die sich auf der Karte befinden.
    protected ArrayList<Gegenstand> gegenstaende;

    // Liste aller Gegner, die sich auf der Karte befinden.
    protected ArrayList<Gegner> gegner;

    /**
     * Konstruktor der Karte.
     * @param pX x-Koordinate in der Welt
     * @param pY y-Koordinate in der Welt
     */
    public Karte( int pX, int pY, Welt pWelt ) {
        welt = pWelt;
        weltX = pX;
        weltY = pY;
        felder = new Feld[20][15];

        gegner = new ArrayList<>();
        gegenstaende = new ArrayList<>();

        // Erstelle eine Karte nur aus Grasfeldern
        for( int i = 0; i < felder.length; i++ ) {
            for (int j = 0; j < felder[0].length; j++) {
                felder[i][j] = new Feld(i*48,j*48, "gras");
                add(felder[i][j]);
            }
        }
    }

    /**
     * Gibt die Welt zurück, zu der diese Karte gehört.
     * @return
     */
    public Welt getWelt() {
        return welt;
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
     * Gibt das Feld zurück, dass in der Karte am angegeben Index liegt. Ist der
     * Index außerhalb der Karte, wird {@code null} zurück gegeben.
     * @param i
     * @param j
     * @return
     */
    public Feld feldAnIndex( int i, int j ) {
        if( i >= 0 && i < felder.length  && j >= 0 && j < felder[0].length ) {
            return felder[i][j];
        } else {
            return null;
        }
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
        return feldAnIndex(i, j);
    }

    /**
     * Ermittelt das Feld an den Koordinaten des angegebenen Punktes.
     * @see #feldAnKoordinate(float, float)
     * @param p
     * @return
     */
    public Feld feldAnKoordinate( Punkt p ) {
        return feldAnKoordinate(p.x, p.y);
    }

    /**
     * Verschiebt den angegebenen {@link Raum} (Bild, Knoten, Gegner, ...) auf
     * das angegebene Feld.
     * @param pRaum Das zu verschiebene Objekt
     * @param pFeld Das Zielfeld
     */
    public void verschiebeZuFeld( Raum pRaum, Feld pFeld ) {
        pRaum.mittelpunktSetzen(
            pFeld.zentrum()
        );
    }

    /**
     * Verschiebt den angegebenen {@link Raum} (Bild, Knoten, Gegner, ...) auf
     * das Feld am Index (i|j). Gibt es an diesem Index kein Feld,
     * passiert nichts.
     * @param pRaum
     * @param i
     * @param j
     */
    public void verschiebeZuFeldAnIndex( Raum pRaum, int i, int j ) {
        Feld feld = feldAnIndex(i, j);
        if( feld != null ) {
            verschiebeZuFeld(pRaum, feld);
        }
    }

    /**
     * Verschiebt den angegebenen {@link Raum} (Bild, Knoten, Gegner, ...) auf
     * das Feld an den Koordinaten (x|y). Gibt es zu diesen Koordinaten kein Feld,
     * passiert nichts.
     * @param pRaum
     * @param x
     * @param y
     */
    public void verschiebeZuFeldAnKoordinate( Raum pRaum, float x, float y ) {
        Feld feld = feldAnKoordinate(x, y);
        if( feld != null ) {
            verschiebeZuFeld(pRaum, feld);
        }
    }

    /**
     * Bewegt das angegebene {@link Raum Objekt} ein Feld nach links. Ist das
     * Objekt am Rand der Karte oder ist das Zielfeld nicht
     * {@link Feld#istPassierbar() passierbar}, passiert nichts.
     */
    public void bewegeLinks( Gegner pGegner ) {
        if (pGegner.zentrum().x > 0) {
            Feld feld = feldAnKoordinate(pGegner.zentrum().x-48, pGegner.zentrum().y);
            if( feld != null && feld.istPassierbar() ) {
                verschiebeZuFeld(pGegner, feld);
            }
        }
    }

    /**
     * Bewegt das angegebene {@link Raum Objekt} ein Feld nach rechts. Ist das
     * Objekt am Rand der Karte oder ist das Zielfeld nicht
     * {@link Feld#istPassierbar() passierbar}, passiert nichts.
     */
    public void bewegeRechts( Gegner pGegner ) {
        if (pGegner.zentrum().x < 19*48) {
            Feld feld = feldAnKoordinate(pGegner.zentrum().x+48, pGegner.zentrum().y);
            if( feld != null && feld.istPassierbar() ) {
                verschiebeZuFeld(pGegner, feld);
            }
        }
    }

    /**
     * Bewegt das angegebene {@link Raum Objekt} ein Feld nach oben. Ist das
     * Objekt am Rand der Karte oder ist das Zielfeld nicht
     * {@link Feld#istPassierbar() passierbar}, passiert nichts.
     */
    public void bewegeHoch( Gegner pGegner ) {
        if (pGegner.zentrum().y > 0) {
            Feld feld = feldAnKoordinate(pGegner.zentrum().x, pGegner.zentrum().y-48);
            if( feld != null && feld.istPassierbar() ) {
                verschiebeZuFeld(pGegner, feld);
            }
        }
    }

    /**
     * Bewegt das angegebene {@link Raum Objekt} ein Feld nach unten. Ist das
     * Objekt am Rand der Karte oder ist das Zielfeld nicht
     * {@link Feld#istPassierbar() passierbar}, passiert nichts.
     */
    public void bewegeRunter( Gegner pGegner ) {
        if (pGegner.zentrum().y < 14*48) {
            Feld feld = feldAnKoordinate(pGegner.zentrum().x, pGegner.zentrum().y+48);
            if( feld != null && feld.istPassierbar() ) {
                verschiebeZuFeld(pGegner, feld);
            }
        }
    }

    /**
     * Wird aufgerufen, wenn die Karte von der {@link Welt} angezeigt wird. Also
     * dann, wenn {@link Lunk} sich über den Rand der aktuellen Karte hinaus
     * auf diese Karte bewegt.
     * <p>
     * Hier kann z.B. die Bewegung der Monster gestartet werden, oder die
     * Karte auf einen Startzustand zurückgesetzt werden.
     */
    public void karteAnzeigen() {
        // Starte die Gegner
        for( Gegner g: gegner ) {
            g.start();
        }
    }

    /**
     * Wird aufgerufen, wenn die Karte von der {@link Welt} versteckt wird. Also
     * dann, wenn dies die aktuelle Karte ist und {@link Lunk} sich über den Rand
     * der Karte hinaus auf eine andere Karte bewegt.
     * <p>
     * hier kann zum Beispiel die Bewegung der Monster gestoppt werden, etc.
     */
    public void karteVerstecken() {
        // StStoppe die Gegner
        for( Gegner g: gegner ) {
            g.stopp();
        }
    }

    /**
     * Fügt den Gegenstand dieser Karte auf dem Feld mit dem angegbenen Index hinzu.
     * @param pGegenstand
     */
    public void addGegenstand( int i, int j, Gegenstand pGegenstand ) {
        verschiebeZuFeldAnIndex(pGegenstand, i, j);
        add(pGegenstand);
        gegenstaende.add(pGegenstand);
    }

    /**
     * Fügt den Gegner dieser Karte auf dem dem Feld mit dem angegbenen Index hinzu.
     * @param pGegener
     */
    public void addGegner( int i, int j, Gegner pGegener ) {
        verschiebeZuFeldAnIndex(pGegener, i, j);
        add(pGegener);
        gegner.add(pGegener);
    }

    /**
     * Entfernt einen Gegenstand aus der Karte. Der Gegenstand wird nicht
     * zerstört oder anderweitig modifiziert. Falls dies gewünscht ist,
     * muss explizit im Programm passieren.
     * @param pGegenstand
     */
    public void entferneGegenstand( Gegenstand pGegenstand ) {
        entfernen(pGegenstand);
        gegenstaende.remove(pGegenstand);
    }

    /**
     * Entfernt einen Gegner aus der Karte und dem Spiel. Der Ticker
     * des Gegners wird gestoppt.
     * @param pGegner
     */
    public void entferneGegner( Gegner pGegner ) {
        pGegner.stopp();
        entfernen(pGegner);
        gegner.remove(pGegner);
    }

    /**
     * Gibt eine Liste aller Gegenstände zurück, die sich auf dieser Karte
     * befinden.
     * @return
     */
    public ArrayList<Gegenstand> getGegenstaende() {
        return this.gegenstaende;
    }

    /**
     * Gibt eine Liste aller Gegner zurück, die sich auf dieser Karte befinden.
     * @return
     */
    public ArrayList<Gegner> getGegner() {
        return this.gegner;
    }

    /**
     * Gibt eine Liste aller {@link Gegner} zurück, die sich aktuell auf dem
     * angegebenen Feld befinden.
     * @param pFeld
     * @return
     */
    public ArrayList<Gegner> getGegnerAufFeld( Feld pFeld ) {
        ArrayList<Gegner> list = new ArrayList<>(gegner.size());
        for( Gegner g: gegner ) {
            if (pFeld.beinhaltet(g.zentrum())) {
                list.add(g);
            }
        }
        return list;
    }

    /**
     * Gibt eine Liste aller {@link Gegenstand Gegenstände} zurück, die sich
     * aktuell auf dem angegebenen Feld befinden.
     * @param pFeld
     * @return
     */
    public ArrayList<Gegenstand> getGegenstaendeAufFeld( Feld pFeld ) {
        ArrayList<Gegenstand> list = new ArrayList<>(gegenstaende.size());
        for( Gegenstand g: gegenstaende ) {
            if (pFeld.beinhaltet(g.zentrum())) {
                list.add(g);
            }
        }
        return list;
    }

}
