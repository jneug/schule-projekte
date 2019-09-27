import java.util.Random;

/**
 * Ein Würfel, der zufällig Ganzzahlen in einem festgelegten
 * Interval erzeugt.
 * @version 1.0 (2019-09-13)
 * @author J. Neugebauer <schule@neugebauer.cc>
 */
public class Die {

    private int maxValue, minValue;

    private Random rand;

    /**
     * Erzeugt einen normalen, sechsseitigen Würfel.
     */
    public Die() {
        this(1, 6);
    }

    /**
     * Erzeugt einen Würfel mit der angegebenen Anzahl Seiten.
     * @param pMaxValue
     */
    public Die( int pMaxValue ) {
        this(1, pMaxValue);
    }

    /**
     * Erzeugt einen Würfel, der Zahlen im Bereich <code>pMinValue</code> bis
     * <code>pMaxValue</code> erzeugt (jeweils inklusiv).
     * @param pMinValue
     * @param pMaxValue
     */
    public Die( int pMinValue, int pMaxValue ) {
        maxValue = pMaxValue;
        minValue = pMinValue;
        rand = new Random();
    }

    /**
     * Wirft den Würfel.
     * @return Eine zufällige, ganze Zahl im festgelegten Bereich.
     */
    public int roll() {
        return rand.nextInt(maxValue-minValue+1) + minValue;
    }

    /**
     * Wirft den Würfel <var>pTimes</var>-mal und gibt die
     * Ergebnisse als Array zurück.
     * @param pTimes
     * @return
     */
    public int[] roll( int pTimes ) {
        int[] results = new int[pTimes];
        for(; pTimes > 0; pTimes-- ) {
            results[pTimes-1] = roll();
        }
        return results;
    }

}
