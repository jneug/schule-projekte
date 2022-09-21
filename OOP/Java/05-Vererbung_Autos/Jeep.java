/*aufg*
public class Jeep {

    private int sitzplaetze;

    private int ps;

    private String bezeichnung;

    private int reifen;

    private int baujahr;

    private int kilometerstand = 0;

    public Jeep( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr ) {
        bezeichnung = pBezeichnung;
        sitzplaetze = pPlaetze;
        ps = pPs;
        baujahr = pBaujahr;
        reifen = 4;
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

    public void fahren( int pStrecke) {
        kilometerstand += pStrecke;
    }

    public void hupen() {
        System.out.println("Honk, Honk!");
    }

}
*aufg*/
//ml*
public class Jeep extends Kfz {

    public Jeep( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr ) {
        super(pBezeichnung, pPlaetze, pPs, pBaujahr, 4);
    }

    public void hupen() {
        System.out.println("Honk, Honk!");
    }

}
//*ml
