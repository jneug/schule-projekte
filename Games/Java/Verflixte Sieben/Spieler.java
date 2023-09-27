import java.util.Scanner;

public class Spieler {

    private int nummer;

    private int punktzahl;

    private int punktzahlRunde;

    public Spieler( int pNummer ) {
        nummer = pNummer;
        punktzahl = 0;
    }

    public int getNummer() {
        return nummer;
    }

    public int getPunktzahl() {
        return punktzahl;
    }

    public int wuerfeln( Wuerfel pWuerfelEins, Wuerfel pWuerfelZwei ) {
        return pWuerfelEins.werfen()+pWuerfelZwei.werfen();
    }

    public int runde(Wuerfel pWuerfelEins, Wuerfel pWuerfelZwei) {
        Scanner eingabe = new Scanner(System.in);

        boolean weiter = true;
        while( weiter ) {
            System.out.print("Möchtest du weiter machen? (Ja/Nein): ");
            String entscheidung = eingabe.nextLine();
            if( entscheidung.trim().equalsIgnoreCase("ja") ) {
                int wurf = wuerfeln(pWuerfelEins, pWuerfelZwei);
                if( wurf == 7 ) {
                    punktzahlRunde = 0;
                    weiter = false;
                    System.out.printf("    Oh nein. :-( Diese verflixte Sieben!\n");
                } else {
                    punktzahlRunde += wurf;
                    System.out.printf("    Du hast %2d Punkte erwürfelt. Diese Runde bisher %3d.\n", wurf, punktzahlRunde);
                }
            } else {
                weiter = false;
            }
        }

        punktzahl += punktzahlRunde;
        return punktzahlRunde;
    }

}
