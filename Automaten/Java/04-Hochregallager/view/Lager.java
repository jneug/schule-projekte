package view;

import schule.ngb.zm.Color;
import schule.ngb.zm.Constants;
import schule.ngb.zm.Options;
import schule.ngb.zm.Vector;
import schule.ngb.zm.layers.DrawingLayer;

public class Lager extends DrawingLayer {

    public static int EBENEN = 4, FAECHER = 8;

    public static int LAGERPLATZ_BREITE = 60, LAGERPLATZ_HOEHE = 50, BASIS_HOEHE = 20, PFOSTEN_BREITE = 8;

    public static int ABSTAND = 15;

    public static int WIDTH = 2 * ABSTAND + FAECHER * LAGERPLATZ_BREITE + FAECHER * PFOSTEN_BREITE + PFOSTEN_BREITE;

    public static int HEIGHT = 2 * ABSTAND + EBENEN * LAGERPLATZ_HOEHE + EBENEN + BASIS_HOEHE;

    public static Color FARBE = Color.BLACK;

    public Lager() {
        setSize(WIDTH, HEIGHT);

        setFill(FARBE);
        rect(ABSTAND, HEIGHT - BASIS_HOEHE, WIDTH - 2 * ABSTAND, ABSTAND, Constants.SOUTHWEST);

        String letters = "ABCDEFGHIJKLMNOPQRSTUVW";
        for( int i = 0; i < EBENEN; i += 1 ) {
            this.line(ABSTAND, HEIGHT - ABSTAND - BASIS_HOEHE - i * LAGERPLATZ_HOEHE, WIDTH - ABSTAND, HEIGHT - ABSTAND - BASIS_HOEHE - i * LAGERPLATZ_HOEHE);
            this.text(letters.charAt(i) + "", ABSTAND, HEIGHT - ABSTAND - i * LAGERPLATZ_HOEHE - BASIS_HOEHE - LAGERPLATZ_HOEHE * .5, EAST);
        }

        for( int i = 0; i < FAECHER+1; i++ ) {
            Vector fach = getKoordinatenVonFach(1, i + 1, SOUTHWEST);
            this.rect(fach.getX(), fach.getY(), PFOSTEN_BREITE, EBENEN * LAGERPLATZ_HOEHE, SOUTHEAST);
            this.text((i+1) + "", fach.getX() + LAGERPLATZ_BREITE / 2, ABSTAND, CENTER);
        }
    }

    public Vector getKoordinatenVonFach( int ebene, int fach, Options.Direction anchor ) {
        Vector point = new Vector(
            ABSTAND + fach * PFOSTEN_BREITE + (fach - 1) * LAGERPLATZ_BREITE + LAGERPLATZ_BREITE / 2,
            HEIGHT - ABSTAND - BASIS_HOEHE - (ebene - 1) * LAGERPLATZ_HOEHE - LAGERPLATZ_HOEHE / 2
        );

        Vector vec = anchor.asVector();
        point.x += vec.x * LAGERPLATZ_BREITE / 2;
        point.y += vec.y * LAGERPLATZ_HOEHE / 2;

        return point;
    }


}
