
/**
 * 
 */
public class Spieler {
    
    // 
    private String name;

    // 
    private int punkte;

    // 
    private Tisch tisch;

    /**
     * 
     */
    public Spieler(String pName, Tisch pTisch) {
        name = pName;
        punkte = 0;
        // 
        tisch = pTisch;
    }

    /**
     * 
     */
    public void wuerfeln() {
        // 
        tisch.wuerfelWerfen();
    }
    
}
