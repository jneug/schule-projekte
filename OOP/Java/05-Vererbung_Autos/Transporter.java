/*aufg*
public class Transporter {

    private int sitzplaetze;

    private int ps;

    private String bezeichnung;

    private int reifen;

    private int baujahr;

    private int kilometerstand = 0;

    private double ladevolumen;

    public Transporter( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr, double pVolumen ) {
        bezeichnung = pBezeichnung;
        sitzplaetze = pPlaetze;
        ps = pPs;
        baujahr = pBaujahr;
        ladevolumen = pVolumen;
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

    public double getLadevolumen() {
        return ladevolumen;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public void fahren( int pStrecke) {
        kilometerstand += pStrecke;
    }

    public void hupen() {
        System.out.println("Huup, Huup!");
    }

}
*aufg*/
//ml*
public class Transporter extends Kfz {

    private double ladevolumen;

    public Transporter( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr, double ladevolumen ) {
        super(pBezeichnung, pPlaetze, pPs, pBaujahr, 4);
        this.ladevolumen = ladevolumen;
    }

    public double getLadevolumen() {
        return ladevolumen;
    }

    public void hupen() {
        System.out.println("Huup, Huup!");
    }

}
//*ml
