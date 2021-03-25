
public class Zahlengenerator {

    /**
     * Generiert ein Array mit <code>anzahl</code> Zufallszahlen im
     * Bereich von 0 bis 100.
     */
    public static int[] zufallsArray( int anzahl ) {
        return zufallsArray(anzahl, 0, 100);
    }

    /**
     * Generiert ein Array mit <code>anzahl</code> Zufallszahlen im
     * Bereich von <code>min</code> bis <code>max</code>.
     */
    public static int[] zufallsArray( int anzahl, int min, int max ) {
        return zufallsArray(anzahl, min, max, false);
    }

    /**
     * Generiert ein Array mit <code>anzahl</code> Zufallszahlen im
     * Bereich von <code>min</code> bis <code>max</code>. <code>sortiert</code>
     * bestimmt, ob die Zahlen in aufsteigender Reihenfolge sortiert erzeugt
     * werden.
     */
    public static int[] zufallsArray( int anzahl, int min, int max, boolean sortiert ) {
        // Negative Anzahlen abfangen
        if( anzahl < 0 ) {
            anzahl = 0;
        }

        // Neues int-Array der Größe "anzahl" initialisieren
        // und mit Zahlen befüllen
        int[] zahlen = new int[anzahl];
        for( int i = 0; i < anzahl; i++ ) {
            zahlen[i] = (int) (Math.random() * max + min);
        }

        // Zahlen-Array sortieren
        if( sortiert )
            java.util.Arrays.sort(zahlen);

        return zahlen;
    }

    /**
     * Generiert eine Liste mit <code>anzahl</code> Zufallszahlen im
     * Bereich von 0 bis 100.
     */
    public static List<Integer> zufallsList( int anzahl ) {
        return zufallsList(anzahl, 0, 100);
    }

    /**
     * Generiert eine Liste mit <code>anzahl</code> Zufallszahlen im
     * Bereich von <code>min</code> bis <code>max</code>.
     */
    public static List<Integer> zufallsList( int anzahl, int min, int max ) {
        return zufallsList(anzahl, min, max, false);
    }

    /**
     * Generiert eine Liste mit <code>anzahl</code> Zufallszahlen im
     * Bereich von <code>min</code> bis <code>max</code>. <code>sortiert</code>
     * bestimmt, ob die Zahlen in aufsteigender Reihenfolge sortiert erzeugt
     * werden.
     */
    public static List<Integer> zufallsList( int anzahl, int min, int max, boolean sortiert ) {
        int[] zahlen = zufallsArray(anzahl, min, max, sortiert);
        List<Integer> liste = new List<Integer>();
        for( int i = 0; i < zahlen.length; i++ ) {
            liste.append(zahlen[i]);
        }
        return liste;
    }

}
