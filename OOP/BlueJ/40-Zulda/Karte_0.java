/**
 * Beispiel einer "von Hand" erstellten Karte in der Spielwelt.
 */
public class Karte_0 extends Karte {

    private Ork feind;

    private TrankAngriff trank;

    public Karte_0( int x, int y ) {
        super(x,y);

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
        feind = new Ork(this);
        verschiebeZuFeldAnIndex(feind,2, 2);
        add(feind);

        // Einen Trank als Gegenstand einfügen
        addFeld(18, 13, "sand");
        trank = new TrankAngriff(this);
        verschiebeZuFeldAnIndex(trank, 18,13);
        add(trank);
    }

    /**
     * Hilfsmethode, um einfach ein Feld an einem Index i,j in die Karte einzufügen.
     * @param i i-Index des Feldes
     * @param j j-Index des Feldes
     * @param typ Typ des Untergrundes (bestimmt auch die Passierbarkeit)
     */
    private void addFeld(int i, int j, String typ ) {
        felder[i][j] = new Feld(i*48, j * 48, typ);
        add(felder[i][j]);
    }

    @Override
    public void karteAnzeigen() {
        feind.start();
    }

    @Override
    public void karteVerstecken() {
        feind.stopp();
    }

}
