/**
 * Ein Zähler, der einfach nur in einer Zählschleife bis zu einem Maximum zählt.
 * <p>
 * Jeder {@code Counter} ist ein eigener {@link Thread}.
 */
public class Counter extends Thread {

    /**
     * Main-Methode initialisiert einige Zähler und startet sie.
     *
     * @param args
     */
    public static void main( String[] args ) {
        // Obere Grenze, bis zu der gezählt werden soll
        int countTo = 10000;
        // Zähler erstellen
        Counter c1 = new Counter("Counter 1", countTo);
        Counter c2 = new Counter("Counter 2", countTo);
        Counter c3 = new Counter("Counter 3", countTo);
        Counter c4 = new Counter("Counter 4", countTo);
        // Zähler starten
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }

    /**
     * Der Name des Zählers.
     */
    private String name;

    /**
     * Obere Grenze des Zählers.
     */
    private int max;

    /**
     * Erstellt einen Zähler mit dem angegebenen Namen.
     *
     * @param pName
     */
    public Counter( String pName, int pMax ) {
        name = pName;
        max = pMax;
    }

    /**
     * Hauptmethode des {@link Thread}. Startet die Schleife und Zählt
     * bis {@link #max}.
     */
    @Override
    public void run() {
        for( int count = 0; count <= max; count++ ) {
            System.out.printf("%s: %04d\n", name, count);
        }
    }

}

