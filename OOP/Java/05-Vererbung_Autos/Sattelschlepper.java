/*aufg*
public class Sattelschlepper {

    private String bezeichnung;

    private int sitzplaetze;

    private int ps;

    private int reifen;

    private int baujahr;

    private int kilometerstand = 0;

    private double maxZuladung;

    private double zuladung = 0;

    public Sattelschlepper( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr, int pReifen, double pZuladung ) {
        bezeichnung = pBezeichnung;
        sitzplaetze = pPlaetze;
        ps = pPs;
        baujahr = pBaujahr;
        reifen = pReifen;
        maxZuladung = pZuladung;
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

    public double getZuladung() {
        return zuladung;
    }

    public boolean kannAufladen( double pLadung ) {
        return (getZuladung() + pLadung) <= maxZuladung;
    }

    public void aufladen( double pLadung ) {
        if( kannAufladen(pLadung) ) {
            zuladung += pLadung;
        }
    }

    public void abladen( double pLadung ) {
        if( getZuladung() >= pLadung ) {
            zuladung -= pLadung;
        }
    }

    public void fahren( int pStrecke) {
        kilometerstand += pStrecke;
    }

    public void hupen() {
        System.out.println("Tröööt, Tröööt!");
    }

}
*aufg*/
//ml*
public class Sattelschlepper extends Kfz {


    private double maxZuladung;

    private double zuladung = 0;

    public Sattelschlepper( String pBezeichnung, int pPlaetze, int pPs, int pBaujahr, int pReifen, double pZuladung ) {
        super(pBezeichnung, pPlaetze, pPs, pBaujahr, pReifen);
        this.maxZuladung = pZuladung;
    }

    public double getZuladung() {
        return zuladung;
    }

    public boolean kannAufladen( double pLadung ) {
        return (getZuladung() + pLadung) <= maxZuladung;
    }

    public void aufladen( double pLadung ) {
        if( kannAufladen(pLadung) ) {
            zuladung += pLadung;
        }
    }

    public void abladen( double pLadung ) {
        if( getZuladung() >= pLadung ) {
            zuladung -= pLadung;
        }
    }

    public void hupen() {
        System.out.println("Tröööt, Tröööt!");
    }

}
//*ml
