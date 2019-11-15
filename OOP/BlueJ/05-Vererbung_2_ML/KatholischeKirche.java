
public class KatholischeKirche extends Kirche  {

    private int naechsteFirmung;

    /**
     * Konstruktor für Objekte der Klasse Kirche
     */
    public KatholischeKirche( String pPrediger, int pAnahlPlaetze,
            int pNaechsterGottesdienst, int pNaechsteFirmung) {
        super(pPrediger, pAnahlPlaetze, pNaechsterGottesdienst);
        naechsteFirmung = pNaechsteFirmung;
    }

    public void setNaechsteFirmung( int pNaechsteFirmung ) {
        naechsteFirmung = pNaechsteFirmung;
    }

    public int getNaechsteFirmung() {
        return naechsteFirmung;
    }
}
