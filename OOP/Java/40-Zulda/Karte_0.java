import ea.DateiManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Beispiel einer "von Hand" erstellten Karte in der Spielwelt.
 */
public class Karte_0 extends Karte {

    public Karte_0( int x, int y, Welt pWelt ) {
        super(x, y, pWelt);

        for( int i = 0; i < felder.length; i++ ) {
            if( i < 9 || i > 10 ) {
                addFeld(i, 0, "stein");
            }
            addFeld(i, 14, "stein");
        }
        for (int j = 0; j < felder[0].length; j++) {
            if( j != 7 ){
                addFeld(0, j, "stein");
                addFeld(19, j, "stein");
            }
        }

        int centerX = 4, centerY = 5;
        addFeld(centerX, centerY-1, "wasser");
        addFeld(centerX-1, centerY, "wasser");
        addFeld(centerX, centerY, "wasser");
        addFeld(centerX+1, centerY, "wasser");
        addFeld(centerX, centerY+1, "wasser");

        addFeld(centerX, centerY-2, "sand");
        addFeld(centerX-2, centerY, "sand");
        addFeld(centerX, centerY+2, "sand");
        addFeld(centerX+2, centerY, "sand");
        addFeld(centerX-1, centerY-1, "sand");
        addFeld(centerX+1, centerY-1, "sand");
        addFeld(centerX+1, centerY+1, "sand");
        addFeld(centerX-1, centerY+1, "sand");

        centerX = 11;
        centerY = 5;
        for( int i = 0; i < 6; i++ ) {
            for (int j = 0; j < 6; j++) {
                if( i == 0 || j == 0 || i == 5 || j == 5 ) {
                    addFeld(centerX+i, centerY+j, "sand");
                } else {
                    addFeld(centerX+i, centerY+j, "stein");
                }
            }
        }

        // Einen Ork als Gegner einfügen
        addGegner(2, 2, new Ork(this));

        // Einen Trank als Gegenstand einfügen
        addFeld(18, 13, "sand");
        addGegenstand(18,13, new TrankAngriff(this));
    }

    /**
     * Hilfsmethode, um einfach ein Feld an einem Index i,j in die Karte einzufügen.
     * @param i i-Index des Feldes
     * @param j j-Index des Feldes
     * @param typ Typ des Untergrundes (bestimmt auch die Passierbarkeit)
     */
    private void addFeld(int i, int j, String typ ) {
        felder[i][j] = new Feld(i*Zulda.TILE_SIZE, j * Zulda.TILE_SIZE, typ);
        add(felder[i][j]);
    }

    @Override
    public void karteAnzeigen() {
        super.karteAnzeigen();
    }

    @Override
    public void karteVerstecken() {
        super.karteVerstecken();
    }

}
