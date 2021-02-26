import ea.*;

/**
 * Die Spielwelt besetht aus 4x3 {@link Karte}n, durch dei die {@link Lunk Spielfigur}
 * wandern kann. Sie enthaät alles, was im Spiel sichtbar ist und interagieren kann.
 *
 * Sie prüft, ob die Bewegung einer Figur erlaub tist und setzt ggf. die Figuren
 * auf eine neue Position (z.B. auch auf eine neue Karte).
 *
 * Der Prototyp enthält als Beispiel eine Karte mit wenig Inhalt und einigen
 * zufälligen Elementen.
 */
public class Welt extends Knoten {

    // Index der aktuelle sichtbaren Karte im Karten-Array
    private int karteX, karteY;

    // Speicher der 12 Karten
    private Karte[][] karten;

    // Referenz zur Spielfigur
    private Lunk lunk;

    // Aktuelle Position der Spielerfigur als Index der aktuellen Karte
    // (Index des Feldes, auf dem Lunk steht.)
    private int lunkX, lunkY;

    public Welt( Lunk pLunk ) {
        lunk = pLunk;

        // Initialisiere die Karten der Welt
        karten = new Karte[4][3];
        for( int i = 0; i < karten.length; i++ ) {
            for (int j = 0; j < karten[0].length; j++) {
                if( i == 2 && j == 2 ) {
                    karten[i][j] = new Karte_0(i, j);
                } else if( i == 0 || j == 0 ) {
                    karten[i][j] = new Karte_Random(i, j);
                } else {
                    karten[i][j] = new Karte(i, j);
                }
            }
        }

        // Zeige die erste Karte, die angezeigt werden soll.
        karteX = 2;
        karteY = 2;
        add(karten[karteX][karteY]);
        karten[karteX][karteY].karteAnzeigen();

        // Setze Lunks Startposition auf der aktuellen Karte.
        lunkX = 10;
        lunkY = 8;
        karten[karteX][karteY].verschiebeZuFeldAnIndex(lunk, lunkX, lunkY);
        add(lunk);
    }

    /**
     * Bewegt den {@ink Lunk Spielercharackter} ein Feld nach links. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeLinks() {
        lunk.zustandSetzen("run_left");
        if (lunk.aktuelleFigur().getX() < 48) {
            if( karteX > 0 ) {
                wechseleKarte(karteX-1, karteY);

                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, 19*48, lunk.getY());
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX()-48, lunk.getY());
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);
            }
        }
        lunk.zustandSetzen("idle_left");
    }

    /**
     * Bewegt den {@ink Lunk Spielercharackter} ein Feld nach rechts. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeRechts() {
        lunk.zustandSetzen("run_right");
        if (lunk.aktuelleFigur().getX() >= 19*48) {
            if( karteX < 3 ) {
                wechseleKarte(karteX+1, karteY);

                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, 0, lunk.getY());
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX()+48, lunk.getY());
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);
            }
        }
        lunk.zustandSetzen("idle_right");
    }

    /**
     * Bewegt den {@ink Lunk Spielercharackter} ein Feld nach oben. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeHoch() {
        lunk.zustandSetzen("run_right");
        if (lunk.aktuelleFigur().getY() < 48) {
            if( karteY > 0 ) {
                wechseleKarte(karteX, karteY-1);

                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, lunk.getX(), 14*48);
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX(), lunk.getY()-48);
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);
            }
        }
        lunk.zustandSetzen("idle_right");
    }

    /**
     * Bewegt den {@ink Lunk Spielercharackter} ein Feld nach unten. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeRunter() {
        lunk.zustandSetzen("run_left");
        if (lunk.aktuelleFigur().getY() >= 14*48) {
            if( karteY < 2 ) {
                wechseleKarte(karteX, karteY+1);

                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, lunk.getX(), 0);
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX(), lunk.getY()+48);
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);
            }
        }
        lunk.zustandSetzen("idle_left");
    }

    /**
     * Tauscht die aktuelle Karte gegen eine neue am Index (i|j) im Karten-Array
     * aus. Gibt es an diesem index keine Karte, dann passiert nichts.
     * @param i Horizontaler Index der neuen Karte
     * @param j Vertikaler Index der neuen Karte
     */
    private void wechseleKarte( int i, int j ) {
        if( i >= 0 || i < karten.length && j >= 0 && j < karten[0].length ) {
            karten[karteX][karteY].karteVerstecken();
            entfernen(karten[karteX][karteY]);
            karteX = i;
            karteY = j;
            add(karten[karteX][karteY]);
            karten[karteX][karteY].karteAnzeigen();

            // Lunk einmal entfernen und wieder adden, damit die Figur
            // nicht von der neuen Karte verdeckt wird.
            entfernen(lunk);
            add(lunk);
        }
    }

}
