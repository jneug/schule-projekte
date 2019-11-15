
public class KatholischeKirche {
    
    private int anzahlPlaetze;
    
    private String prediger;
    
    private int naechsterGottesdienst;
    
    private int naechsteFirmung;

    /**
     * Konstruktor für Objekte der Klasse Kirche
     */
    public KatholischeKirche( String pPrediger, int pAnahlPlaetze,
            int pNaechsterGottesdienst, int pNaechsteFirmung) {
        prediger = pPrediger;
        anzahlPlaetze = pAnahlPlaetze;
        naechsterGottesdienst = pNaechsterGottesdienst;
        naechsteFirmung = pNaechsteFirmung;
    }

    public void setPrediger( String pPrediger ) {
        prediger = pPrediger;
    }
    
    public String getPrediger() {
        return prediger;
    }
    
    public int getAnzahlPlaetze() {
        return anzahlPlaetze;
    }
    
    public void setNaechsterGottesdienst( int pNaechsterGottesdienst ) {
        naechsterGottesdienst = pNaechsterGottesdienst;
    }
    
    public int getNaechsterGottesdienst() {
        return naechsterGottesdienst;
    }
    
    public void setNaechsteFirmung( int pNaechsteFirmung ) {
        naechsteFirmung = pNaechsteFirmung;
    }
    
    public int getNaechsteFirmung() {
        return naechsteFirmung;
    }
}
