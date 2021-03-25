

/*aufg*
public class EvangelischeKirche {

    private int anzahlSitzplaetze;

    private String prediger;

    private int naechsterGottesdienst;

    private int naechsteKonfirmation;

    public EvangelischeKirche( String pPrediger, int pAnahlSitzplaetze,
                               int pNaechsterGottesdienst, int pNaechsteKonfirmation ) {
        prediger = pPrediger;
        anzahlSitzplaetze = pAnahlSitzplaetze;
        naechsterGottesdienst = pNaechsterGottesdienst;
        naechsteKonfirmation = pNaechsteKonfirmation;
    }

    public String getPrediger() {
        return prediger;
    }

    public void setPrediger( String pPrediger ) {
        prediger = pPrediger;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlSitzplaetze;
    }

    public int getNaechsterGottesdienst() {
        return naechsterGottesdienst;
    }

    public void setNaechsterGottesdienst( int pNaechsterGottesdienst ) {
        naechsterGottesdienst = pNaechsterGottesdienst;
    }

    public int getNaechsteKonfirmation() {
        return naechsteKonfirmation;
    }

    public void setNaechsteKonfirmation( int pNaechsteKonfirmation ) {
        naechsteKonfirmation = pNaechsteKonfirmation;
    }

}
*aufg*/
//ml*
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

    public int getNaechsteKonfirmation() {
        return naechsteKonfirmation;
    }

    public void setNaechsteKonfirmation( int pNaechsteKonfirmation ) {
        naechsteKonfirmation = pNaechsteKonfirmation;
    }

}
//*ml
