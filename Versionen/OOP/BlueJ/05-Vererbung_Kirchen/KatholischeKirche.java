

public class KatholischeKirche {

    private int anzahlPlaetze;

    private String prediger;

    private int naechsterGottesdienst;

    private int naechsteFirmung;

    public KatholischeKirche( String pPrediger, int pAnahlPlaetze,
                              int pNaechsterGottesdienst, int pNaechsteFirmung ) {
        prediger = pPrediger;
        anzahlPlaetze = pAnahlPlaetze;
        naechsterGottesdienst = pNaechsterGottesdienst;
        naechsteFirmung = pNaechsteFirmung;
    }

    public String getPrediger() {
        return prediger;
    }

    public void setPrediger( String pPrediger ) {
        prediger = pPrediger;
    }

    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }

    public int getNaechsterGottesdienst() {
        return naechsterGottesdienst;
    }

    public void setNaechsterGottesdienst( int pNaechsterGottesdienst ) {
        naechsterGottesdienst = pNaechsterGottesdienst;
    }

    public int getNaechsteFirmung() {
        return naechsteFirmung;
    }

    public void setNaechsteFirmung( int pNaechsteFirmung ) {
        naechsteFirmung = pNaechsteFirmung;
    }

}
