import greenfoot.Greenfoot;

/**
 * Hilfreiche Methoden, um einen Rover zu programmieren.
 */
public class Utils {

    /**
     * Erzeugt eine zufällige Zahl zwischen 0 und <var>max</var> (exklusive).
     *
     * Der Aufruf <code>zufallsInt(4)</code> kann als Ergebnis also 0,1,2
     * oder 3 haben.
     */
    public static int zufallsInt( int max ) {
        return Greenfoot.getRandomNumber(max);
    }

    /**
     * Erzeugt eine zufällige Zahl zwischen <var>min</var> und <var>max</var>
     * (inklusive).
     *
     * Der Aufruf <code>zufallsInt(3,7)</code> kann als Ergebnis also 3,4,5,6
     * oder 7 haben.
     */
    public static int zufallsInt( int min, int max ) {
        return Greenfoot.getRandomNumber(max-min+1)+min;
    }

    /**
     * Erzeugt einen zufälligen Wahrheitswert.
     *
     * <code>true</code> und <code>false</code> haben die gleiche
     * Wahrscheinlichkeit.
     */
    public static boolean zufallsBool() {
        return Greenfoot.getRandomNumber(2) == 0;
    }
    /**
     * Erzeugt einen zufälligen Wahrheitswert, wobei <code>true</code> mit der
     * Wahrscheinlichkeit <var>wkeit</var> (in Prozent) erzeugt wird.
     *
     * Der Aufruf <code>zufallsBool(75)</code> erzeugt also in 75% der Fälle
     * den Wert <code>true</code>.
     */
    public static boolean zufallsBool( int wkeit ) {
        return Greenfoot.getRandomNumber(100) < wkeit;
    }
}
