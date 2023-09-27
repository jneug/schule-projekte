

public class VerflixteSieben {

    private Wuerfel wuerfelEins;

    private Wuerfel wuerfelZwei;

    private Spieler[] spieler;

    public VerflixteSieben( int anzahlSpieler ) {
        wuerfelEins = new Wuerfel();
        wuerfelZwei = new Wuerfel();

        spieler = new Spieler[anzahlSpieler];
        for( int i = 0; i < anzahlSpieler; i += 1 ) {
            spieler[i] = new Spieler(i+1);
        }
    }

    public void spielen() {
        for( int runde = 0; runde < 3; runde += 1 ) {
            for( int i = 0; i < spieler.length; i += 1 ) {
                System.out.printf("Spieler %2d ist an der Reihe.\n", spieler[i].getNummer());
                System.out.printf("Spieler %2d hat im Moment %3d Punkte.\n", spieler[i].getNummer(), spieler[i].getPunktzahl());
                int punktzahlRunde = spieler[i].runde(wuerfelEins, wuerfelZwei);
                System.out.printf("Spieler %2d hat diese Runde %3d Punkte erwürfelt.\n", spieler[i].getNummer(), punktzahlRunde);
            }
        }
    }

}
