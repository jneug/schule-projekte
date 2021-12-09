import ea.*;

import java.util.ArrayList;

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
        karten = new Karte[Zulda.WORLD_WIDTH][Zulda.WORLD_HEIGHT];
        for( int i = 0; i < karten.length; i++ ) {
            for (int j = 0; j < karten[0].length; j++) {
                if( i == 2 && j == 2 ) {
                    karten[i][j] = new Karte_0(i, j, this);
                } else if( i == 0 || j == 0 ) {
                    karten[i][j] = new Karte_Random(i, j,  this);
                } else {
                    karten[i][j] = new Karte(i, j, this);
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
     * Gibt den Spielercharakter zurück.
     * @return
     */
    public Lunk getSpieler() {
        return lunk;
    }

    /**
     * Bewegt den {@ink Lunk Spielercharakter} ein Feld nach links. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeLinks() {
        lunk.zustandSetzen("run_left");
        if (lunk.aktuelleFigur().getX() < Zulda.TILE_SIZE) {
            if( karteX > 0 ) {
                wechseleKarte(karteX-1, karteY);

                int newX = (Zulda.MAP_WIDTH-1)*Zulda.TILE_SIZE;
                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, newX, lunk.getY());
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX()-Zulda.TILE_SIZE, lunk.getY());
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);

                // Gegenstände auf dem neuen Feld einsammeln
                for( Gegenstand g: karten[karteX][karteY].getGegenstaendeAufFeld(feld) ) {
                    g.einsammeln(lunk);
                }
            }
        }
        lunk.zustandSetzen("idle_left");
    }

    /**
     * Bewegt den {@ink Lunk Spielercharakter} ein Feld nach rechts. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeRechts() {
        lunk.zustandSetzen("run_right");
        if (lunk.aktuelleFigur().getX() >= (Zulda.MAP_WIDTH-1)*Zulda.TILE_SIZE) {
            if( karteX < 3 ) {
                wechseleKarte(karteX+1, karteY);

                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, 0, lunk.getY());
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX()+Zulda.TILE_SIZE, lunk.getY());
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);

                // Gegenstände auf dem neuen Feld einsammeln
                for( Gegenstand g: karten[karteX][karteY].getGegenstaendeAufFeld(feld) ) {
                    g.einsammeln(lunk);
                }
            }
        }
        lunk.zustandSetzen("idle_right");
    }

    /**
     * Bewegt den {@ink Lunk Spielercharakter} ein Feld nach oben. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeHoch() {
        lunk.zustandSetzen("run_right");
        if (lunk.aktuelleFigur().getY() < Zulda.TILE_SIZE) {
            if( karteY > 0 ) {
                wechseleKarte(karteX, karteY-1);

                int newY = (Zulda.MAP_HEIGHT-1)*Zulda.TILE_SIZE;
                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, lunk.getX(), newY);
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX(), lunk.getY()-Zulda.TILE_SIZE);
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);

                // Gegenstände auf dem neuen Feld einsammeln
                for( Gegenstand g: karten[karteX][karteY].getGegenstaendeAufFeld(feld) ) {
                    g.einsammeln(lunk);
                }
            }
        }
        lunk.zustandSetzen("idle_right");
    }

    /**
     * Bewegt den {@ink Lunk Spielercharakter} ein Feld nach unten. Überschreitet
     * die Figur den Rand der Karte, wird die aktuelle Karte gewechselt, sofern
     * nicht der Rand der Welt erreicht wurde. Ist das Feld nicht passierbar
     * oder der Rand der Welt erricht, passiert nichts.
     */
    public void bewegeRunter() {
        lunk.zustandSetzen("run_left");
        if (lunk.aktuelleFigur().getY() >= (Zulda.MAP_HEIGHT-1)*Zulda.TILE_SIZE) {
            if( karteY < 2 ) {
                wechseleKarte(karteX, karteY+1);

                karten[karteX][karteY].verschiebeZuFeldAnKoordinate(lunk, lunk.getX(), 0);
            }
        } else {
            Feld feld = karten[karteX][karteY].feldAnKoordinate(lunk.getX(), lunk.getY()+Zulda.TILE_SIZE);
            // feld ist ungleich null, da sonst nicht der else-Zweig ausgeführt werden würde
            if( feld.istPassierbar() ) {
                karten[karteX][karteY].verschiebeZuFeld(lunk, feld);

                // Gegenstände auf dem neuen Feld einsammeln
                for( Gegenstand g: karten[karteX][karteY].getGegenstaendeAufFeld(feld) ) {
                    g.einsammeln(lunk);
                }
            }
        }
        lunk.zustandSetzen("idle_left");
    }

    /**
     * Lässt den {@ink Lunk Spielercharakter} alle Gegner auf dem Feld rechts
     * von ihm attackieren. Gegener deren Hitpoints auf Null sinken, werden aus
     * der Karte entfernt.
     */
    public void attackeRechts() {
        Karte aktuelleKarte = karten[karteX][karteY];
        Feld feldRechts = aktuelleKarte.feldAnKoordinate(lunk.zentrum().x+ Zulda.TILE_SIZE, lunk.zentrum().y);
        ArrayList<Gegner> gegnerRechts = aktuelleKarte.getGegnerAufFeld(feldRechts);
        for( Gegner g: gegnerRechts ) {
            // TODO: Überlgen, wie Schaden berechnet wird ...
            g.addHitpoints((int) ((lunk.getAttack() - g.getDefense()) * -0.5) );

            if( g.getHitpoints() <= 0 ) {
                aktuelleKarte.entferneGegner(g);
            }
        }
    }

    /**
     * Lässt den {@ink Lunk Spielercharakter} alle Gegner auf dem Feld links
     * von ihm attackieren. Gegener deren Hitpoints auf Null sinken, werden aus
     * der Karte entfernt.
     */
    public void attackeLinks() {
        Karte aktuelleKarte = karten[karteX][karteY];
        Feld feldRechts = aktuelleKarte.feldAnKoordinate(lunk.zentrum().x-Zulda.TILE_SIZE, lunk.zentrum().y);
        ArrayList<Gegner> gegnerRechts = aktuelleKarte.getGegnerAufFeld(feldRechts);
        for( Gegner g: gegnerRechts ) {
            // TODO: Überlgen, wie Schaden berechnet wird ...
            g.addHitpoints((int) ((lunk.getAttack() - g.getDefense()) * -0.5) );

            if( g.getHitpoints() <= 0 ) {
                aktuelleKarte.entferneGegner(g);
            }
        }
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
