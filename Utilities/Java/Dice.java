import java.util.Random;

/**
 * Ein Würfel, der zufällig Ganzzahlen in einem festgelegten Interval erzeugt.
 *
 * @author J. Neugebauer
 * @version 1.1 (2022-01-30)
 */
public class Dice {

    /**
     * Kleinste und größte Zahl, die geworfen werden kann.
     */
    private final int minValue, maxValue;

    /**
     * Zufallsgenerator dieses Würfels.
     */
    private final Random rand;

    /**
     * Die zuletzt geworfene Zahl.
     */
    private int lastValue;

    /**
     * Erzeugt einen normalen, sechsseitigen Würfel.
     */
    public Dice() {
        this(1, 6);
    }

    /**
     * Erzeugt einen Würfel mit der angegebenen Anzahl Seiten.
     *
     * @param pMaxValue Die Anzahl Seiten des Würfels.
     */
    public Dice( int pMaxValue ) {
        this(1, pMaxValue);
    }

    /**
     * Erzeugt einen Würfel, der Zahlen im Bereich <var>pMinValue</var> bis
     * <var>pMaxValue</var> erzeugt (jeweils inklusiv). Ein sechseitiger
     * Würfel kann also mit {@code Die(1, 6)} erzeugt werden.
     *
     * @param pMinValue
     * @param pMaxValue
     */
    public Dice( int pMinValue, int pMaxValue ) {
        maxValue = pMaxValue;
        minValue = pMinValue;
        rand = new Random();
        roll();
    }

    /**
     * Gibt die zuletzt geworfene Zahl zurück (die "oben" liegt).
     *
     * @return Die zuletzt geworfene Zahl.
     */
    public int getValue() {
        return lastValue;
    }

    /**
     * Wirft den Würfel.
     *
     * @return Eine zufällige, ganze Zahl im festgelegten Bereich.
     */
    public int roll() {
        lastValue = rand.nextInt(maxValue - minValue + 1) + minValue;
        return lastValue;
    }

    /**
     * Wirft den Würfel <var>pTimes</var>-mal und gibt die Ergebnisse als Array
     * zurück.
     *
     * @param pTimes
     * @return
     */
    public int[] roll( int pTimes ) {
        int[] results = new int[pTimes];
        for( ; pTimes > 0; pTimes-- ) {
            results[pTimes - 1] = roll();
        }
        return results;
    }

    @Override
    public String toString() {
        return "Dice[" + minValue + "-" + maxValue +
            "]<" + lastValue + '>';
    }

}
