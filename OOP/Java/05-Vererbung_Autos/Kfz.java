//ml*
public abstract class Kfz {

    private int sitzplaetze;

    private int ps;

    private String bezeichnung;

    private int reifen;

    private int baujahr;

    private int kilometerstand = 0;

    public Kfz( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr, int pReifen ) {
        bezeichnung = pBezeichnung;
        sitzplaetze = pPlaetze;
        ps = pPs;
        baujahr = pBaujahr;
        reifen = pReifen;
    }

    public int getSitzplaetze() {
        return sitzplaetze;
    }

    public void setSitzplaetze( int pSitzplaetze ) {
        this.sitzplaetze = pSitzplaetze;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung( String pBezeichnung ) {
        this.bezeichnung = pBezeichnung;
    }

    public int getPs() {
        return ps;
    }

    public int getReifen() {
        return reifen;
    }

    public int getBaujahr() {
        return baujahr;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public void fahren( int pStrecke ) {
        kilometerstand += pStrecke;
    }

    public abstract void hupen();

}
//*ml
