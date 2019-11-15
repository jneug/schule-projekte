
public class EvangelischeKirche {
    
    private int anzahlSitzplaetze;
    
    private String prediger;
    
    private int naechsterGottesdienst;
    
    private int naechsteKonfirmation;

    /**
     * Konstruktor für Objekte der Klasse Kirche
     */
    public EvangelischeKirche( String pPrediger, int pAnahlSitzplaetze,
            int pNaechsterGottesdienst, int pNaechsteKonfirmation ) {
        prediger = pPrediger;
        anzahlSitzplaetze = pAnahlSitzplaetze;
        naechsterGottesdienst = pNaechsterGottesdienst;
        naechsteKonfirmation = pNaechsteKonfirmation;
    }

    public void setPrediger( String pPrediger ) {
        prediger = pPrediger;
    }
    
    public String getPrediger() {
        return prediger;
    }
    
    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }
    
    public void setNaechsterGottesdienst( int pNaechsterGottesdienst ) {
        naechsterGottesdienst = pNaechsterGottesdienst;
    }
    
    public int getNaechsterGottesdienst() {
        return naechsterGottesdienst;
    }
    
    public void setNaechsteKonfirmation( int pNaechsteKonfirmation ) {
        naechsteKonfirmation = pNaechsteKonfirmation;
    }
    
    public int getNaechsteKonfirmation() {
        return naechsteKonfirmation;
    }
}
