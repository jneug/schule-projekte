/**
 * Eine Bank mit Kunden, die Geld von <em>einem gemeinsamen</em>
 * Konto abheben möchten.
 */
public class Bank {

    /**
     * Main-methode zum Start des Programms.
     *
     * @param args
     */
    public static void main( String[] args ) {
        // Das Konto mit 10000 Euro
        Konto konto = new Konto(10000);

        // Kunden erstellen
        Kunde[] kunden = new Kunde[]{
            new Kunde("Kunde 1", konto, 1000),
            new Kunde("Kunde 2", konto, 1000),
            new Kunde("Kunde 3", konto, 1000),
            new Kunde("Kunde 4", konto, 1000),
            new Kunde("Kunde 5", konto, 1000)
        };

        // Starten der Threads
        for( Kunde k : kunden ) {
            k.start();
        }

    }

}
