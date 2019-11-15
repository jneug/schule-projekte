
public class EvangelischeKirche extends Kirche {

    private int naechsteKonfirmation;

    /**
     * Konstruktor für Objekte der Klasse Kirche
     */
    public EvangelischeKirche( String pPrediger, int pAnzahlPlaetze,
            int pNaechsterGottesdienst, int pNaechsteKonfirmation ) {
        super(pPrediger, pAnzahlPlaetze, pNaechsterGottesdienst);
        naechsteKonfirmation = pNaechsteKonfirmation;
    }

    public void setNaechsteKonfirmation( int pNaechsteKonfirmation ) {
        naechsteKonfirmation = pNaechsteKonfirmation;
    }

    public int getNaechsteKonfirmation() {
        return naechsteKonfirmation;
    }
}
