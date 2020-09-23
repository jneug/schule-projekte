//ml*
public class Kirche {

    protected int anzahlPlaetze;

    protected String prediger;

    protected int naechsterGottesdienst;

    public Kirche( String pPrediger, int pAnahlPlaetze,
                   int pNaechsterGottesdienst ) {
        prediger = pPrediger;
        anzahlPlaetze = pAnahlPlaetze;
        naechsterGottesdienst = pNaechsterGottesdienst;
    }

    public String getPrediger() {
        return prediger;
    }

    public void setPrediger( String pPrediger ) {
        prediger = pPrediger;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlPlaetze;
    }

    public int getNaechsterGottesdienst() {
        return naechsterGottesdienst;
    }

    public void setNaechsterGottesdienst( int pNaechsterGottesdienst ) {
        naechsterGottesdienst = pNaechsterGottesdienst;
    }

}
//*ml
