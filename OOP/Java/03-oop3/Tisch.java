
/**
 * 
 */
public class Tisch {

    // 
    private Wuerfel w1;
    private Wuerfel w2;
    private Wuerfel w3;

    //
    private Spieler spieler1;
    private Spieler spieler2;

    //
    private Spieler aktuellerSpieler;

    /**
     * 
     */
    public Tisch( String pNameS1, String pNameS2 ) {
        // 
        // 
        // 
        w1 = new Wuerfel(1);
        w2 = new Wuerfel(2);
        w3 = new Wuerfel(3);

        // 
        // 
        spieler1 = new Spieler(pNameS1, this);
        spieler2 = new Spieler(pNameS2, this);

        // 
        aktuellerSpieler = spieler1;
    }

    /**
     * 
     */
    public void wuerfelWerfen() {
        // 
        w1.werfen();
        w2.werfen();
        w3.werfen();
        // 
        System.out.printf("w1: %d, w2: %d, w3: %d\n", 
            // 
            w1.getAugenzahl(),
            w2.getAugenzahl(),
            w3.getAugenzahl() );
    }

    /**
     * 
     */
    public Spieler getAktuellenSpieler() {
        return aktuellerSpieler;
    }

    /**
     * 
     */
    public void naechsterSpieler() {
        // 
        // 
        if( aktuellerSpieler == spieler1 ) {
            // 
            aktuellerSpieler = spieler2;
        } else {
            // 
            aktuellerSpieler = spieler1;
        }
    }
}
