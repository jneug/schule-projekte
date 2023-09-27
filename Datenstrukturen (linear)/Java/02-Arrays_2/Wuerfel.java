import java.util.Random;

/**
 * Ein normaler, sechsseitiger Würfel.
 */
public class Wuerfel {

    private String name;

    private Random rand;

    private int value;

    private int rolls;

    /**
     * Erstellt einen Würfel mit einem Namen zur Identifikation.
     * @param pName
     */
    public Wuerfel( String pName ) {
        name = pName;
        rand = new Random();
        werfen();
        rolls = 0;
    }

    /**
     * Wirft den Würfel. Die geworfene Augenzahl
     * kann dann mit {@link #getAugenzahl()} abgerufen
     * werden.
     */
    public void werfen() {
        value = rand.nextInt(6)+1;
        rolls += 1;
    }

    public int getAugenzahl() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getAnzahlWuerfe() {
        return rolls;
    }

    public String toString() {
        return name + "<Wert:" + value + ", Würfe:" + rolls + ">";
    }
}
