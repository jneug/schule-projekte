public class Waggon {

    private int nummer;
    private String zielort;
    private String ladung;

    public Waggon(int pNummer, String pZielort, String pLadung) {
        nummer = pNummer;
        zielort = pZielort;
        ladung = pLadung;
    }

    public int getNummer() {
        return nummer;
    }

    public String getZielort() {
        return zielort;
    }

    public void setZielort(String pZielort) {
        zielort = pZielort;
    }

    public String getLadung() {
        return ladung;
    }

    public void setLadung(String pLadung) {
        ladung = pLadung;
    }

}