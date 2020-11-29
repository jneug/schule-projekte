public class Artikel {

    private int artnr;
    private String name;
    private double preis;

    public Artikel(int pArtnr, String pName, double pPreis) {
        artnr = pArtnr;
        name = pName;
        preis = pPreis;
    }

    public int getArtnr() {
        return artnr;
    }

    public String getName() {
        return name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double pPreis) {
        preis = pPreis;
    }

    public int hashCode() {
        return artnr;
    }

}