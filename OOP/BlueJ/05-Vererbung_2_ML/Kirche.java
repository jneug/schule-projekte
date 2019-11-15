public class Kirche {

    protected int anzahlPlaetze;

    protected String prediger;

    protected int naechsterGottesdienst;

    public Kirche( String pPrediger, int pAnahlPlaetze,
                              int pNaechsterGottesdienst) {
        prediger = pPrediger;
        anzahlPlaetze = pAnahlPlaetze;
        naechsterGottesdienst = pNaechsterGottesdienst;
    }

    public void setPrediger( String pPrediger ) {
        prediger = pPrediger;
    }

    public String getPrediger() {
        return prediger;
    }

    public int getAnzahlSitzplaetze() {
        return anzahlPlaetze;
    }

    public void setNaechsterGottesdienst( int pNaechsterGottesdienst ) {
        naechsterGottesdienst = pNaechsterGottesdienst;
    }

    public int getNaechsterGottesdienst() {
        return naechsterGottesdienst;
    }

}
