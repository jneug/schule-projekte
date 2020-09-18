/**
 * Eine Maus, die am Mäusekampf (Cheese Champion) teilnimmt, hat eine Nummer
 * und eine Stärke als Attribute.
 */
public class Maus {

    private int nummer;

    private int staerke;

    public Maus( int pNummer, int pStaerke ) {
        nummer = pNummer;
        staerke = pStaerke;
    }

    public int getNummer() {
        return nummer;
    }

    public int getStaerke() {
        return staerke;
    }

}
