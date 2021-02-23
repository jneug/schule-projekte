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

    // Index der aktuelle sichtbaren Karte
    private int karteX, karteY;

    // Speicher der 12 Karten
    private Karte[][] karten;

    // Referenz zur Spielfigur
    private Lunk lunk;

    public Welt( Lunk pLunk ) {
        lunk = pLunk;

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

        karteX = 2;
        karteY = 2;
        add(karten[karteX][karteY]);

        lunk.setX(10*48);
        lunk.setY(8*48);
        add(lunk);
    }

    /**
     * Bewegt den Spielercharackter ein Feld nach links.
     */
    public void bewegeLinks() {
        lunk.zustandSetzen("run_left");
        if (lunk.aktuelleFigur().getX() <= 0) {
            if( karteX > 0 ) {
                entfernen(karten[karteX][karteY]);
                karteX -= 1;
                add(karten[karteX][karteY]);

                entfernen(lunk);
                lunk.verschieben(new Vektor((19*48)-lunk.aktuelleFigur().getX(), 0));
                add(lunk);
            }
        } else {
            lunk.verschieben(new Vektor(-48, 0));
        }
        lunk.zustandSetzen("idle_left");
    }

    /**
     * Bewegt den Spielercharackter ein Feld nach rechts.
     */
    public void bewegeRechts() {
        lunk.zustandSetzen("run_right");
        if (lunk.aktuelleFigur().getX() >= 19*48) {
            if( karteX < 3 ) {
                entfernen(karten[karteX][karteY]);
                karteX += 1;
                add(karten[karteX][karteY]);

                entfernen(lunk);
                lunk.verschieben(new Vektor(-1*lunk.aktuelleFigur().getX(), 0));
                add(lunk);
            }
        } else {
            lunk.verschieben(new Vektor(48, 0));
        }
        lunk.zustandSetzen("idle_right");
    }

    /**
     * Bewegt den Spielercharackter ein Feld nach oben.
     */
    public void bewegeHoch() {
        lunk.zustandSetzen("run_right");
        if (lunk.aktuelleFigur().getY() <= 0) {
            if( karteY > 0 ) {
                entfernen(karten[karteX][karteY]);
                karteY -= 1;
                add(karten[karteX][karteY]);

                entfernen(lunk);
                lunk.verschieben(new Vektor(0, (14*48)-lunk.aktuelleFigur().getY()));
                add(lunk);
            }
        } else {
            lunk.verschieben(new Vektor(0, -48));
        }
        lunk.zustandSetzen("idle_right");
    }

    /**
     * Bewegt den Spielercharackter ein Feld nach unten.
     */
    public void bewegeRunter() {
        lunk.zustandSetzen("run_left");
        if (lunk.aktuelleFigur().getY() >= 14*48) {
            if( karteY < 2 ) {
                entfernen(karten[karteX][karteY]);
                karteY += 1;
                add(karten[karteX][karteY]);

                entfernen(lunk);
                lunk.verschieben(new Vektor(0, -1*lunk.aktuelleFigur().getY()));
                add(lunk);
            }
        } else {
            lunk.verschieben(new Vektor(0, 48));
        }
        lunk.zustandSetzen("idle_left");
    }

}
