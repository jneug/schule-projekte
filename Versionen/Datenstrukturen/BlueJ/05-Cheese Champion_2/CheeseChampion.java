import java.util.Random;

public class CheeseChampion {

    // TODO: Ersetze ??? durch die passende Datenstruktur
    private ???<Maus> eingang;
    private ???<Maus> seitengang;
    private ???<Maus> ausgang;

    public CheeseChampion() {
        // TODO: Ersetze ??? durch die passende Datenstruktur
        eingang = ???;
        seitengang = ???;
        ausgang = ???;
    }

    /**
     * Erzeugt <var>anzahl</var> fortlaufend nummerierte Mäuse (von 1 bis
     * <var>anzahl</var>) mit zufälliger Stärke.
     * @param anzahl
     * @see java.util.Random
     */
    public void erzeugeMaeuse( int anzahl ) {
        Random r = new Random();
        // TODO: Erzeuge hier "anzahl" Mäuse mit zufälliger Stärke
    }

    /**
     * Führt den Mäusekampf nach den bekannten Regeln durch.
     *
     * Siehe im Buch auf Seite 88 für eine Beschreibung des Kampfes.
     */
    public void kampf() {
        // TODO: Implemenitere hier den Mäusekampf
        while( !eingang.isEmpty() ) {
            // Hier fehlt was
        }

        // Kämpfe vorbei weil keine Maus mehr im Eingang
        // Restliche Mäuse zum Ausgang schicken
        while( !seitengang.isEmpty() ) {
            // Hier fehlt was
        }

        // Mäuse im Ausgang auf der Kommandozeile ausgeben
        while( !ausgang.isEmpty() ) {
            System.out.println("Maus #" + ausgang.front().getNummer());
            ausgang.dequeue();
        }
    }

    /**
     * Erzeugt eine Startaufstellung von zehn Mäusen mit festen Stärken, mit
     * denen der Kampf geprüft werden kann. Die erwartete Reihenfolge der Mäuse
     * im Ausgang lautet:
     * <pre>
     * #2, #3, #5, #6, #4, #1, #8, #9, #10, #7
     * </pre>
     */
    public void erzeugeTestMaeuse() {
        eingang.enqueue( new Maus(1, 8) );
        eingang.enqueue( new Maus(2, 75) );
        eingang.enqueue( new Maus(3, 33) );
        eingang.enqueue( new Maus(4, 23) );
        eingang.enqueue( new Maus(5, 73) );
        eingang.enqueue( new Maus(6, 71) );
        eingang.enqueue( new Maus(7, 6) );
        eingang.enqueue( new Maus(8, 86) );
        eingang.enqueue( new Maus(9, 63) );
        eingang.enqueue( new Maus(10, 38) );
    }


}
